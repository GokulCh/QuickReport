package com.example.loader_quickreport.notification;

import com.example.loader_quickreport.general.ListenerClass;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class TrayIconDemo {
    private static String OS = System.getProperty("os.name").toLowerCase();
    public static boolean IS_WINDOWS = (OS.indexOf("win") >= 0);
    public static boolean IS_MAC = (OS.indexOf("mac") >= 0);

    public static void main(String[] args) throws AWTException {
        if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();

            td.displayTray();
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public static void displayTray() throws AWTException {

       /*if (IS_WINDOWS) {*/
            SystemTray tray = SystemTray.getSystemTray();
            //If the icon is a file
            Image image = Toolkit.getDefaultToolkit().createImage("JartexLogo.png");
            //Alternative (if the icon is on the classpath):
            //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("assets\\JartexLogo.png"));

            TrayIcon trayIcon = new TrayIcon(image, "QuickReport");
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("QuickReport");
            trayIcon.getActionCommand();
            tray.add(trayIcon);
            trayIcon.displayMessage("QuickReport \uD83D\uDEE1 JartexNetwork", "\uD83D\uDCF0 Possible Report\n\uD83D\uDED1 Trigger: " + ListenerClass.notificationTrigger + "\n\n\uD83E\uDEA7 Reportable at JartexNetwork.com", MessageType.INFO);
        /*} else if (IS_MAC) {
            try {
                Runtime.getRuntime().exec(new String[]{"QuickReport || Possible Report"});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            SystemTray tray = SystemTray.getSystemTray();
            //If the icon is a file
            Image image = Toolkit.getDefaultToolkit().createImage("JartexLogo.png");
            //Alternative (if the icon is on the classpath):
            //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("assets\\JartexLogo.png"));

            TrayIcon trayIcon = new TrayIcon(image, "QuickReport");
            //Let the system resize the image if needed
            trayIcon.setImageAutoSize(true);
            //Set tooltip text for the tray icon
            trayIcon.setToolTip("QuickReport");
            trayIcon.getActionCommand();
            tray.add(trayIcon);
            trayIcon.displayMessage("QuickReport \uD83D\uDEE1 JartexNetwork", "\uD83D\uDCF0 Possible Report\n\uD83D\uDED1 Trigger: " + ListenerClass.notificationTrigger + "\n\n\uD83E\uDEA7 Reportable at JartexNetwork.com", MessageType.INFO);
        }*/
    }
}