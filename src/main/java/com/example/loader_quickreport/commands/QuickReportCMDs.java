package com.example.loader_quickreport.commands;

import com.example.loader_quickreport.commands.cmds.*;
import com.example.loader_quickreport.commands.general.contact;
import com.example.loader_quickreport.commands.general.features;
import com.example.loader_quickreport.commands.general.help;
import com.example.loader_quickreport.general.JartexHandler;
import com.example.loader_quickreport.general.JartexRunnable;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class QuickReportCMDs implements ICommand {
    public static final String COMMAND_NAME = "quickreport";
    private static final String COMMAND_USAGE = EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + "/quickreport <help / commands / features / contact>\n" + EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + "/quickreport <toggle / notifications / autoss / logs /\n" + EnumChatFormatting.YELLOW + "       capture >";
    private static final List<String> ALIASES = ImmutableList.of("qr");
    private static final List<String> TABS = Arrays.asList("help", "commands", "features", "contact", "toggle", "notifications", "autoss", "logs", "capture");

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }


    @Override
    public String getCommandUsage(ICommandSender sender) {
        return COMMAND_USAGE;
    }

    @Override
    public List<String> getCommandAliases() {
        return ALIASES;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        switch (args.length) {
            case 0:
                help.list();
                break;
            case 1:
                if (Objects.equals(args[0], "help")) {
                    help.list();
                    break;
                } else if (Objects.equals(args[0], "commands")) {
                    commands.list();
                    break;
                } else if (Objects.equals(args[0], "features")) {
                    features.list();
                    break;
                } else if (Objects.equals(args[0], "contact")) {
                    contact.list();
                    break;
                } else if (Objects.equals(args[0], "toggle")) {
                    quick.toggle();
                    break;
                } else if (Objects.equals(args[0], "autoss")) {
                    autoss.toggle();
                    break;
                } else if (Objects.equals(args[0], "notifications")) {
                    notifications.toggle();
                    break;
                } else if (Objects.equals(args[0], "logs")) {
                    if (JartexRunnable.quickLog.size() == 0) {
                        ChatComponentText quicklog = new ChatComponentText("\n" + EnumChatFormatting.WHITE + "\u2615 " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Quick" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "Report" +
                                EnumChatFormatting.WHITE + " \u00BB " + EnumChatFormatting.GOLD + "Logs" + "\n" +
                                EnumChatFormatting.GRAY + " \u25C8 No pending reports!\n");
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(quicklog);
                    } else {
                        sendOutput();
                    }
                    break;
                } else if (Objects.equals(args[0], "capture")) {
                    JartexHandler.processScreenshot(null);
                    break;
                } else {
                    ChatComponentText error = new ChatComponentText("\n" + EnumChatFormatting.WHITE + "\u2615 " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Quick" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "Report" +
                            EnumChatFormatting.WHITE + " \u00BB " + EnumChatFormatting.GOLD + "Input Error" + "\n" +
                            EnumChatFormatting.GRAY + " \u25C8 Incorrect command usage!\n\n" + getCommandUsage(sender));
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(error);
                    break;
                }
            case 2:
                if (args[1].matches("[a-zA-Z]")) {
                    ChatComponentText error = new ChatComponentText("\n" + EnumChatFormatting.WHITE + "\u2615 " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Quick" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "Report" +
                            EnumChatFormatting.WHITE + " \u00BB " + EnumChatFormatting.GOLD + "Input Error" + "\n" +
                            EnumChatFormatting.GRAY + " \u25C8 Incorrect command usage!\n\n" + EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + "(Ex) /quickreport logs 5\n" + EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + "Prints the 5 most recent logs");
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(error);
                    break;
                }
                if (JartexRunnable.quickLog.size() == 0) {
                    ChatComponentText quicklog = new ChatComponentText("\n" + EnumChatFormatting.WHITE + "\u2615 " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Quick" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "Report" +
                            EnumChatFormatting.WHITE + " \u00BB " + EnumChatFormatting.GOLD + "Logs" + "\n" +
                            EnumChatFormatting.GRAY + " \u25C8 No pending reports!\n");
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(quicklog);
                    break;
                }
                int n = Integer.parseInt(args[1]);
                if (n > JartexRunnable.quickLog.size()) {
                    ChatComponentText error = new ChatComponentText("\n" + EnumChatFormatting.WHITE + "\u2615 " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Quick" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "Report" +
                            EnumChatFormatting.WHITE + " \u00BB " + EnumChatFormatting.GOLD + "Input Error" + "\n" +
                            EnumChatFormatting.GRAY + " \u25C8 Requesting more then the reports logged!\n\n" + EnumChatFormatting.GOLD + "   \u00BB " + EnumChatFormatting.YELLOW + "Currently logged: " + EnumChatFormatting.GOLD + "[" + EnumChatFormatting.YELLOW + JartexRunnable.quickLog.size() + EnumChatFormatting.GOLD + "]");
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(error);
                    break;
                }
                if (n % 1 == 0) {
                    ChatComponentText page = new ChatComponentText("\n" + EnumChatFormatting.WHITE + "\u2615 " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Quick" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "Report" +
                            EnumChatFormatting.WHITE + " \u00BB " + EnumChatFormatting.GOLD + "Logs" + "\n" +
                            EnumChatFormatting.GRAY + " \u25C8 Log of" + EnumChatFormatting.GOLD + " [" + EnumChatFormatting.YELLOW + JartexRunnable.quickLog.size() + EnumChatFormatting.GOLD + "] " + EnumChatFormatting.GRAY + "reported players! Requested " + EnumChatFormatting.GOLD + " [" + EnumChatFormatting.YELLOW + n + EnumChatFormatting.GOLD + "]\n\n");
                    int counter = 0;
                    for (String i : JartexRunnable.quickLog.keySet()) {
                        if (counter == n) break;
                        ChatComponentText upload = new ChatComponentText("    " + i + EnumChatFormatting.GOLD + "\u00BB " + EnumChatFormatting.WHITE + JartexRunnable.quickLog.get(i) + "\n");
                        upload.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, JartexRunnable.quickLog.get(i)));
                        page.appendSibling(upload);
                        counter++;
                    }
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(page);
                    break;
                }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return TABS;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    public void sendOutput() {
        ChatComponentText page = new ChatComponentText("\n" + EnumChatFormatting.WHITE + "\u2615 " + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "Quick" + EnumChatFormatting.GOLD + EnumChatFormatting.BOLD + "Report" +
                EnumChatFormatting.WHITE + " \u00BB " + EnumChatFormatting.GOLD + "Logs" + "\n" +
                EnumChatFormatting.GRAY + " \u25C8 Log of" + EnumChatFormatting.GOLD + " [" + EnumChatFormatting.YELLOW + JartexRunnable.quickLog.size() + EnumChatFormatting.GOLD + "] " + EnumChatFormatting.GRAY + "reported players! Default display" + EnumChatFormatting.GOLD + " [" + EnumChatFormatting.YELLOW + "10" + EnumChatFormatting.GOLD + "]\n" +
                EnumChatFormatting.GRAY + " \u25C8 Specific quantity: /quickreport logs <num>\n\n");
        int counter = 0;
        for (String i : JartexRunnable.quickLog.keySet()) {
            if (counter == 10) break;
            ChatComponentText upload = new ChatComponentText("    " + i + EnumChatFormatting.GOLD + "\u00BB " + EnumChatFormatting.WHITE + JartexRunnable.quickLog.get(i) + "\n");
            upload.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, JartexRunnable.quickLog.get(i)));
            page.appendSibling(upload);
            counter++;
        }
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(page);
    }
}