package com.example.loader_quickreport.commands.cmds;

import com.example.loader_quickreport.general.JartexUploader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class autoss {
    public static void toggle() {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (JartexUploader.autoss) {
            JartexUploader.autoss = false;
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "AutoScreenShot is disabled!"));

        } else {
            JartexUploader.autoss = true;
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "AutoScreenShot is enabled!"));

        }
    }
}