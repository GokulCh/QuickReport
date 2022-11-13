package com.example.loader_quickreport.general;

import com.example.loader_quickreport.commands.QuickReportCMDs;
import com.example.loader_quickreport.commands.cmds.capture;
import com.example.loader_quickreport.host.ImageHost;
import com.example.loader_quickreport.host.ImgurHandler;
import com.example.loader_quickreport.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

@Mod(modid = "loader_quickreport",
        name = "QuickReport || JartexNetwork",
        version = "1.8.9",
        guiFactory = "com.example.loader_quickreport.JartexUploaderGuiFactory",
        acceptedMinecraftVersions = "[1.8.9]"
)
public class JartexUploader
{
    public static final String MOD_ID = "loader_quickreport";
    public static final Logger logger;
    public static KeyBinding screenshotQuickKey;
    public static KeyBinding screenshotDeleteKey;
    public static long deleteLastConfirm;
    public static ImageHost imgur;
    public static Config config;

    public static boolean autoss = false;
    public static boolean curse = false;
    public static int pressTime = 0;



    @Mod.Instance(MOD_ID)
    public static JartexUploader INSTANCE;



    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ListenerClass());
        System.out.println("Registered Event");

        if (event.getSide().isClient()) {
            JartexUploader.config = new Config(event.getSuggestedConfigurationFile());
            MinecraftForge.EVENT_BUS.register((Object)this);
            FMLCommonHandler.instance().bus().register((Object)this);
            JartexUploader.screenshotQuickKey = new KeyBinding("UPLOAD", 62, "key.categories.misc");
            JartexUploader.screenshotDeleteKey = new KeyBinding("DELETE", 211, "key.categories.misc");
        }
        else {
            FMLLog.bigWarning("You are attempting to load QuickReport on the server. This is a silly thing to do", new Object[0]);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onKeyEvent(final InputEvent.KeyInputEvent event) {
        final int keyCode = Keyboard.getEventKey();
        if (!Keyboard.isRepeatEvent() && Keyboard.getEventKeyState()) {
            if (keyCode == JartexUploader.screenshotQuickKey.getKeyCode()) {
                if (Keyboard.isKeyDown(42)) {
                    FMLClientHandler.instance().getClient().gameSettings.hideGUI = true;
                }
                JartexHandler.processScreenshot(null);
                if (Keyboard.isKeyDown(42)) {
                    FMLClientHandler.instance().getClient().gameSettings.hideGUI = false;
                }
            }
            else if (keyCode == JartexUploader.screenshotDeleteKey.getKeyCode()) {
                if (JartexUploader.deleteLastConfirm == 0L) {
                    Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage((IChatComponent)new ChatComponentTranslation(EnumChatFormatting.GOLD + "[QuickReport] " + EnumChatFormatting.YELLOW + "Deleted Recent ScreenShot", new Object[] { Keyboard.getKeyName(JartexUploader.screenshotDeleteKey.getKeyCode()) }));
                    JartexUploader.deleteLastConfirm = System.currentTimeMillis();
                }
                else if (JartexUploader.deleteLastConfirm - System.currentTimeMillis() < 30000L) {
                    JartexUploader.deleteLastConfirm = 0L;
                }
            }
        }
    }



    static {
        logger = LogManager.getLogger();
        JartexUploader.deleteLastConfirm = 0L;
        JartexUploader.imgur = new ImgurHandler();
    }



    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onKeyPress(final InputEvent.KeyInputEvent event){

    }


    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e) throws InterruptedException {
       /* if (ListenerClass.tog) {
            quickCaptureCommand.p();
            ListenerClass.tog = false;
        }*/
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ClientTickEvent event) {
        pressTime++;

        ChatComponentText text = new ChatComponentText(String.valueOf(pressTime));
        //Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(text);
        if (pressTime == 100) {
            if (ListenerClass.tog) {
                capture.screen();
                ListenerClass.tog = false;
            }
            pressTime = 0;
        }
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new QuickReportCMDs());
    }

}
