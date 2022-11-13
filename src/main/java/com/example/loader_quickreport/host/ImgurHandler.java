package com.example.loader_quickreport.host;

import com.example.loader_quickreport.general.JartexRunnable;
import com.example.loader_quickreport.general.JartexUploader;
import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ImgurHandler extends ImageHost
{
    public static final String CLIENT_ID = "ff1c42458a0fdd4";
    private JsonObject lastUploadData;
    
    public ImgurHandler() {
        super("imgur");
        this.lastUploadData = null;
    }

    @Override
    public boolean upload(final BufferedImage image, final UPLOAD_METHOD uploadMethod, final String[] description) {
        OutputStreamWriter wr = null;
        BufferedReader in = null;
        HttpURLConnection conn = null;
        ByteArrayOutputStream baos = null;
        try {
            final URL url = new URL("https://api.imgur.com/3/upload.json");
            baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            final byte[] imageInByte = baos.toByteArray();
            final String encoded = Base64.encodeBase64String(imageInByte);
            String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(encoded, "UTF-8");
            if (!Strings.isNullOrEmpty(JartexUploader.config.branding)) {
                data = data + "&" + URLEncoder.encode("description", "UTF-8") + "=" + URLEncoder.encode(JartexUploader.config.branding, "UTF-8");
            }
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            if (JartexRunnable.switchAPI) {
                conn.setRequestProperty("Authorization", "Client-ID ff1c42458a0fdd4");
            }
            else {
                conn.setRequestProperty("Authorization", "Client-ID 8087af05c14d6a4");
            }

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            this.lastUploadData = new JsonParser().parse((Reader)in).getAsJsonObject();
            return true;
        }
        catch (IOException e) {
            JartexUploader.logger.catching((Throwable)e);
            return false;
        }
        catch (JsonParseException e2) {
            JartexUploader.logger.catching((Throwable)e2);
            return false;
        }
        finally {
            IOUtils.close((URLConnection)conn);
            IOUtils.closeQuietly((Writer)wr);
            IOUtils.closeQuietly((Reader)in);
            IOUtils.closeQuietly((OutputStream)baos);
        }
    }
    
    @Override
    public boolean deleteLast() {
        HttpURLConnection conn = null;
        try {
            final URL url = new URL("https://api.imgur.com/3/image/" + this.getDeleteHash());
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            if (JartexRunnable.switchAPI) {
                conn.setRequestProperty("Authorization", "Client-ID ff1c42458a0fdd4");
            }
            else {
                conn.setRequestProperty("Authorization", "Client-ID 8087af05c14d6a4");
            }
            if (conn.getResponseCode() == 200) {
                this.lastUploadData = null;
                return true;
            }
            JartexUploader.logger.error("Failed to delete last image");
            return false;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            IOUtils.close((URLConnection)conn);
        }
    }
    
    @Override
    public String getLink() {
        if (this.lastUploadData != null) {
            final JsonObject dataJson = this.lastUploadData.get("data").getAsJsonObject();
            return "https://imgur.com/" + dataJson.get("id").getAsString();
        }
        return null;
    }
    
    private String getDeleteHash() throws JsonParseException {
        if (this.lastUploadData != null) {
            final JsonObject dataJson = this.lastUploadData.get("data").getAsJsonObject();
            return dataJson.get("deletehash").getAsString();
        }
        return null;
    }
    
    @Override
    public boolean canUploadAnon() {
        return true;
    }
    
    @Override
    public boolean canUploadAccount() {
        return false;
    }
}
