package com.example.loader_quickreport.general;


import com.example.loader_quickreport.host.ImageHost;
import com.example.loader_quickreport.util.ImageUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.TreeMap;
import java.util.logging.Filter;

public class JartexRunnable implements Runnable {
    public static int switchAPICount = 0;
    public static boolean switchAPI = false;
    public static String getImgurLink;
    public static TreeMap<String, String> quickLog = new TreeMap<String, String>(Collections.reverseOrder());
    public static TreeMap<String, String> quickLogCopy = new TreeMap<String, String>(Collections.reverseOrder());


    private Filter filter;
    private String host;
    private BufferedImage screenshot;
    private boolean processing;


    public JartexRunnable(final Filter filter, final String host, final BufferedImage screenshot) {
        this.screenshot = screenshot;
        this.filter = filter;
        this.host = host;
    }

    public boolean isProcessing() {
        return this.processing;
    }

    public BufferedImage get() {
        return this.processing ? null : this.screenshot;
    }

    @Override
    public void run() {
        this.processing = true;
        if (this.filter != null) {
            this.screenshot = ImageUtil.processImageWithFilter(this.screenshot, this.filter);
        }
        JartexHandler.saveScreenshot(this.screenshot);
        if (this.host.equals("imgur")) {
            try {
                final ImageHost imageHost = ImageHost.imageHosts.get("imgur");
                if (imageHost.upload(this.screenshot, ImageHost.UPLOAD_METHOD.ANON, new String[0])) {
                    final String link = imageHost.getLink();
                    getImgurLink = link;

                    switchAPICount++;
                    if (switchAPICount >= 10) {
                        if (switchAPI) {
                            switchAPI = false;

                        } else {
                            switchAPI = true;
                        }
                        switchAPICount = 0;
                    }

                    ChatComponentText upload = new ChatComponentText(EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + " [COPY]");
                    upload.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, link));
                    ChatComponentText report = new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + " [" + EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "REPORT" + EnumChatFormatting.DARK_RED + EnumChatFormatting.BOLD + "]");
                    report.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://jartexnetwork.com/form/9/select"));
                    ChatComponentText text = new ChatComponentText(EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "Copied to Clipboard!");
                    text.appendSibling((IChatComponent) report);
                    text.appendSibling((IChatComponent) upload);
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);



                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime localTime = LocalTime.now();
                    System.out.println(dtf.format(localTime));

                        String replaceColor = ListenerClass.realName;
                        for (int c = 0; c < ListenerClass.colorCode.size(); c++) {
                            replaceColor = replaceColor.replace("\u00A7" + ListenerClass.colorCode.get(c), "");
                        }

                        if (JartexUploader.config.copyName && JartexUploader.autoss) {
                            JartexHandler.addToClipboard(link + " \u2756 " + replaceColor);
                        } else {
                            JartexHandler.addToClipboard(link);
                        }

                    String replace = ListenerClass.realName;
                    for (int c = 0; c < ListenerClass.colorCode.size(); c++) {
                        replace = replace.replace("\u00A7" + ListenerClass.colorCode.get(c), "");
                    }
                    replace = replace.replace("*", "");

                    if (JartexUploader.autoss) quickLog.put(EnumChatFormatting.DARK_GRAY + "[" + EnumChatFormatting.GRAY + localTime + EnumChatFormatting.DARK_GRAY + "] ", link + " " + replace);
                    else quickLog.put(EnumChatFormatting.DARK_GRAY + "[" + EnumChatFormatting.GRAY + localTime + EnumChatFormatting.DARK_GRAY + "] ", link);

                }
            } catch (Exception e) {
                JartexUploader.logger.catching((Throwable) e);
            }
        }
        this.processing = false;
    }
}


