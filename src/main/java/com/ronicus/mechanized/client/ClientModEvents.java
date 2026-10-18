package com.ronicus.mechanized.client;

import com.ronicus.mechanized.Mechanized;
import com.ronicus.mechanized.content.device.dome.client.LiteSpaceDomeCurioRenderer;
import com.ronicus.mechanized.core.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientChatEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@EventBusSubscriber(modid = Mechanized.MODID, value = Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        CuriosRendererRegistry.register(ModItems.LITE_SPACE_DOME.get(), LiteSpaceDomeCurioRenderer::new);
        CuriosRendererRegistry.load();
        NeoForge.EVENT_BUS.addListener(ClientModEvents::onClientChat);
        System.out.println("[Mechanized] Successfully registered Curios renderer and chat tuning listener for Lite Space Dome.");
    }

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        CuriosRendererRegistry.register(ModItems.LITE_SPACE_DOME.get(), LiteSpaceDomeCurioRenderer::new);
        CuriosRendererRegistry.load();
    }

    public static void onClientChat(ClientChatEvent event) {
        String msg = event.getMessage();
        if (msg.equals("bracelet") || msg.startsWith("bracelet ")) {
            event.setCanceled(true);

            String[] args = msg.length() == 8 ? new String[0] : msg.substring(9).trim().split("\\s+");

            try {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player == null) return;

                boolean isLeftArm = (mc.player.getMainArm() == net.minecraft.world.entity.HumanoidArm.LEFT);
                boolean isSlim = (mc.player.getSkin().model() == PlayerSkin.Model.SLIM);
                BraceletConfig.Transform active = BraceletConfig.get(isLeftArm, isSlim);
                String activeName = (isLeftArm ? "Left" : "Right") + " Arm (" + (isSlim ? "Alex 3px" : "Steve 4px") + ")";

                if (args.length == 0 || args[0].isEmpty() || "help".equals(args[0])) {
                    sendHelp(activeName);
                    return;
                }

                String command = args[0];

                if ("offset".equals(command)) {
                    if (args.length == 4) {
                        float x = Float.parseFloat(args[1]);
                        float y = Float.parseFloat(args[2]);
                        float z = Float.parseFloat(args[3]);
                        active.offsetX = x;
                        active.offsetY = y;
                        active.offsetZ = z;
                        sendFeedback(String.format("Updated offset for [%s]: (%.3f, %.3f, %.3f)", activeName, x, y, z));
                    } else {
                        sendError("Usage: bracelet offset <x> <y> <z>");
                    }
                } else if ("scale".equals(command)) {
                    if (args.length == 2) {
                        float s = Float.parseFloat(args[1]);
                        active.scaleX = s;
                        active.scaleY = s;
                        active.scaleZ = s;
                        sendFeedback(String.format("Updated scale for [%s]: %.3f", activeName, s));
                    } else if (args.length == 4) {
                        float sx = Float.parseFloat(args[1]);
                        float sy = Float.parseFloat(args[2]);
                        float sz = Float.parseFloat(args[3]);
                        active.scaleX = sx;
                        active.scaleY = sy;
                        active.scaleZ = sz;
                        sendFeedback(String.format("Updated 3D scale for [%s]: (%.3f, %.3f, %.3f)", activeName, sx, sy, sz));
                    } else {
                        sendError("Usage: bracelet scale <s> OR bracelet scale <sx> <sy> <sz>");
                    }
                } else if ("rot".equals(command)) {
                    if (args.length == 4) {
                        float x = Float.parseFloat(args[1]);
                        float y = Float.parseFloat(args[2]);
                        float z = Float.parseFloat(args[3]);
                        active.rotationX = x;
                        active.rotationY = y;
                        active.rotationZ = z;
                        sendFeedback(String.format("Updated rotation for [%s]: (%.3f, %.3f, %.3f)", activeName, x, y, z));
                    } else {
                        sendError("Usage: bracelet rot <degreesX> <degreesY> <degreesZ>");
                    }
                } else if ("reset".equals(command)) {
                    if (args.length >= 2 && "all".equalsIgnoreCase(args[1])) {
                        BraceletConfig.resetAll();
                        sendFeedback("Reset all 4 arm profiles to defaults.");
                    } else {
                        BraceletConfig.reset(isLeftArm, isSlim);
                        sendFeedback("Reset [" + activeName + "] to default values.");
                    }
                } else if ("show".equals(command)) {
                    showArgs(activeName, active);
                } else {
                    sendError("Unknown command. Use 'bracelet help' for usage.");
                }

            } catch (NumberFormatException e) {
                sendError("Invalid number! Please enter valid decimals.");
            }
        }
    }

    private static void sendFeedback(String message) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.player.displayClientMessage(Component.literal("§a[Bracelet] " + message), false);
        }
    }

    private static void sendError(String message) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.player.displayClientMessage(Component.literal("§c[Bracelet] " + message), false);
        }
    }

    private static void sendHelp(String activeName) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.player.displayClientMessage(Component.literal("§a=== Bracelet Context-Aware Commands ==="), false);
            mc.player.displayClientMessage(Component.literal("§eCurrently viewing: §f" + activeName), false);
            mc.player.displayClientMessage(Component.literal("§7Commands automatically apply to your active arm:"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet offset <x> <y> <z>"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet scale <s> OR <sx> <sy> <sz>"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet rot <x> <y> <z>"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet reset §7(Reset active arm)"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet reset all §7(Reset all 4 profiles)"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet show §7(View active & all profiles)"), false);
        }
    }

    private static void showArgs(String activeName, BraceletConfig.Transform active) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.player.displayClientMessage(Component.literal("§a=== Bracelet Configuration ==="), false);
            mc.player.displayClientMessage(Component.literal("§6▶ ACTIVE: §f" + activeName), false);
            mc.player.displayClientMessage(Component.literal(String.format("  Offset:   (%.3f, %.3f, %.3f)", active.offsetX, active.offsetY, active.offsetZ)), false);
            mc.player.displayClientMessage(Component.literal(String.format("  Rotation: (%.1f°, %.1f°, %.1f°)", active.rotationX, active.rotationY, active.rotationZ)), false);
            mc.player.displayClientMessage(Component.literal(String.format("  Scale:    (%.3f, %.3f, %.3f)", active.scaleX, active.scaleY, active.scaleZ)), false);
            mc.player.displayClientMessage(Component.literal("§7--- All Profiles Overview ---"), false);
            printProfileSummary(mc, "Right Wide (Steve)", BraceletConfig.RIGHT_WIDE);
            printProfileSummary(mc, "Right Slim (Alex) ", BraceletConfig.RIGHT_SLIM);
            printProfileSummary(mc, "Left  Wide (Steve)", BraceletConfig.LEFT_WIDE);
            printProfileSummary(mc, "Left  Slim (Alex) ", BraceletConfig.LEFT_SLIM);
            mc.player.displayClientMessage(Component.literal("§a================================"), false);
        }
    }

    private static void printProfileSummary(Minecraft mc, String label, BraceletConfig.Transform t) {
        mc.player.displayClientMessage(Component.literal(String.format("§8- §7%s: §fpos=(%.3f, %.3f, %.3f) §7rot=(%.0f, %.0f, %.0f) §7scale=(%.3f, %.3f, %.3f)",
                label, t.offsetX, t.offsetY, t.offsetZ, t.rotationX, t.rotationY, t.rotationZ, t.scaleX, t.scaleY, t.scaleZ)), false);
    }
}
