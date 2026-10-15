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
                if (args.length == 0 || args[0].isEmpty() || "help".equals(args[0])) {
                    sendHelp();
                    return;
                }

                String command = args[0];

                if ("offset".equals(command)) {
                    if (args.length == 4) {
                        float x = Float.parseFloat(args[1]);
                        float y = Float.parseFloat(args[2]);
                        float z = Float.parseFloat(args[3]);
                        BraceletConfig.offsetX = x;
                        BraceletConfig.offsetY = y;
                        BraceletConfig.offsetZ = z;
                        sendFeedback(String.format("Right Offset updated: %.3f, %.3f, %.3f", x, y, z));
                    } else {
                        sendError("Usage: bracelet offset <x> <y> <z>");
                    }
                } else if ("scale".equals(command)) {
                    if (args.length == 2) {
                        float s = Float.parseFloat(args[1]);
                        BraceletConfig.scaleX = s;
                        BraceletConfig.scaleY = s;
                        BraceletConfig.scaleZ = s;
                        sendFeedback(String.format("Right Scale updated: %.3f", s));
                    } else if (args.length == 4) {
                        float sx = Float.parseFloat(args[1]);
                        float sy = Float.parseFloat(args[2]);
                        float sz = Float.parseFloat(args[3]);
                        BraceletConfig.scaleX = sx;
                        BraceletConfig.scaleY = sy;
                        BraceletConfig.scaleZ = sz;
                        sendFeedback(String.format("Right Scale updated (3D): %.3f, %.3f, %.3f", sx, sy, sz));
                    } else {
                        sendError("Usage: bracelet scale <s> OR bracelet scale <sx> <sy> <sz>");
                    }
                } else if ("rot".equals(command)) {
                    if (args.length == 4) {
                        float x = Float.parseFloat(args[1]);
                        float y = Float.parseFloat(args[2]);
                        float z = Float.parseFloat(args[3]);
                        BraceletConfig.rotationX = x;
                        BraceletConfig.rotationY = y;
                        BraceletConfig.rotationZ = z;
                        sendFeedback(String.format("Right Rotation updated: %.3f, %.3f, %.3f", x, y, z));
                    } else {
                        sendError("Usage: bracelet rot <degreesX> <degreesY> <degreesZ>");
                    }
                } else if ("left".equals(command)) {
                    if (args.length >= 2 && "offset".equals(args[1])) {
                        if (args.length == 5) {
                            float x = Float.parseFloat(args[2]);
                            float y = Float.parseFloat(args[3]);
                            float z = Float.parseFloat(args[4]);
                            BraceletConfig.leftOffsetX = x;
                            BraceletConfig.leftOffsetY = y;
                            BraceletConfig.leftOffsetZ = z;
                            sendFeedback(String.format("Left Offset updated: %.3f, %.3f, %.3f", x, y, z));
                        } else {
                            sendError("Usage: bracelet left offset <x> <y> <z>");
                        }
                    } else if (args.length >= 2 && "scale".equals(args[1])) {
                        if (args.length == 3) {
                            float s = Float.parseFloat(args[2]);
                            BraceletConfig.leftScaleX = s;
                            BraceletConfig.leftScaleY = s;
                            BraceletConfig.leftScaleZ = s;
                            sendFeedback(String.format("Left Scale updated: %.3f", s));
                        } else if (args.length == 5) {
                            float sx = Float.parseFloat(args[2]);
                            float sy = Float.parseFloat(args[3]);
                            float sz = Float.parseFloat(args[4]);
                            BraceletConfig.leftScaleX = sx;
                            BraceletConfig.leftScaleY = sy;
                            BraceletConfig.leftScaleZ = sz;
                            sendFeedback(String.format("Left Scale updated (3D): %.3f, %.3f, %.3f", sx, sy, sz));
                        } else {
                            sendError("Usage: bracelet left scale <s> OR bracelet left scale <sx> <sy> <sz>");
                        }
                    } else if (args.length >= 2 && "rot".equals(args[1])) {
                        if (args.length == 5) {
                            float x = Float.parseFloat(args[2]);
                            float y = Float.parseFloat(args[3]);
                            float z = Float.parseFloat(args[4]);
                            BraceletConfig.leftRotationX = x;
                            BraceletConfig.leftRotationY = y;
                            BraceletConfig.leftRotationZ = z;
                            sendFeedback(String.format("Left Rotation updated: %.3f, %.3f, %.3f", x, y, z));
                        } else {
                            sendError("Usage: bracelet left rot <degreesX> <degreesY> <degreesZ>");
                        }
                    } else {
                        sendError("Usage: bracelet left <offset|scale|rot> ...");
                    }
                } else if ("slim".equals(command)) {
                    if (args.length >= 2 && "offset".equals(args[1])) {
                        if (args.length == 3) {
                            float x = Float.parseFloat(args[2]);
                            BraceletConfig.slimOffsetX = x;
                            sendFeedback(String.format("Slim arm X offset updated: %.3f", x));
                        } else if (args.length == 5) {
                            float x = Float.parseFloat(args[2]);
                            float y = Float.parseFloat(args[3]);
                            float z = Float.parseFloat(args[4]);
                            BraceletConfig.slimOffsetX = x;
                            BraceletConfig.slimOffsetY = y;
                            BraceletConfig.slimOffsetZ = z;
                            sendFeedback(String.format("Slim arm Offset updated: %.3f, %.3f, %.3f", x, y, z));
                        } else {
                            sendError("Usage: bracelet slim offset <x> [y] [z]");
                        }
                    } else if (args.length >= 2 && "scale".equals(args[1])) {
                        if (args.length == 3) {
                            float s = Float.parseFloat(args[2]);
                            BraceletConfig.slimScaleX = s;
                            BraceletConfig.slimScaleY = s;
                            BraceletConfig.slimScaleZ = s;
                            sendFeedback(String.format("Slim arm scale multiplier updated: %.3f", s));
                        } else if (args.length == 5) {
                            float sx = Float.parseFloat(args[2]);
                            float sy = Float.parseFloat(args[3]);
                            float sz = Float.parseFloat(args[4]);
                            BraceletConfig.slimScaleX = sx;
                            BraceletConfig.slimScaleY = sy;
                            BraceletConfig.slimScaleZ = sz;
                            sendFeedback(String.format("Slim arm 3D scale multiplier updated: %.3f, %.3f, %.3f", sx, sy, sz));
                        } else {
                            sendError("Usage: bracelet slim scale <val> OR bracelet slim scale <sx> <sy> <sz>");
                        }
                    } else {
                        sendError("Usage: bracelet slim <offset|scale> ...");
                    }
                } else if ("show".equals(command)) {
                    showArgs();
                } else {
                    sendError("Unknown command. Use bracelet help");
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

    private static void sendHelp() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.player.displayClientMessage(Component.literal("§a=== Bracelet Debug Commands ==="), false);
            mc.player.displayClientMessage(Component.literal("§7[Right Arm]:"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet offset <x> <y> <z>"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet scale <s> OR <sx> <sy> <sz>"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet rot <x> <y> <z>"), false);
            mc.player.displayClientMessage(Component.literal("§7[Left Arm]:"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet left offset <x> <y> <z>"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet left scale <s> OR <sx> <sy> <sz>"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet left rot <x> <y> <z>"), false);
            mc.player.displayClientMessage(Component.literal("§7[Slim Skin (Alex 3px)] (Ratio / Multiplier):"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet slim offset <x> [y] [z]"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet slim scale <mult> OR <sx> <sy> <sz>"), false);
            mc.player.displayClientMessage(Component.literal("§7[General]:"), false);
            mc.player.displayClientMessage(Component.literal("§f  bracelet show"), false);
        }
    }

    private static void showArgs() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            String armType = (mc.player.getSkin().model() == PlayerSkin.Model.SLIM) ? "§bSlim (Alex 3px)" : "§eWide (Steve 4px)";
            String mainHand = mc.player.getMainArm().name();
            mc.player.displayClientMessage(Component.literal("§a=== Bracelet Current Arguments ==="), false);
            mc.player.displayClientMessage(Component.literal("§7Player Arm: " + armType + " | Main Arm: §f" + mainHand), false);
            mc.player.displayClientMessage(Component.literal(String.format("Right: offset=(%.3f, %.3f, %.3f), rot=(%.3f, %.3f, %.3f), scale=(%.3f, %.3f, %.3f)",
                    BraceletConfig.offsetX, BraceletConfig.offsetY, BraceletConfig.offsetZ,
                    BraceletConfig.rotationX, BraceletConfig.rotationY, BraceletConfig.rotationZ,
                    BraceletConfig.scaleX, BraceletConfig.scaleY, BraceletConfig.scaleZ)), false);
            mc.player.displayClientMessage(Component.literal(String.format("Left:  offset=(%.3f, %.3f, %.3f), rot=(%.3f, %.3f, %.3f), scale=(%.3f, %.3f, %.3f)",
                    BraceletConfig.leftOffsetX, BraceletConfig.leftOffsetY, BraceletConfig.leftOffsetZ,
                    BraceletConfig.leftRotationX, BraceletConfig.leftRotationY, BraceletConfig.leftRotationZ,
                    BraceletConfig.leftScaleX, BraceletConfig.leftScaleY, BraceletConfig.leftScaleZ)), false);
            mc.player.displayClientMessage(Component.literal(String.format("Slim:  offset=(%.3f, %.3f, %.3f), scaleMult=(%.3f, %.3f, %.3f)",
                    BraceletConfig.slimOffsetX, BraceletConfig.slimOffsetY, BraceletConfig.slimOffsetZ,
                    BraceletConfig.slimScaleX, BraceletConfig.slimScaleY, BraceletConfig.slimScaleZ)), false);
            mc.player.displayClientMessage(Component.literal("§a=== Arguments END ==="), false);
        }
    }
}
