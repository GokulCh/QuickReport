package com.example.loader_quickreport.commands.general;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class features {
    public static void list() {
        ChatComponentText features = new ChatComponentText("\n" + EnumChatFormatting.WHITE + "\u2615 " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Quick" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "Report" +
                EnumChatFormatting.WHITE + " \u00BB " + EnumChatFormatting.GOLD + "Features" + "\n" +
                EnumChatFormatting.GRAY + " \u25C8 List of features!\n");
        ChatComponentText ft1 = new ChatComponentText(EnumChatFormatting.GOLD + "\n   \u00BB " + EnumChatFormatting.YELLOW + "Detection of rule breakers (Chat) (Not 100% Accurate)");
        ChatComponentText ft2 = new ChatComponentText(EnumChatFormatting.GOLD + "\n   \u00BB " + EnumChatFormatting.YELLOW + "Notify user on possible report");
        ChatComponentText ft3 = new ChatComponentText(EnumChatFormatting.GOLD + "\n   \u00BB " + EnumChatFormatting.YELLOW + "Auto ScreenShot, Auto Imgur Upload, Auto Clipboard Copy");
        ChatComponentText ft4 = new ChatComponentText(EnumChatFormatting.GOLD + "\n   \u00BB " + EnumChatFormatting.YELLOW + "Toggle Mod, Auto ScreenShot, Notifications");
        ChatComponentText ft5 = new ChatComponentText(EnumChatFormatting.GOLD + "\n   \u00BB " + EnumChatFormatting.YELLOW + "Logging Imgur Link Logs w/ Time, Link, Player Name");
        ChatComponentText ft6 = new ChatComponentText(EnumChatFormatting.GOLD + "\n   \u00BB " + EnumChatFormatting.YELLOW + "Manual ScreenShot w/ Auto Upload [ FN + F4 ] Key");
        ChatComponentText ft7 = new ChatComponentText(EnumChatFormatting.GOLD + "\n   \u00BB " + EnumChatFormatting.YELLOW + "Delete Recent ScreenShot (30 sec) [ DELETE ] Key\n");


        features.appendSibling(ft1);
        features.appendSibling(ft2);
        features.appendSibling(ft3);
        features.appendSibling(ft4);
        features.appendSibling(ft5);
        features.appendSibling(ft6);
        features.appendSibling(ft7);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(features);
    }
}
