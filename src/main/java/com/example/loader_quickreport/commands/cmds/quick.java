package com.example.loader_quickreport.commands.cmds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class quick {

    public static boolean quickToggle = true;
    public static void toggle() {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (quickToggle) {
            quickToggle = false;
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "QuickReport is disabled!"));

        } else {
            quickToggle = true;
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "QuickReport is enabled!"));
        }
    }
}
