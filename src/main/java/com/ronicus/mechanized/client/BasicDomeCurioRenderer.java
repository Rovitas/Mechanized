package com.ronicus.mechanized.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
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
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack itemStack, SlotContext slotContext, PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource buffer
            , int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        // 只对玩家生效
        var entity = slotContext.entity();
        if (!(entity instanceof Player)) return;

        // 获取模型（必须是 HumanoidModel，如 PlayerModel）
        EntityModel<T> model = renderLayerParent.getModel();
        if (!(model instanceof HumanoidModel<?> humanoidModel)) return;

        ModelPart limb = humanoidModel.rightArm;

        poseStack.pushPose();

        // 平移：模型坐标单位是像素，需除以 16 转为方块单位
        poseStack.translate(limb.x / 16.0, limb.y / 16.0, limb.z / 16.0);
        // 旋转：应用当前肢体的旋转（弧度）
        poseStack.mulPose(Axis.XP.rotation(limb.xRot));
        poseStack.mulPose(Axis.YP.rotation(limb.yRot));
        poseStack.mulPose(Axis.ZP.rotation(limb.zRot));

        // 微调：让手环贴合手腕内侧（避免穿入手臂）
        poseStack.translate(BraceletConfig.offsetX, BraceletConfig.offsetY, BraceletConfig.offsetZ); // 向下 + 向里偏移
        poseStack.scale(BraceletConfig.scale, BraceletConfig.scale, BraceletConfig.scale);     // 缩小到合适尺寸
        poseStack.mulPose(Axis.XP.rotationDegrees(BraceletConfig.rotationX));
        poseStack.mulPose(Axis.YP.rotationDegrees(BraceletConfig.rotationY));
        poseStack.mulPose(Axis.ZP.rotationDegrees(BraceletConfig.rotationZ));

        // 渲染物品
        Minecraft.getInstance().getItemRenderer()
                .renderStatic(itemStack, ItemDisplayContext.FIXED, light,
                        OverlayTexture.NO_OVERLAY, poseStack, buffer, entity.level(), 0
                );

        poseStack.popPose();
    }

}
