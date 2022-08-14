package com.eofitg.cuteplayerchat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CutePlayerChat extends JavaPlugin {

    public static JavaPlugin instance;
    @Override
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("setsuff")).setExecutor(new CommandHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("cuteplayerchat:setsuff")).setExecutor(new CommandHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("delsuff")).setExecutor(new CommandHandler());
        Objects.requireNonNull(Bukkit.getPluginCommand("cuteplayerchat:delsuff")).setExecutor(new CommandHandler());
        instance = this;
        // Bukkit.getServer().getPluginManager().registerEvent(this, this);
        // saveResource("userCache.yml", false);
        getLogger().info("CPC插件已经成功加载！");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
        getLogger().info("CPC插件已经成功卸载！");
    }


}
