package com.ronicus.mechanized.content.device.dome.client;

import com.ronicus.mechanized.content.device.dome.LiteSpaceDomeItem;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LiteSpaceDomeItemRenderer extends GeoItemRenderer<LiteSpaceDomeItem> {
    public LiteSpaceDomeItemRenderer() {
        super(new LiteSpaceDomeItemModel());
    }

    @Override
    public long getInstanceId(LiteSpaceDomeItem animatable) {
        if (this.currentItemStack != null) {
            long id = GeoItem.getId(this.currentItemStack);
            if (id != Long.MAX_VALUE) {
                return id;
            }
            String state = LiteSpaceDomeItem.getDomeState(this.currentItemStack);
            return ((long) this.currentItemStack.getItem().hashCode() << 32) ^ state.hashCode();
        }
        return super.getInstanceId(animatable);
    }

    @Override
    public void renderRecursively(com.mojang.blaze3d.vertex.PoseStack poseStack, LiteSpaceDomeItem animatable, software.bernie.geckolib.cache.object.GeoBone bone, net.minecraft.client.renderer.RenderType renderType, net.minecraft.client.renderer.MultiBufferSource bufferSource, com.mojang.blaze3d.vertex.VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        if ("front".equals(bone.getName()) && this.currentItemStack != null) {
            long instanceId = getInstanceId(animatable);
            software.bernie.geckolib.animation.AnimatableManager<LiteSpaceDomeItem> manager = animatable.getAnimatableInstanceCache().getManagerForId(instanceId);
            software.bernie.geckolib.animation.AnimationController<LiteSpaceDomeItem> controller = manager != null ? manager.getAnimationControllers().get("main_controller") : null;

            boolean isQuantumIdle = false;
            if (controller != null) {
                // Check if the current playing animation is "idle", or if base state is IDLE
                software.bernie.geckolib.animation.AnimationProcessor.QueuedAnimation currentAnim = controller.getCurrentAnimation();
                if (currentAnim != null && currentAnim.animation() != null) {
                    isQuantumIdle = "idle".equals(currentAnim.animation().name());
                }
            }

            // Fallback: If controller is not yet initialized or has no current animation, inspect ItemStack dome_state
            if (!isQuantumIdle) {
                isQuantumIdle = LiteSpaceDomeItem.STATE_IDLE.equals(LiteSpaceDomeItem.getDomeState(this.currentItemStack));
            }

            if (isQuantumIdle) {
                float gameTime = (net.minecraft.client.Minecraft.getInstance().level != null ? net.minecraft.client.Minecraft.getInstance().level.getGameTime() : 0) + partialTick;
                // Single seamless scroll loop spans 8 pixels. In a 32x32 texture, UV step = 8.0f / 32.0f = 0.25f
                float cycle = 8.0f / 32.0f;
                float speed = 0.05f; // Horizontal scrolling speed
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
