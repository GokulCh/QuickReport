package com.example.loader_quickreport.util;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

public class Config
{
    public Configuration config;
    public String branding;
    public boolean clipboardEnabled;
    public boolean cameraEffects;
    public boolean copyName;
    
    public Config(final File configFile) {
        FMLCommonHandler.instance().bus().register((Object)this);
        (this.config = new Configuration(configFile)).load();
        this.loadGeneralConfig();
        if (this.config.hasChanged()) {
            this.config.save();
        }
    }

    
    private void loadGeneralConfig() {
        Property prop = this.config.get("general", "Copy To Clipboard", true);
        prop.comment = "Set false to not automatically copy the link to the clipboard";
        this.clipboardEnabled = prop.getBoolean(true);
        prop = this.config.get("general", "Player Name w/ Imgur Link", false);
        prop.comment = "Add offenders name to your Imgur Link";
        this.copyName = prop.getBoolean(true);
    }
    
    @SubscribeEvent
    public void onConfigChange(final ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals("loader_quickreport")) {
            this.loadGeneralConfig();
        }
    }
}
