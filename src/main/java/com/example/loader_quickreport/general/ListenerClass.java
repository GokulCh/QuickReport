package com.example.loader_quickreport.general;

import com.example.loader_quickreport.commands.cmds.notifications;
import com.example.loader_quickreport.commands.cmds.quick;
import com.example.loader_quickreport.notification.TrayIconDemo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.util.*;
import java.util.List;


public class ListenerClass {
    public static boolean isNicked = false;
    public static String nickedName;
    public static boolean tog = false;
    public static boolean curseChecker = false;
    public static boolean output = false;
    public static boolean ignoreChat = false;
    public static boolean noAutoNicked = false;
    public static boolean yesAutoNicked = false;
    public static String realName;
    public static String globalMessage;
    public static String nullFiller;
    public static String unMessage;
    public static String unformattedChat;
    public static String notificationTrigger;
    public static String badWordsI;
    public static int pressTime = 0;
    public  static int count = 0;
    Map<String, String> PlayerAndCurses = new HashMap<String, String>();
        ArrayList<String> generalRudeness = new ArrayList<String>() {
        {
            // General Rudeness
            add("FUCKER");add("BITCH");add("MOTHERFUCKER");add("MOTHER FUCKER");
            add("FUK U");add("FUK YOU");add("CUNT");add("BITCHASS");add("STFU");add("SHUT THE FUCK UP");
            add("FUCK U");add("FUCK YOU");add("FUK ME");add("FUCK ME");add("GTFO");add("GET THE FUCK OUT");add("MILF");add("DILF");add("WANK");add("WANKER");
            add("TWAT");add("SON OF A BITCH");
    }
};
    ArrayList<String> racism = new ArrayList<String>() {
        {
            // Racism
            add("NIGGER");add("NIGGA");add("NIGA");add("NIGAR");
            add("NIGGE");add("NIGR");add("SLAVE");

        }
    };
    ArrayList<String> deathThreats = new ArrayList<String>() {
        {
            // Death Threats
            add("KYS");add("KILL YOURSELF");add("COMMIT SUICIDE");add("JUMP OFF A CLIFF");
        }
    };
    ArrayList<String> inappropriateLanguage = new ArrayList<String>() {
        {
            // Inappropriate Language
            add("DICK");add("PUSSY");add("NUDE");add("PUSSY");add("PENIS");
            add("INTERCOURSE");add("VAGINA");add("COCK");add("TESTICLES");add("CUM");
            add("JIZZ");add("MASTURBATE");add("MASTURBATION");add("JERKING OFF");add("JERK OFF");
            add("PENISHEAD");add("ANUS");add("PORN");
        }
    };
    ArrayList<String> swearingWithDisability = new ArrayList<String>() {
        {
            // Swearing with Disability
            add("SHITHEAD");add("DICKHEAD");add("RETARD");add("FUCKTARD");add("ASSHOLE");
            add("BASTARD");add("CANCER");add("AUTISM");add("AUTIST");add("BRAINDEAD");
        }
    };
    ArrayList<String> advertising = new ArrayList<String>() {
        {
            // General
            add("HTTP");add("HTTPS");add("(.)");add("(.)COM");add("[.]COM");
            add(".ORG");add("(.)ORG");add("[.]ORG");add(".NET");add("(.)NET");add("[.]NET");
            add(".GOV");add("(.)GOV");add("[.]GOV");add("(.)CO");add("[.]CO");add(".US");
            add("(.)US");add("[.]US");
            // YouTube
            add("ON YOUTUBE");add("YOUTUBECOM");add("YOUTUBE COM");add("YOUTUBE(.)COM");add("YOUTUBE[.]COM ");
            add("YOUTU BE");add("YOUTU(.)BE");add("YOUTU[.]BE");add("WATCH?V=");
            // Twitch
            add("TWITCHTV");add("ON TWITCH");add("TWITCH(.)TV");
            add("TWITCH[.]TV");add("TWITCH AT");
            // Discord
            add("DISCORDGG");add("DISCORD GG");add("DISCORD(.)GG");add("DISCORD[.]GG");
            // Facebook
            add("FACEBOOKCOM");add("FACEBOOK COM");add("FACEBOOK(.)COM");add("FACEBOOK[.]COM");
            add("IN FACEBOOK");add("ON FACEBOOK");
            // Instagram
            add("INSTAGRAMCOM");add("INSTAGRAM COM");add("INSTAGRAM(.)COM");add("INSTAGRAM[.]COM");
            add("IN INSTAGRAM");add("IN INSTA");add("ON INSTAGRAM");add("ON INSTA");
            // TikTok
            add("TIKTOKCOM");add("TIKTOK COM");add("TIKTOK(.)COM");
            add("TIKTOK[.]COM");add("IN TIKTOK");add("ON TIKTOK");
        }
    };
    ArrayList<String> badWords = new ArrayList<String>() {
        {
            // General Rudeness
            add("FUCKER");add("BITCH");add("MOTHERFUCKER");add("MOTHER FUCKER");
            add("FUK U");add("FUK YOU");add("CUNT");add("BITCHASS");add("STFU");add("SHUT THE FUCK UP");
            add("FUCK U");add("FUCK YOU");add("FUK ME");add("FUCK ME");add("GTFO");add("GET THE FUCK OUT");add("MILF");add("DILF");add("WANK");add("WANKER");
            add("TWAT");add("SON OF A BITCH");
            // Racism
            add("NIGGER");add("NIGGA");add("NIGA");add("NIGAR");
            add("NIGGE");add("NIGR");add("SLAVE");
            // Death Threats
            add("KYS");add("KILL YOURSELF");add("COMMIT SUICIDE");add("JUMP OFF A CLIFF");
            // Swearing with Disability
            add("SHITHEAD");add("DICKHEAD");add("RETARD");add("FUCKTARD");add("ASSHOLE");
            add("BASTARD");add("CANCER");add("AUTISM");add("AUTIST");add("BRAINDEAD");
            // Inappropriate Language
            add("DICK");add("PUSSY");add("NUDE");add("PUSSY");add("PENIS");
            add("INTERCOURSE");add("VAGINA");add("COCK");add("TESTICLES");add("CUM");
            add("JIZZ");add("MASTURBATE");add("MASTURBATION");add("JERKING OFF");add("JERK OFF");
            add("PENISHEAD");add("ANUS");add("PORN");
            // General
            add("HTTP");add("HTTPS");add("(.)");add("(.)COM");add("[.]COM");
            add(".ORG");add("(.)ORG");add("[.]ORG");add(".NET");add("(.)NET");add("[.]NET");
            add(".GOV");add("(.)GOV");add("[.]GOV");add("(.)CO");add("[.]CO");add(".US");
            add("(.)US");add("[.]US");
            // YouTube
            add("ON YOUTUBE");add("YOUTUBECOM");add("YOUTUBE COM");add("YOUTUBE(.)COM");add("YOUTUBE[.]COM ");
            add("YOUTU BE");add("YOUTU(.)BE");add("YOUTU[.]BE");add("WATCH?V=");
            // Twitch
            add("TWITCHTV");add("ON TWITCH");add("TWITCH(.)TV");
            add("TWITCH[.]TV");add("TWITCH AT");
            // Discord
            add("DISCORDGG");add("DISCORD GG");add("DISCORD(.)GG");add("DISCORD[.]GG");
            // Facebook
            add("FACEBOOKCOM");add("FACEBOOK COM");add("FACEBOOK(.)COM");add("FACEBOOK[.]COM");
            add("IN FACEBOOK");add("ON FACEBOOK");
            // Instagram
            add("INSTAGRAMCOM");add("INSTAGRAM COM");add("INSTAGRAM(.)COM");add("INSTAGRAM[.]COM");
            add("IN INSTAGRAM");add("IN INSTA");add("ON INSTAGRAM");add("ON INSTA");
            // TikTok
            add("TIKTOKCOM");add("TIKTOK COM");add("TIKTOK(.)COM");
            add("TIKTOK[.]COM");add("IN TIKTOK");add("ON TIKTOK");
        }
    };
    ArrayList<String> ignoreWords = new ArrayList<String>() {
        {
            add("JARTEXNETWORK.COM");
            add("MINIGAMES");
            add("\u00A7e\u00A7lJ\u00A76\u00A7lN");
        }
    };
    static ArrayList<String> colorCode = new ArrayList<String>() {
        {
            add("a");add("b");add("c");add("d");add("e");add("f");add("0");add("1");add("2");
            add("3");add("4");add("5");add("6");add("7");add("8");add("9");add("l");add("m");
            add("n");add("o");add("k");add("r");add("e");add("*");
        }
    };


    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        pressTime++;
        if (pressTime == 100) {
            pressTime = 0;
            PlayerAndCurses.clear();
        }
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e) {
        if (!quick.quickToggle) return;
        String message = e.message.getUnformattedText();
        String formatted = e.message.getFormattedText();
        globalMessage = message;
        unMessage = formatted;


        for (int al = 0; al < ignoreWords.size(); al++) {
            if (message.toUpperCase().contains(ignoreWords.get(al)) && message.toUpperCase().contains("\u00BB")) {
                ignoreChat = true;
                break;
            } else {
                ignoreChat = false;
            }
        }
        if (!ignoreChat) {
            // Checks if badwords are in chat messages
            for (int bl = 0; bl < badWords.size(); bl++) {
                if (message.toUpperCase().contains(badWords.get(bl)) && message.toUpperCase().contains("\u00BB")) {
                    curseChecker = true;
                    JartexUploader.curse = true;
                    // Splitting chat before and after "»"
                    final String[] unformattedChatArray = e.message.getUnformattedText().split("\u00BB");
                    String beforeDoubleArrow = unformattedChatArray[0];
                    String afterDoubleArrow = unformattedChatArray[1];
                    String sep2[] = beforeDoubleArrow.split(" ");
                    // Removes all colorCode symbols, aka "§"
                    for (String j : sep2) {
                        if (j.contains("~")) {
                            badWordsI = badWords.get(bl);
                            nickedName = j;
                            unformattedChat = nickedName;
                            for (int c = 0; c < colorCode.size(); c++) {
                                unformattedChat = unformattedChat.replace("\u00A7" + colorCode.get(c), "");
                            }
                            // Reassurance color Code
                            unformattedChat = unformattedChat.replace("\u00A7", "");
                            unformattedChat = unformattedChat.replace("*", "");
                            nickedName = EnumChatFormatting.YELLOW + unformattedChat;
                            isNicked = true;
                        } else isNicked = false;
                    }

                    // Checks HoverEvents for player name, verification process
                    List<IChatComponent> siblings = e.message.getSiblings();
                    if (siblings.size() > 0 && siblings.get(0).getChatStyle().getChatHoverEvent() != null) {
                        for (IChatComponent sibling : siblings) {
                            String[] siblings2 = sibling.toString().split(" ");
                            int loop = 0;
                            for (String j : siblings2) {
                                if (j.contains("Name:")) {
                                    String realname = siblings2[loop + 1];
                                    realname = realname.replaceAll("\n", "");
                                    for (int c = 0; c < colorCode.size(); c++) {
                                        realname = realname.replace("\u00A7" + colorCode.get(c), "");
                                    }
                                    realname = realname.replace("*", "");
                                    realName = EnumChatFormatting.YELLOW + realname;
                                }
                                loop++;
                            }
                        }
                    }
                    // Minigames player name finder & username assurance
                    else {
                        if (sep2[sep2.length - 1].contains("[") || sep2[sep2.length - 1].contains("]")) {
                            nullFiller = sep2[sep2.length - 2];
                        } else {
                            nullFiller = sep2[sep2.length - 1];
                        }
                        realName = EnumChatFormatting.YELLOW + nullFiller;
                    }

                    // Sets category for notification tray
                    for (int dl = 0; dl < generalRudeness.size(); dl++) {
                        if (message.toUpperCase().contains(generalRudeness.get(dl))) {
                            notificationTrigger = "General Rudeness";
                            break;
                        }
                    }
                    for (int el = 0; el < racism.size(); el++) {
                        if (message.toUpperCase().contains(racism.get(el))) {
                            notificationTrigger = "Racism";
                            break;
                        }
                    }
                    for (int fl = 0; fl < inappropriateLanguage.size(); fl++) {
                        if (message.toUpperCase().contains(inappropriateLanguage.get(fl))) {
                            notificationTrigger = "Inappropriate Language";
                            break;
                        }
                    }
                    for (int gl = 0; gl < swearingWithDisability.size(); gl++) {
                        if (message.toUpperCase().contains(swearingWithDisability.get(gl))) {
                            notificationTrigger = "Swearing with Disability";
                            break;
                        }
                    }
                    for (int hl = 0; hl < deathThreats.size(); hl++) {
                        if (message.toUpperCase().contains(deathThreats.get(hl))) {
                            notificationTrigger = "Death Threats";
                            break;
                        }
                    }
                    for (int il = 0; il < advertising.size(); il++) {
                        if (message.toUpperCase().contains(advertising.get(il))) {
                            notificationTrigger = "Advertising";
                            break;
                        }
                    }
                    // Special case: If player has denied username, it will be flagged
                    if (isNicked) {
                        for (int jl = 0; jl < badWords.size(); jl++) {
                            if (unformattedChat.toUpperCase().contains(badWords.get((jl)))) {
                                notificationTrigger = "Inappropriate Username";
                                break;
                            }
                        }
                    }


                    // HashMap
                    if (!PlayerAndCurses.keySet().contains(realName) && JartexUploader.curse) {
                        PlayerAndCurses.put(realName, badWordsI);


                        new Thread() {
                            @Override
                            public void run() {
                                if (isNicked) {
                                    EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
                                    player.sendChatMessage("/realname " + unformattedChat);
                                }
                                // AutoScreenShot off & Curse on
                                if (!JartexUploader.autoss && JartexUploader.curse) {
                                    ChatComponentText upload = new ChatComponentText("\n" + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "   \u2756" + EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + " [UPLOAD]");
                                    upload.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quickreport capture"));

                                    if (!isNicked) {
                                        ChatComponentText text = new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "Possible Report\n" + EnumChatFormatting.GOLD + "    \u00BB Player: " + realName + "\n" + EnumChatFormatting.GOLD + "    \u00BB Trigger: " + EnumChatFormatting.YELLOW + notificationTrigger);
                                        text.appendSibling(upload);
                                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
                                    } else {
                                        output = true;
                                    }

                                }
                                // AutoScreenShot on & Curse on
                                else if (JartexUploader.autoss && JartexUploader.curse) {
                                    if (!isNicked) {
                                        ChatComponentText text = new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "Possible Report\n" + EnumChatFormatting.GOLD + "    \u00BB Player: " + realName + "\n" + EnumChatFormatting.GOLD + "    \u00BB Trigger: " + EnumChatFormatting.YELLOW + notificationTrigger);
                                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
                                        tog = true;
                                    } else {
                                        output = true;
                                        tog = true;
                                    }
                                }
                                // Displays Notifications
                                if (notifications.notificationToggle) {
                                    try {
                                        TrayIconDemo.displayTray();
                                    } catch (AWTException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                                JartexUploader.curse = false;
                                isNicked = false;
                            }
                        }.start();
                        count++;
                    }
                }
            }
        }
    }

    public void nameCheck() {

    }

    // Method that checks chat for character spam.
    @SubscribeEvent
    public void charSpamChecker(ClientChatReceivedEvent e) {
        if (!quick.quickToggle) return;
        String message = e.message.getUnformattedText();
        String formatted = e.message.getFormattedText();
        globalMessage = message;
        unMessage = formatted;


        for (int al = 0; al < ignoreWords.size(); al++) {
            if (message.toUpperCase().contains(ignoreWords.get(al)) && message.toUpperCase().contains("\u00BB")) {
                ignoreChat = true;
            } else {
                ignoreChat = false;
            }
        }
        if (!ignoreChat) {
            // Checks if badwords are in chat messages
            if (!curseChecker && message.toUpperCase().contains("\u00BB")) {
                // Splitting chat before and after "»"
                final String[] unformattedChatArray = e.message.getUnformattedText().split("\u00BB");
                String beforeDoubleArrow = unformattedChatArray[0];
                String afterDoubleArrow = unformattedChatArray[1];
                String sep2[] = beforeDoubleArrow.split(" ");


                // Removes all colorCode symbols, aka "§"
                for (String j : sep2) {
                    if (j.contains("~")) {
                        nickedName = j;
                        unformattedChat = nickedName;
                        for (int c = 0; c < colorCode.size(); c++) {
                            unformattedChat = unformattedChat.replace("\u00A7" + colorCode.get(c), "");
                        }
                        // Reassurance color Code
                        unformattedChat = unformattedChat.replace("\u00A7", "");
                        unformattedChat = unformattedChat.replace("*", "");

                        nickedName = EnumChatFormatting.YELLOW + unformattedChat;
                        isNicked = true;
                    } isNicked = false;
                }

                // Checks HoverEvents for player name, verification process
                List<IChatComponent> siblings = e.message.getSiblings();
                if (siblings.size() > 0 && siblings.get(0).getChatStyle().getChatHoverEvent() != null) {
                    for (IChatComponent sibling : siblings) {
                        String[] siblings2 = sibling.toString().split(" ");
                        int loop = 0;
                        for (String j : siblings2) {
                            if (j.contains("Name:")) {
                                String realname = siblings2[loop + 1];
                                realname = realname.replaceAll("\n", "");
                                for (int c = 0; c < colorCode.size(); c++) {
                                    realname = realname.replace("\u00A7" + colorCode.get(c), "");
                                }
                                realname = realname.replace("*", "");
                                realName = EnumChatFormatting.YELLOW + realname;
                            }
                            loop++;
                        }
                    }
                }
                // Minigames player name finder & username assurance
                else {
                    if (sep2[sep2.length - 1].contains("[") || sep2[sep2.length - 1].contains("]")) {
                        nullFiller = sep2[sep2.length - 2];
                    } else {
                        nullFiller = sep2[sep2.length - 1];
                    }
                    for (int c = 0; c < colorCode.size(); c++) {
                        nullFiller = nullFiller.replace("\u00A7" + colorCode.get(c), "");
                    }
                    nullFiller = nullFiller.replace("*", "");
                    realName = EnumChatFormatting.YELLOW + nullFiller;
                }

                String afterArr = unformattedChatArray[1];
                String[] afterAr = afterArr.split(" ");
                for (int j = 0; j < afterAr.length; j++) {
                    char[] chars = afterAr[j].toCharArray();
                    Map<Character, Integer> map = new HashMap<Character, Integer>();
                    for (char c : chars) {
                        if (map.containsKey(c)) {
                            int counter = map.get(c);
                            map.put(c, ++counter);
                        } else {
                            map.put(c, 1);
                        }
                    }
                    //Print duplicate characters excluding white space
                    for (char c : map.keySet()) {
                        if (map.get(c) > 9 && !Character.isWhitespace(c)) {
                            notificationTrigger = "Character Spam";
                            sendOutput();
                        }
                    }
                }
            } else (curseChecker) = false;
        }
    }

    // Testing Output Method
    public void sendOutputTest() {
        ChatComponentText text = new ChatComponentText("TESTTTT");
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
    }

    // Send Output for CharSpamChecker
    public void sendOutput() {
        new Thread() {
            @Override
            public void run() {
                if (isNicked) {
                    EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
                    player.sendChatMessage("/realname " + unformattedChat);
                }

                // AutoScreenShot off
                if (!JartexUploader.autoss) {
                    ChatComponentText upload = new ChatComponentText("\n" + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "   \u2756" + EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + " [UPLOAD]");
                    upload.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/quickreport capture"));
                    if (!isNicked) {
                        ChatComponentText text = new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "Possible Report\n" + EnumChatFormatting.GOLD + "    \u00BB Player: " + realName + "\n" + EnumChatFormatting.GOLD + "    \u00BB Trigger: " + EnumChatFormatting.YELLOW + notificationTrigger);
                        text.appendSibling(upload);
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
                    } else {
                        output = true;
                    }
                }
                // AutoScreenShot on
                else if (JartexUploader.autoss) {
                    if (!isNicked) {
                        ChatComponentText text = new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "Possible Report\n" + EnumChatFormatting.GOLD + "    \u00BB Player: " + realName + "\n" + EnumChatFormatting.GOLD + "    \u00BB Trigger: " + EnumChatFormatting.YELLOW + notificationTrigger);
                        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
                        tog = true;
                    } else {
                        output = true;
                        tog = true;
                    }
                }
                // Displays Notifications
                if (notifications.notificationToggle) {
                    try {
                        TrayIconDemo.displayTray();
                    } catch (AWTException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                isNicked = false;
            }
        }.start();
    }

    // Method that executes Auto Screenshot Chat Outputs
    @SubscribeEvent
    public void autoScreenShotOutput(ClientChatReceivedEvent e) {
        if (!ignoreChat & output) {
            ChatComponentText upload = new ChatComponentText("\n" + EnumChatFormatting.YELLOW + EnumChatFormatting.BOLD + "   \u2756" + EnumChatFormatting.BLUE + "" + EnumChatFormatting.BOLD + " [UPLOAD]");
            if (e.message.getUnformattedText().contains("is") && e.message.getUnformattedText().contains(nickedName)) {
                if (!JartexUploader.autoss && output) {
                    e.setCanceled(true);
                    ChatComponentText text = new ChatComponentText(EnumChatFormatting.GOLD + "[QuickReport] " + unMessage + "\n" + EnumChatFormatting.GOLD + "" + EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "Possible Report\n" + EnumChatFormatting.GOLD + "    \u00BB Player: " + realName + "\n" + EnumChatFormatting.GOLD + "    \u00BB Trigger: " + EnumChatFormatting.YELLOW + notificationTrigger);
                    text.appendSibling(upload);
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
                    noAutoNicked = false;
                }
                if (JartexUploader.autoss && output) {
                    e.setCanceled(true);
                    ChatComponentText text = new ChatComponentText(EnumChatFormatting.GOLD + "[QuickReport] " + unMessage + "\n" + EnumChatFormatting.GOLD + "" + EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "Possible Report\n" + EnumChatFormatting.GOLD + "    \u00BB Player: " + realName + "\n" + EnumChatFormatting.GOLD + "    \u00BB Trigger: " + EnumChatFormatting.YELLOW + notificationTrigger);
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
                    yesAutoNicked = false;
                }
                output = false;
            }
            isNicked = false;
        }
    }
    @SubscribeEvent
    public void logs(ClientChatReceivedEvent e) {

    }
}
