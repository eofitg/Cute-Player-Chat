package com.eofitg.cuteplayerchat.messaging;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;

public class MessageFile {
    private FileConfiguration config = null;

    public MessageFile(String name) {
        this.config = YamlConfiguration.loadConfiguration((Reader)new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(name)));
    }

    public MessageFile(File file) {
        this.config = YamlConfiguration.loadConfiguration((File)file);
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public String get(String key) {
        return this.config.getString(key);
    }
}
