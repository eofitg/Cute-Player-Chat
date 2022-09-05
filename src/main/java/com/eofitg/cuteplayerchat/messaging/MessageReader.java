package com.eofitg.cuteplayerchat.messaging;

import com.eofitg.cuteplayerchat.CutePlayerChat;
import org.bukkit.ChatColor;

public class MessageReader {
    public static String get (String key) {
        return ChatColor.translateAlternateColorCodes('&', MessageReader.getString(key));
    }
    public static String getString (String key) {
        return CutePlayerChat.getMessageFile().get(key);
    }

}
