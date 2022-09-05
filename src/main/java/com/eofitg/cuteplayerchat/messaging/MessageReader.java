package com.eofitg.cuteplayerchat.messaging;

import com.eofitg.cuteplayerchat.CutePlayerChat;
import org.bukkit.ChatColor;

public class MessageReader {
    public static String get (String key) {
        return ChatColor.translateAlternateColorCodes('&', CutePlayerChat.getMessageFile().get(key));
    }
}
