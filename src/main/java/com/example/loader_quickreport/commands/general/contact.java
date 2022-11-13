package com.example.loader_quickreport.commands.general;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class contact  {
    public static void list() {
        ChatComponentText contact = new ChatComponentText("\n" + EnumChatFormatting.WHITE + "\u2615 " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Quick" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "Report" +
        EnumChatFormatting.WHITE + " \u00BB " + EnumChatFormatting.GOLD + "Contact" + "\n" +
        EnumChatFormatting.GRAY + " \u25C8 Contact information!\n\n");
        ChatComponentText made = new ChatComponentText(EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW  + "Made By mopmop2\n\n");
        ChatComponentText discord = new ChatComponentText(EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "[Discord] ");
        ChatComponentText discordserv = new ChatComponentText(EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "[Discord Server] ");
        ChatComponentText forums = new ChatComponentText(EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "[Forums] ");

        discord.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "_Wiggels#2390"));
        discordserv.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/kF7UqbDrf2"));
        forums.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://jartexnetwork.com/members/mopmop.3177/"));
        contact.appendSibling(made);
        contact.appendSibling(discord);
        contact.appendSibling(discordserv);
        contact.appendSibling(forums);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(contact);
    }


}