package com.eofitg.cuteplayerchat;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class CPCConfigReader {
    private static FileConfiguration config = CutePlayerChat.getInstance().getConfig();
    private static List<String> cmdNames = config.getStringList("commandNames");

    public static FileConfiguration getConfig () {
        return config;
    }
    public static List<String> getUsers (String opeName) {
        return config.getStringList("users." + opeName);
    }
    public static List<String> getCmdNames () {
        return cmdNames;
    }
    public static List<String> getCmdList (String commandName) {
        return config.getStringList("commands." + commandName);
    }
    public static String getStr (String key) {
        return config.getString(key, "");
    }
    public static List<String> getStrL (String key) {
        return config.getStringList(key);
    }
    public static void set (String key, Object value) {
        config.set(key, value);
    }
    public static void reset () {
        config = null;
        cmdNames = null;
    }

}
