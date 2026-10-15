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
                        sendFeedback(String.format("Offset updated: %.3f, %.3f, %.3f", x, y, z));
                    } else {
                        sendError("Usage: bracelet offset <x> <y> <z>");
                    }
                } else if ("scale".equals(command)) {
                    if (args.length == 2) {
                        float s = Float.parseFloat(args[1]);
                        BraceletConfig.scale = s;
                        sendFeedback(String.format("Scale updated: %.3f", s));
                    } else {
                        sendError("Usage: bracelet scale <value>");
                    }
                } else if ("rot".equals(command)) {
                    if (args.length == 4) {
                        float x = Float.parseFloat(args[1]);
                        float y = Float.parseFloat(args[2]);
                        float z = Float.parseFloat(args[3]);
                        BraceletConfig.rotationX = x;
                        BraceletConfig.rotationY = y;
                        BraceletConfig.rotationZ = z;
                        sendFeedback(String.format("Rotation updated: %.3f, %.3f, %.3f", x, y, z));
                    } else {
                        sendError("Usage: bracelet rot <degrees>");
                    }
                } else if ("slim".equals(command)) {
                    if (args.length >= 2 && "offset".equals(args[1])) {
                        if (args.length == 3) {
                            float x = Float.parseFloat(args[2]);
                            BraceletConfig.slimOffsetX = x;
                            sendFeedback(String.format("Slim arm X offset updated: %.3f", x));
                        } else {
                            sendError("Usage: bracelet slim offset <x>");
                        }
                    } else if (args.length >= 2 && "scale".equals(args[1])) {
                        if (args.length == 3) {
                            float s = Float.parseFloat(args[2]);
                            BraceletConfig.slimScale = s;
                            sendFeedback(String.format("Slim arm scale updated: %.3f", s));
                        } else {
                            sendError("Usage: bracelet slim scale <value>");
                        }
                    } else {
                        sendError("Usage: bracelet slim offset <x> OR bracelet slim scale <value>");
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
            mc.player.displayClientMessage(Component.literal("§7bracelet offset <x> <y> <z>"), false);
            mc.player.displayClientMessage(Component.literal("§7bracelet scale <value>"), false);
            mc.player.displayClientMessage(Component.literal("§7bracelet rot <X degrees> <Y degrees> <Z degrees>"), false);
            mc.player.displayClientMessage(Component.literal("§7bracelet slim offset <x>"), false);
            mc.player.displayClientMessage(Component.literal("§7bracelet slim scale <value>"), false);
            mc.player.displayClientMessage(Component.literal("§7bracelet show (To show all arguments)"), false);
            mc.player.displayClientMessage(Component.literal("§7=== Example: §fbracelet offset -0.05 0.35 0"), false);
        }
    }

    private static void showArgs() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            String armType = (mc.player.getSkin().model() == PlayerSkin.Model.SLIM) ? "§bSlim (Alex 3px)" : "§eWide (Steve 4px)";
            String mainHand = mc.player.getMainArm().name();
            mc.player.displayClientMessage(Component.literal("§a=== Bracelet Arguments ==="), false);
            mc.player.displayClientMessage(Component.literal("§7Player Arm: " + armType + " | Main Arm: §f" + mainHand), false);
            mc.player.displayClientMessage(Component.literal(String.format("Base Offset: %.3f, %.3f, %.3f", BraceletConfig.offsetX, BraceletConfig.offsetY, BraceletConfig.offsetZ)), false);
            mc.player.displayClientMessage(Component.literal(String.format("Base Scale: %.3f", BraceletConfig.scale)), false);
            mc.player.displayClientMessage(Component.literal(String.format("Base Rotation: %.3f, %.3f, %.3f", BraceletConfig.rotationX, BraceletConfig.rotationY, BraceletConfig.rotationZ)), false);
            mc.player.displayClientMessage(Component.literal(String.format("Slim Arm Offset X: %.3f | Slim Scale: %.3f", BraceletConfig.slimOffsetX, BraceletConfig.slimScale)), false);
            mc.player.displayClientMessage(Component.literal("§a=== Arguments END ==="), false);
        }
    }
}
