package com.example.loader_quickreport.commands.cmds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class notifications {

    public static boolean notificationToggle = true;

    public static void toggle() {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (notificationToggle) {
            notificationToggle = false;
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "PC notifications is disabled!"));

        } else {
            notificationToggle = true;
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "PC notifications is enabled!"));
        }
    }
}