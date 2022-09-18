package com.eofitg.cuteplayerchat.messaging;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStreamReader;

public class MessageFile {
    private final FileConfiguration config;

    public MessageFile (String name) {
        this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(name)));
    }

    public MessageFile (File file) {
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig () {
        return this.config;
    }

    public String get (String key) {
        return this.config.getString(key);
    }
}
