package com.stili.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Simple configuration loader for game settings.
 * This class only reads properties from config.properties - it doesn't manage state.
 */
public class Config {
    private static final String CONFIG_FILE = "config.properties";
    private static Config instance;
    
    private final Properties properties;
    
    private Config() {
        properties = new Properties();
        
        try {
            FileHandle configFile = Gdx.files.internal(CONFIG_FILE);
            if (configFile.exists()) {
                InputStream input = configFile.read();
                properties.load(input);
                input.close();
                Gdx.app.log("Config", "Loaded configuration from " + CONFIG_FILE);
            } else {
                Gdx.app.log("Config", "Config file not found, using defaults");
            }
        } catch (IOException e) {
            Gdx.app.error("Config", "Error loading config file", e);
        }
    }
    
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
    
    // General property getters
    public String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    public boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(properties.getProperty(key, String.valueOf(defaultValue)));
    }
    
    public int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    public float getFloat(String key, float defaultValue) {
        try {
            return Float.parseFloat(properties.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    // Specific getters for known properties
    public boolean isDevModeAllowed() {
        return getBoolean("dev_mode_allowed", false);
    }
}

