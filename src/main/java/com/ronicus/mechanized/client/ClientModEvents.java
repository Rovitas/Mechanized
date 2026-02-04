package com.ronicus.mechanized.client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.ronicus.mechanized.Mechanized.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientModEvents{
    @SubscribeEvent
    public static void onClientChat(ClientChatEvent event) {
        String msg = event.getOriginalMessage();
        if (msg.startsWith("bracelet ")) {
            event.setCanceled(true);

            String[] args = msg.substring(9).trim().split("\\s+");

            try {
                if (args.length == 0) {
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
                } else if ("help".equals(command)) {
                    sendHelp();
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
            mc.player.displayClientMessage(Component.literal(message), false);
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
            mc.player.displayClientMessage(Component.literal("§7bracelet show (To show all arguments)"), false);
            mc.player.displayClientMessage(Component.literal("§7=== Example: §f bracelet offset 0 -0.15 -0.1"), false);
        }
    }


    private static void showArgs() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.player.displayClientMessage(Component.literal("§a=== Bracelet Arguments ==="), false);
            mc.player.displayClientMessage(Component.literal(String.format("Offset now: %.3f, %.3f, %.3f", BraceletConfig.offsetX, BraceletConfig.offsetY, BraceletConfig.offsetZ)), false);
            mc.player.displayClientMessage(Component.literal(String.format("Scale now: %.3f", BraceletConfig.scale)), false);
            mc.player.displayClientMessage(Component.literal(String.format("Rotation now: %.3f, %.3f, %.3f", BraceletConfig.rotationX, BraceletConfig.rotationY, BraceletConfig.rotationZ)), false);
            mc.player.displayClientMessage(Component.literal("§a=== Arguments END ==="), false);
        }
    }
}
