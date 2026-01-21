package com.ronicus.mechanized.device;

import com.ronicus.mechanized.client.BasicDomeItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class BasicDomeItem extends Item implements GeoItem {
    private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("idle");
    private static final RawAnimation STARTUP = RawAnimation.begin().thenPlay("startup");
    private static final RawAnimation STANDBY = RawAnimation.begin().thenPlay("standby");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public BasicDomeItem(Properties properties) {
        super(properties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private BasicDomeItemRenderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if (this.renderer == null)
                    this.renderer = new BasicDomeItemRenderer();

                return this.renderer;
            }
        });
    }

    private PlayState predicate(AnimationState state){
        state.getController().setAnimation(STANDBY);
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar data) {
        data.add(new AnimationController<>(this,"standby_cr", 0, this::predicate));
        data.add(new AnimationController<>(this,"startup_cr", state -> PlayState.CONTINUE).triggerableAnim("device_startup",STARTUP));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand){
        if(level instanceof ServerLevel serverLevel){
            this.triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(hand), serverLevel), "startup_cr", "device_startup");
        }
        return super.use(level, player, hand);
    }
}
