package com.eofitg.cuteplayerchat.listener;

import com.eofitg.cuteplayerchat.CPCConfigReader;
import com.eofitg.cuteplayerchat.CutePlayerChat;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.temporal.ChronoUnit;
import java.util.List;

public class PlayerListener implements Listener {
    private static String userNamePath = "userNames";
    @EventHandler
    public void onJoin (PlayerJoinEvent e) {
        // 新玩家加入时为该玩家申请存储空间并添加到List
        String playerName =  e.getPlayer().getName();
        List<String> userNames = CPCConfigReader.getStrL("userNames");
        if (!userNames.contains(playerName)) {
            userNames.add(playerName);
        }
        CPCConfigReader.set(userNamePath, userNames);
        CutePlayerChat.getInstance().saveConfig();
    }
}
