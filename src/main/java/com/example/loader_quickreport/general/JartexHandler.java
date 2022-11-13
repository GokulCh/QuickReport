package com.example.loader_quickreport.general;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.shader.Framebuffer;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.IntBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Filter;

public class JartexHandler
{
    private static IntBuffer pixelBuffer;
    private static int[] pixelData;
    private static final File minecraftDir;
    private static final DateFormat dateFormat;
    
    @NotNull
    public static BufferedImage takeScreenshot(int width, int height, final Framebuffer framebuffer) {
        if (OpenGlHelper.isFramebufferEnabled()) {
            width = framebuffer.framebufferTextureWidth;
            height = framebuffer.framebufferTextureHeight;
        }
        final int pixels = width * height;
        if (JartexHandler.pixelBuffer == null || JartexHandler.pixelBuffer.capacity() < pixels) {
            JartexHandler.pixelBuffer = BufferUtils.createIntBuffer(pixels);
            JartexHandler.pixelData = new int[pixels];
        }
        GL11.glPixelStorei(3333, 1);
        GL11.glPixelStorei(3317, 1);
        JartexHandler.pixelBuffer.clear();
        if (OpenGlHelper.isFramebufferEnabled()) {
            GL11.glBindTexture(3553, framebuffer.framebufferTexture);
            GL11.glGetTexImage(3553, 0, 32993, 33639, JartexHandler.pixelBuffer);
        }
        else {
            GL11.glReadPixels(0, 0, width, height, 32993, 33639, JartexHandler.pixelBuffer);
        }
        JartexHandler.pixelBuffer.get(JartexHandler.pixelData);
        TextureUtil.processPixelValues(JartexHandler.pixelData, width, height);
        final BufferedImage bufferedimage = new BufferedImage(width, height, 1);
        bufferedimage.setRGB(0, 0, width, height, JartexHandler.pixelData, 0, width);
        return bufferedimage;
    }

    public static void processScreenshot(final Filter filter) {
        final Minecraft mc = Minecraft.getMinecraft();
        final Thread t = new Thread(new JartexRunnable(filter, "imgur", takeScreenshot(mc.displayWidth, mc.displayHeight, mc.getFramebuffer())));
        t.start();
    }
    
    public static void saveScreenshot(final BufferedImage screenshot) {
        final File saveDir = new File(JartexHandler.minecraftDir, "screenshots");
        saveDir.mkdir();
        final File screenshotFile = getAvailableSaveName(saveDir);
        try {
            ImageIO.write(screenshot, "PNG", screenshotFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static File getAvailableSaveName(final File file) {
        final String s = JartexHandler.dateFormat.format(new Date());
        for (int i = 1; i < 10; ++i) {
            final File file2 = new File(file, s + ((i == 1) ? "" : ("_" + i)) + ".png");
            if (!file2.exists()) {
                return file2;
            }
        }
        return null;
    }
    
    public static void addToClipboard(final String string) {
        final StringSelection selection = new StringSelection(string);
        final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }



    static {
        minecraftDir = Minecraft.getMinecraft().mcDataDir;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
    }
}
