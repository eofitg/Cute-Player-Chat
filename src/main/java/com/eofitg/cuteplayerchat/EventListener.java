package com.eofitg.cuteplayerchat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {

        String playerMessage = e.getMessage();
        String playerName = e.getPlayer().getName();
        // String playerUuid = e.getPlayer().getUniqueId().toString();

        String sendMessage = playerMessage;
        // String playerData = playerName + '|' + playerUuid;
        // sendMessage = playerMessage + "喵~";
        String suffix = ConfigReader.getSuffix(playerName);
        sendMessage += suffix;
        e.setMessage(sendMessage);

    }
}
