package com.ronicus.mechanized.content.device.dome.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.ronicus.mechanized.client.BraceletConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class LiteSpaceDomeCurioRenderer implements ICurioRenderer {
    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack itemStack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource buffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        var entity = slotContext.entity();
        if (!(entity instanceof Player player)) return;

        EntityModel<T> model = renderLayerParent.getModel();
        if (!(model instanceof HumanoidModel<?> humanoidModel)) return;

        HumanoidArm arm = player.getMainArm();
        boolean isLeftArm = (arm == HumanoidArm.LEFT);

        boolean isSlim = false;
        if (player instanceof AbstractClientPlayer clientPlayer) {
            isSlim = (clientPlayer.getSkin().model() == PlayerSkin.Model.SLIM);
        }

        poseStack.pushPose();

        humanoidModel.translateToHand(arm, poseStack);

        float scale = isSlim ? BraceletConfig.slimScale : BraceletConfig.scale;
        float baseOffsetX = BraceletConfig.offsetX + (isSlim ? BraceletConfig.slimOffsetX : 0.0f);
        float offsetX = isLeftArm ? -baseOffsetX : baseOffsetX;
        float offsetY = BraceletConfig.offsetY;
        float offsetZ = BraceletConfig.offsetZ;

        float rotX = BraceletConfig.rotationX;
        float rotY = isLeftArm ? -BraceletConfig.rotationY : BraceletConfig.rotationY;
        float rotZ = isLeftArm ? -BraceletConfig.rotationZ : BraceletConfig.rotationZ;

        poseStack.translate(offsetX, offsetY, offsetZ);
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Axis.XP.rotationDegrees(rotX));
        poseStack.mulPose(Axis.YP.rotationDegrees(rotY));
        poseStack.mulPose(Axis.ZP.rotationDegrees(rotZ));

        Minecraft.getInstance().getItemRenderer()
                .renderStatic(itemStack, ItemDisplayContext.FIXED, light,
                        OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.level(), 0
                );

        poseStack.popPose();
    }
}
