package com.eofitg.cuteplayerchat.data;

import com.eofitg.cuteplayerchat.CPCConfigReader;
import com.eofitg.cuteplayerchat.CutePlayerChat;

import java.util.List;
import java.util.Objects;

public class PrefixReader {
    private static final List<String> users = CPCConfigReader.getUsers("affix.prefix");
    private static final List<String> userNames = CPCConfigReader.getStrL("userNames");
    private static String userPath = "users.affix.prefix";
    private static String userNamePath = "userNames";
    private static final String cachePath = "usercaches.affix.prefix.";

    public static String[] getUsers () {
        return users.toArray(new String[0]);
    }
    public static String[] getUserNames () {
        return userNames.toArray(new String[0]);
    }
    public static String getPrefix (String playerName) {
        if (users.contains(playerName)) {
            String re = CPCConfigReader.getStr(cachePath + playerName);
            if (re.equals("")) {
                re = "喵";
            } // 当re == "EMPTY" 表示删除过后缀，为""才是没有设置过
            return re;
        }
        return "";
    }
    public static void setPrefix (String playerName, String prefix) {
        if (!users.contains(playerName)) {
            users.add(playerName);
        }
        if (!userNames.contains(playerName)) {
            userNames.add(playerName);
        }
        CPCConfigReader.set(userPath,users);
        CPCConfigReader.set(userNamePath, userNames);
        CPCConfigReader.set(cachePath + playerName, prefix);
        CutePlayerChat.getInstance().saveConfig();
    }
    public static boolean deletePrefix(String playerName) {
        if (users.contains(playerName)&&!Objects.equals(CPCConfigReader.getStr(cachePath + playerName), "EMPTY")) {
            String empty = "EMPTY";
            CPCConfigReader.set(cachePath + playerName, empty);
            CutePlayerChat.getInstance().saveConfig();
            return true;
        }
        return false;
    }
}
