package com.eofitg.cuteplayerchat;

import com.eofitg.cuteplayerchat.cmdoperation.CommandRegister;
import com.eofitg.cuteplayerchat.listener.SuffixListener;
import com.eofitg.cuteplayerchat.messaging.MessageFormatter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CutePlayerChat extends JavaPlugin {

    private static CutePlayerChat instance;
    private MessageFormatter messageFormatter = null;
    private static String pluginName = null;
    public static CutePlayerChat getInstance() {
        return instance;
    }
    public MessageFormatter getMessageFormatter() {
        return this.messageFormatter;
    }
    public static String getPluginName() {
        return pluginName;
    }

    @Override
    public void onLoad() {
        saveDefaultConfig();
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        pluginName = instance.getName();
        File messagesFile = new File("plugins" + File.separator + "CutePlayerChat", "messages.yml");
        if (!messagesFile.exists()) {
            this.saveResource("messages.yml", true);
        }
        this.messageFormatter = new MessageFormatter();
        Bukkit.getPluginManager().registerEvents(new SuffixListener(), this);
        CommandRegister.register(CPCConfigReader.getCmdNames());
        getLogger().info("CPC插件已经成功加载！");
        
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
        messageFormatter = null;
        pluginName = null;
        saveConfig();
        CPCConfigReader.reset();
        getLogger().info("CPC插件已经成功卸载！");
    }


}
