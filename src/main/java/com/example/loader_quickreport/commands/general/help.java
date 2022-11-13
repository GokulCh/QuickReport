package com.example.loader_quickreport.commands.general;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class help  {
    public static void list() {
        ChatComponentText help = new ChatComponentText("\n" + EnumChatFormatting.WHITE + "\u2615 " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Quick" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "Report" +
        EnumChatFormatting.WHITE + " \u00BB " + EnumChatFormatting.GOLD + "Help" + "\n" +
        EnumChatFormatting.GRAY + " \u25C8 Click the buttons below continue!\n" + EnumChatFormatting.GRAY + " \u25C8 Usage: /quickreport <..> & /qr <..> \n\n");
        ChatComponentText modInfo = new ChatComponentText(EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + "QuickReport, a mod with the version compatibility of 1.8.9,\n" + EnumChatFormatting.YELLOW + "      integrates a system, in which, players will assisted while\n"  + EnumChatFormatting.YELLOW + "      reporting\n\n");
        ChatComponentText toggleInfo = new ChatComponentText(EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "[Commands] ");
        ChatComponentText featuresInfo = new ChatComponentText(EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "[Features] ");
        ChatComponentText contactInfo = new ChatComponentText(EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "[Contact] \n");
        toggleInfo.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quickreport commands"));
        featuresInfo.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quickreport features"));
        contactInfo.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quickreport contact"));
        help.appendSibling(modInfo);
        help.appendSibling(toggleInfo);
        help.appendSibling(featuresInfo);
        help.appendSibling(contactInfo);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(help);
    }
}