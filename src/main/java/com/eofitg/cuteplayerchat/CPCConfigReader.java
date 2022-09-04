package com.eofitg.cuteplayerchat;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class CPCConfigReader {
    public static FileConfiguration config = CutePlayerChat.getInstance().getConfig();
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
    public static void reset () {
        config = null;
        cmdNames = null;
    }

}
