package com.ronicus.mechanized.device;

import com.ronicus.mechanized.client.basic_dome.BasicDomeItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class BasicDomeItem extends Item implements GeoItem {
    private static final String STATE_TAG = "mechanized:dome_state";
    private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("idle");
    private static final RawAnimation STARTUP = RawAnimation.begin().thenPlay("startup");
    private static final RawAnimation STANDBY = RawAnimation.begin().thenPlay("standby");
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

    private PlayState predicate(AnimationState<?> state) {
        state.getController().setAnimation(IDLE);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this, "standby_cr", 0, this::predicate));
        data.add(new AnimationController<>(this, "startup_cr", state -> PlayState.CONTINUE).triggerableAnim("device_startup", STARTUP));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    /**
     * 在 MC 1.21.1 中使用 Data Components (CustomData) 安全保存状态并提示玩家
     */
    public static void setDomeState(ItemStack stack, Player player) {
        CustomData customData = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
        CompoundTag tag = customData.copyTag();
        byte newState = 1;

        if (tag.contains(STATE_TAG, Tag.TAG_BYTE)) {
            newState = tag.getByte(STATE_TAG);
        } else {
            byte finalState = newState;
            CustomData.update(DataComponents.CUSTOM_DATA, stack, t -> t.putByte(STATE_TAG, finalState));
        }

        if (player != null) {
            player.displayClientMessage(Component.literal("DomeState changed to " + newState), false);
        }
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (level instanceof ServerLevel serverLevel) {
            this.triggerAnim(player, GeoItem.getOrAssignId(itemStack, serverLevel), "startup_cr", "device_startup");
            return InteractionResultHolder.success(itemStack);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
