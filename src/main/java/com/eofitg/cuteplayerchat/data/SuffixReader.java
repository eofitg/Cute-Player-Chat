package com.eofitg.cuteplayerchat.data;

import com.eofitg.cuteplayerchat.CPCConfigReader;
import com.eofitg.cuteplayerchat.CutePlayerChat;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Objects;

public class SuffixReader {

    public static FileConfiguration config = CPCConfigReader.getConfig();
    public static final List<String> users = CPCConfigReader.getUsers("suffix");
    private static String userPath = "users.suffix";
    private static final String cachePath = "usercaches.suffix.";

    public static String getSuffix(String playerName) {
        if(users.contains(playerName)) {
            String re = config.getString(cachePath + playerName,"");
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
        config.set(userPath,users);
        config.set(cachePath + playerName, suffix);
        CutePlayerChat.getInstance().saveConfig();
    }
    public static boolean deleteSuffix(String playerName) {
        if(users.contains(playerName)&&!Objects.equals(config.getString(cachePath + playerName), "EMPTY")) {
            String empty = "EMPTY";
            config.set(cachePath + playerName, empty);
            CutePlayerChat.getInstance().saveConfig();
            return true;
        }
        return false;
    }

}
