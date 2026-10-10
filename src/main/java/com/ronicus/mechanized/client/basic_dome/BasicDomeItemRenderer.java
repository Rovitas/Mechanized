package com.ronicus.mechanized.client.basic_dome;

import com.ronicus.mechanized.device.BasicDomeItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BasicDomeItemRenderer extends GeoItemRenderer<BasicDomeItem> {
    public BasicDomeItemRenderer() {
        super(new BasicDomeItemModel());
    }

    @Override
    public long getInstanceId(BasicDomeItem animatable) {
        if (this.currentItemStack != null) {
            long id = GeoItem.getId(this.currentItemStack);
            if (id != Long.MAX_VALUE) {
                return id;
            }
            String state = BasicDomeItem.getDomeState(this.currentItemStack);
            return ((long) this.currentItemStack.getItem().hashCode() << 32) ^ state.hashCode();
        }
        return super.getInstanceId(animatable);
    }

    @Override
    public void renderRecursively(com.mojang.blaze3d.vertex.PoseStack poseStack, BasicDomeItem animatable, software.bernie.geckolib.cache.object.GeoBone bone, net.minecraft.client.renderer.RenderType renderType, net.minecraft.client.renderer.MultiBufferSource bufferSource, com.mojang.blaze3d.vertex.VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        if ("front".equals(bone.getName()) && this.currentItemStack != null) {
            long instanceId = getInstanceId(animatable);
            software.bernie.geckolib.animation.AnimatableManager<BasicDomeItem> manager = animatable.getAnimatableInstanceCache().getManagerForId(instanceId);
            software.bernie.geckolib.animation.AnimationController<BasicDomeItem> controller = manager != null ? manager.getAnimationControllers().get("main_controller") : null;

            boolean isQuantumIdle = false;
            if (controller != null) {
                // 如果当前控制器正在播放的动画包含 idle，或者当前基础状态是 IDLE
                software.bernie.geckolib.animation.AnimationProcessor.QueuedAnimation currentAnim = controller.getCurrentAnimation();
                if (currentAnim != null && currentAnim.animation() != null) {
                    isQuantumIdle = "idle".equals(currentAnim.animation().name());
                }
            }

            // 兜底：如果控制器暂未初始化或无当前动画，回退检查物品本身的 dome_state
            if (!isQuantumIdle) {
                isQuantumIdle = BasicDomeItem.STATE_IDLE.equals(BasicDomeItem.getDomeState(this.currentItemStack));
            }

            if (isQuantumIdle) {
                float gameTime = (net.minecraft.client.Minecraft.getInstance().level != null ? net.minecraft.client.Minecraft.getInstance().level.getGameTime() : 0) + partialTick;
                // 单个循环为 8 像素，在 32x32 贴图中跨度为 8.0f / 32.0f = 0.25f
                float cycle = 8.0f / 32.0f;
                float speed = 0.05f; // 横向滚动速度
                float uOffset = (gameTime * speed) % cycle;

                com.mojang.blaze3d.vertex.VertexConsumer uvBuffer = new com.mojang.blaze3d.vertex.VertexConsumer() {
                    @Override
                    public com.mojang.blaze3d.vertex.VertexConsumer addVertex(float x, float y, float z) {
                        buffer.addVertex(x, y, z);
                        return this;
                    }

                    @Override
                    public com.mojang.blaze3d.vertex.VertexConsumer setColor(int red, int green, int blue, int alpha) {
                        buffer.setColor(red, green, blue, alpha);
                        return this;
                    }

                    @Override
                    public com.mojang.blaze3d.vertex.VertexConsumer setUv(float u, float v) {
                        buffer.setUv(u + uOffset, v);
                        return this;
                    }

                    @Override
                    public com.mojang.blaze3d.vertex.VertexConsumer setUv1(int u, int v) {
                        buffer.setUv1(u, v);
                        return this;
                    }

                    @Override
                    public com.mojang.blaze3d.vertex.VertexConsumer setUv2(int u, int v) {
                        buffer.setUv2(u, v);
                        return this;
                    }

                    @Override
                    public com.mojang.blaze3d.vertex.VertexConsumer setNormal(float normalX, float normalY, float normalZ) {
                        buffer.setNormal(normalX, normalY, normalZ);
                        return this;
                    }
                };

                super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, uvBuffer, isReRender, partialTick, packedLight, packedOverlay, colour);
                return;
            }
        }

        super.renderRecursively(poseStack, animatable, bone, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);
    }
}
