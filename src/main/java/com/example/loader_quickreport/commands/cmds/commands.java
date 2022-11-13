package com.example.loader_quickreport.commands.cmds;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class commands {
    public static void list() {

        ChatComponentText commands = new ChatComponentText("\n" + EnumChatFormatting.WHITE + "\u2615 " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Quick" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "Report" +
                EnumChatFormatting.WHITE + " \u00BB " + EnumChatFormatting.GOLD + "Commands" + "\n" +
                EnumChatFormatting.GRAY + " \u25C8 List of commands!\n\n");
        ChatComponentText cmd1 = new ChatComponentText("" + EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + "QuickReport on/off" + EnumChatFormatting.GREEN +EnumChatFormatting.BOLD + "   [Toggle]\n");
        ChatComponentText cmd2 = new ChatComponentText("" + EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + "Toggle on/off notification tray" + EnumChatFormatting.GREEN +EnumChatFormatting.BOLD + "   [Toggle]\n");
        ChatComponentText cmd3 = new ChatComponentText("" + EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + "Toggle auto screen shot" + EnumChatFormatting.GREEN +EnumChatFormatting.BOLD + "   [Toggle]\n");
        ChatComponentText cmd4 = new ChatComponentText("" + EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + "Display screenshot logs" + EnumChatFormatting.GREEN +EnumChatFormatting.BOLD + "   [Show]\n");
        cmd1.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quickreport toggle"));
        cmd2.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quickreport notifications"));
        cmd3.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quickreport autoss"));
        cmd4.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quickreport logs"));

        commands.appendSibling(cmd1);
        commands.appendSibling(cmd2);
        commands.appendSibling(cmd3);
        commands.appendSibling(cmd4);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(commands);
    }
}
