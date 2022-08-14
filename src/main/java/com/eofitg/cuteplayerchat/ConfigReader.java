package com.eofitg.cuteplayerchat;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigReader {

    public static FileConfiguration config = CutePlayerChat.instance.getConfig();
    private static final List<String> users = config.getStringList("data.users");
    // public static File file = new File("userCache.yml");
    // public static FileConfiguration userCache = YamlConfiguration.loadConfiguration(file);

    public static String getSuffix(String playerName) {
        if(users.contains(playerName)) {
            String re = config.getString("data." + playerName + ".suffix","");
            String empty = "EMPTY";
            if(re.equals("")) {
                re = "喵";
            }else if (re.equals(empty)) {
                re = "";
            }
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
        if(users.contains(playerName)&&!config.getString("data." + playerName + ".suffix").equals("EMPTY")) {
            String empty = "EMPTY";
            config.set("data." + playerName + ".suffix", empty);
            return true;
        }
        return false;
    }

}
