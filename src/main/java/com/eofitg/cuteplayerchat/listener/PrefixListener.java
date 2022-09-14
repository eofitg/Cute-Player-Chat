package com.eofitg.cuteplayerchat.listener;

import com.eofitg.cuteplayerchat.data.PrefixReader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PrefixListener implements Listener {
    @EventHandler
    public void onPlayerChat (AsyncPlayerChatEvent e) {
        String playerMessage = e.getMessage();
        String playerName = e.getPlayer().getName();

        String sendMessage = playerMessage;
        String prefix = PrefixReader.getPrefix(playerName);
        if(prefix.equals("EMPTY")) { // 该玩家存在
            prefix = "";
        }
        sendMessage = prefix + sendMessage;
        e.setMessage(sendMessage);

    }
}
