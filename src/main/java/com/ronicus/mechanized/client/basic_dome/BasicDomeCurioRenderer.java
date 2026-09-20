package com.ronicus.mechanized.client.basic_dome;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.ronicus.mechanized.client.BraceletConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class BasicDomeCurioRenderer implements ICurioRenderer {
    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack itemStack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource buffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        var entity = slotContext.entity();
        if (!(entity instanceof Player)) return;

        EntityModel<T> model = renderLayerParent.getModel();
        if (!(model instanceof HumanoidModel<?> humanoidModel)) return;

        ModelPart limb = humanoidModel.rightArm;

        poseStack.pushPose();

        poseStack.translate(limb.x / 16.0, limb.y / 16.0, limb.z / 16.0);
        poseStack.mulPose(Axis.XP.rotation(limb.xRot));
        poseStack.mulPose(Axis.YP.rotation(limb.yRot));
        poseStack.mulPose(Axis.ZP.rotation(limb.zRot));

        poseStack.translate(BraceletConfig.offsetX, BraceletConfig.offsetY, BraceletConfig.offsetZ);
        poseStack.scale(BraceletConfig.scale, BraceletConfig.scale, BraceletConfig.scale);
        poseStack.mulPose(Axis.XP.rotationDegrees(BraceletConfig.rotationX));
        poseStack.mulPose(Axis.YP.rotationDegrees(BraceletConfig.rotationY));
        poseStack.mulPose(Axis.ZP.rotationDegrees(BraceletConfig.rotationZ));

        Minecraft.getInstance().getItemRenderer()
                .renderStatic(itemStack, ItemDisplayContext.FIXED, light,
                        OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.level(), 0
                );

        poseStack.popPose();
    }
}
