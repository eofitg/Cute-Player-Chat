package com.eofitg.cuteplayerchat.listener;

import com.eofitg.cuteplayerchat.data.SuffixReader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class SuffixListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        String playerMessage = e.getMessage();
        String playerName = e.getPlayer().getName();
        // String playerUuid = e.getPlayer().getUniqueId().toString();

        String sendMessage = playerMessage;
        String suffix = SuffixReader.getSuffix(playerName);
        if(suffix.equals("EMPTY")) {
            suffix = "";
        }
        sendMessage += suffix;
        e.setMessage(sendMessage);

    }
}
