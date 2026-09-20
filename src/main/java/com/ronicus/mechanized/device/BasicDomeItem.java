package com.ronicus.mechanized.device;

import com.ronicus.mechanized.ModItems;
import com.ronicus.mechanized.client.basic_dome.BasicDomeItemRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class BasicDomeItem extends Item implements GeoItem {
    public static final String STATE_TAG = "dome_state";
    public static final String STATE_STANDBY = "standby";
    public static final String STATE_IDLE = "idle";
    public static final String STATE_STARTUP = "startup";
    public static final String STATE_SHUTDOWN = "shutdown";

    private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("idle");
    private static final RawAnimation STARTUP = RawAnimation.begin().then("startup", Animation.LoopType.PLAY_ONCE).thenLoop("idle");
    private static final RawAnimation SHUTDOWN = RawAnimation.begin().then("shutdown", Animation.LoopType.PLAY_ONCE).thenPlayAndHold("standby");
    private static final RawAnimation STANDBY = RawAnimation.begin().thenPlayAndHold("standby");

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public BasicDomeItem(Properties properties) {
        super(properties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private BasicDomeItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new BasicDomeItemRenderer();

                return this.renderer;
            }
        });
    }

    private PlayState predicate(AnimationState<BasicDomeItem> state) {
        ItemStack stack = state.getData(DataTickets.ITEMSTACK);
        String domeState = getDomeState(stack);

        switch (domeState) {
            case STATE_IDLE -> state.getController().setAnimation(IDLE);
            case STATE_STARTUP -> state.getController().setAnimation(STARTUP);
            case STATE_SHUTDOWN -> state.getController().setAnimation(SHUTDOWN);
            default -> state.getController().setAnimation(STANDBY);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "main_controller", 0, this::predicate)
                .triggerableAnim("startup", STARTUP)
                .triggerableAnim("shutdown", SHUTDOWN));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    /**
     * 获取指定 ItemStack 的状态字符串，默认为 "standby"
     */
    public static String getDomeState(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) return STATE_STANDBY;
        CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = customData.copyTag();
        if (tag.contains(STATE_TAG)) {
            return tag.getString(STATE_TAG);
        }
        return STATE_STANDBY;
    }

    /**
     * 设置指定 ItemStack 的状态字符串（如 "idle" / "standby"）
     */
    public static void setDomeState(ItemStack stack, String state) {
        if (stack == null || stack.isEmpty()) return;
        CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> {
            tag.putString(STATE_TAG, state);
        });
    }

    /**
     * 快捷创建一个带有指定状态的 ItemStack（方便创造栏图标、GUI 渲染等调用）
     */
    public static ItemStack createStack(String state) {
        ItemStack stack = new ItemStack(ModItems.BASIC_DOME.get());
        setDomeState(stack, state);
        return stack;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        String currentState = getDomeState(itemStack);

        if (level instanceof ServerLevel serverLevel) {
            if (STATE_STANDBY.equals(currentState)) {
                setDomeState(itemStack, STATE_IDLE);
                this.triggerAnim(player, GeoItem.getOrAssignId(itemStack, serverLevel), "main_controller", "startup");
                player.displayClientMessage(Component.literal("Dome activated"), true);
            } else {
                setDomeState(itemStack, STATE_STANDBY);
                this.triggerAnim(player, GeoItem.getOrAssignId(itemStack, serverLevel), "main_controller", "shutdown");
                player.displayClientMessage(Component.literal("Dome deactivated"), true);
            }
            return InteractionResultHolder.success(itemStack);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        boolean isActive = STATE_IDLE.equals(getDomeState(stack));
        Component stateText = isActive
                ? Component.translatable("tooltip.mechanized.basic_dome.state.active").withStyle(ChatFormatting.GREEN)
                : Component.translatable("tooltip.mechanized.basic_dome.state.inactive").withStyle(ChatFormatting.RED);

        tooltipComponents.add(Component.translatable("tooltip.mechanized.basic_dome.state", stateText).withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
