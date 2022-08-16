package com.eofitg.cuteplayerchat;

import com.eofitg.cuteplayerchat.messaging.MessageFormatter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class CutePlayerChat extends JavaPlugin {

    public static CutePlayerChat instance;
    private MessageFormatter messageFormatter = null;
    public static CutePlayerChat getInstance() {
        return instance;
    }
    public MessageFormatter getMessageFormatter() {
        return this.messageFormatter;
    }
    @Override
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        File messagesFile = new File("plugins" + File.separator + "CutePlayerChat", "messages.yml");
        if (!messagesFile.exists()) {
            this.saveResource("messages.yml", true);
        }
        this.messageFormatter = new MessageFormatter();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("cuteplayerchat")).setExecutor(new CommandHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("cuteplayerchat:cuteplayerchat")).setExecutor(new CommandHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("getsuff")).setExecutor(new CommandHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("cuteplayerchat:getsuff")).setExecutor(new CommandHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("setsuff")).setExecutor(new CommandHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("cuteplayerchat:setsuff")).setExecutor(new CommandHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("delsuff")).setExecutor(new CommandHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("cuteplayerchat:delsuff")).setExecutor(new CommandHandler());
        // Bukkit.getServer().getPluginManager().registerEvent(this, this);
        // saveResource("userCache.yml", false);
        getLogger().info("CPC插件已经成功加载！");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
        messageFormatter = null;
        saveConfig();
        getLogger().info("CPC插件已经成功卸载！");
    }


}
