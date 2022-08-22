package com.eofitg.cuteplayerchat;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Objects;

public class ConfigReader {

    public static FileConfiguration config = CutePlayerChat.instance.getConfig();
    public static final List<String> users = config.getStringList("data.users");
    // public static FileConfiguration userCache = YamlConfiguration.loadConfiguration(file);

    public static String getSuffix(String playerName) {
        if(users.contains(playerName)) {
            String re = config.getString("data." + playerName + ".suffix","");
            if(re.equals("")) {
                re = "喵";
            } // 当re == "EMPTY" 表示删除过后缀，为""才是没有设置过
            return re;
        }
        return "";
    }
    public static void setSuffix(String playerName, String suffix) {
        if(!(users.contains(playerName))) {
            users.add(playerName);
        }
        config.set("data.users",users);
        config.set("data." + playerName + ".suffix", suffix);
        CutePlayerChat.instance.saveConfig();
    }
    public static boolean deleteSuffix(String playerName) {
        if(users.contains(playerName)&&!Objects.equals(config.getString("data." + playerName + ".suffix"), "EMPTY")) {
            String empty = "EMPTY";
            config.set("data." + playerName + ".suffix", empty);
            return true;
        }
        return false;
    }

}
