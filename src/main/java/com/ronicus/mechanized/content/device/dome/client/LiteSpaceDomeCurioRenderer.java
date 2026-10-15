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

        float scaleX = (isLeftArm ? BraceletConfig.leftScaleX : BraceletConfig.scaleX) * (isSlim ? BraceletConfig.slimScaleX : 1.0f);
        float scaleY = (isLeftArm ? BraceletConfig.leftScaleY : BraceletConfig.scaleY) * (isSlim ? BraceletConfig.slimScaleY : 1.0f);
        float scaleZ = (isLeftArm ? BraceletConfig.leftScaleZ : BraceletConfig.scaleZ) * (isSlim ? BraceletConfig.slimScaleZ : 1.0f);

        float slimShiftX = isSlim ? (isLeftArm ? -BraceletConfig.slimOffsetX : BraceletConfig.slimOffsetX) : 0.0f;
        float slimShiftY = isSlim ? BraceletConfig.slimOffsetY : 0.0f;
        float slimShiftZ = isSlim ? BraceletConfig.slimOffsetZ : 0.0f;

        float offsetX = (isLeftArm ? BraceletConfig.leftOffsetX : BraceletConfig.offsetX) + slimShiftX;
        float offsetY = (isLeftArm ? BraceletConfig.leftOffsetY : BraceletConfig.offsetY) + slimShiftY;
        float offsetZ = (isLeftArm ? BraceletConfig.leftOffsetZ : BraceletConfig.offsetZ) + slimShiftZ;

        float rotX = isLeftArm ? BraceletConfig.leftRotationX : BraceletConfig.rotationX;
        float rotY = isLeftArm ? BraceletConfig.leftRotationY : BraceletConfig.rotationY;
        float rotZ = isLeftArm ? BraceletConfig.leftRotationZ : BraceletConfig.rotationZ;

        poseStack.translate(offsetX, offsetY, offsetZ);
        poseStack.scale(scaleX, scaleY, scaleZ);
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
