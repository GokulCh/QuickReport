package com.example.loader_quickreport.util;

import java.awt.image.*;
import java.awt.image.FilteredImageSource;
import java.util.logging.*;

import org.lwjgl.util.vector.*;

public class ImageUtil
{
    public static BufferedImage processImageWithFilter(final BufferedImage image, final Filter filter) {
        for (int x = 0; x < image.getWidth(); ++x) {
            for (int y = 0; y < image.getHeight(); ++y) {
                final int col = image.getRGB(x, y);
                final Vector4f in = new Vector4f(((col & 0xFF0000) >> 16) / 256.0f, ((col & 0xFF00) >> 8) / 256.0f, (col & 0xFF) / 256.0f, 1.0f);
                final Vector2f texel = new Vector2f(x / (float)image.getWidth(), y / (float)image.getHeight());
                //final Vector4f out = Filter.process(in, texel, image);
                //image.setRGB(x, y, 0xFF000000 | ((int)(out.x * 255.0f) & 0xFF) << 16 | ((int)(out.y * 255.0f) & 0xFF) << 8 | ((int)(out.z * 255.0f) & 0xFF));
            }
        }
        return image;
    }
}
