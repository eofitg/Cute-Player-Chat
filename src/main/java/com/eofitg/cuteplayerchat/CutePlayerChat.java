package com.eofitg.cuteplayerchat;

import com.eofitg.cuteplayerchat.cmdoperation.CommandRegister;
import com.eofitg.cuteplayerchat.listener.PlayerListener;
import com.eofitg.cuteplayerchat.listener.PrefixListener;
import com.eofitg.cuteplayerchat.listener.SuffixListener;
import com.eofitg.cuteplayerchat.messaging.MessageFile;
import com.eofitg.cuteplayerchat.messaging.MessageFormatter;
import com.eofitg.cuteplayerchat.messaging.MessageReader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class CutePlayerChat extends JavaPlugin {
    private static CutePlayerChat instance;
    private static MessageFile messageFile = null;
    private MessageFormatter messageFormatter = null;
    private static String pluginName = null;
    public static CutePlayerChat getInstance () {
        return instance;
    }
    public static MessageFile getMessageFile () {
        return messageFile;
    }
    public MessageFormatter getMessageFormatter () {
        return this.messageFormatter;
    }
    public static String getPluginName () {
        return pluginName;
    }

    @Override
    public void onLoad () {
        saveDefaultConfig();
    }
    @Override
    public void onEnable () {
        // Plugin startup logic
        instance = this;
        pluginName = instance.getName();
        File mFile = new File("plugins" + File.separator + "CutePlayerChat", "messages.yml");
        if (!mFile.exists()) {
            this.saveResource("messages.yml", true);
        }
        messageFile = new MessageFile(mFile);
        this.messageFormatter = new MessageFormatter();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new PrefixListener(), this);
        Bukkit.getPluginManager().registerEvents(new SuffixListener(), this);
        CommandRegister.register(CPCConfigReader.getCmdNames());
        getLogger().info(MessageReader.get("tips.success.load"));
    }

    @Override
    public void onDisable () {
        // Plugin shutdown logic
        String unload = MessageReader.get("tips.success.unload");
        instance = null;
        messageFile = null;
        messageFormatter = null;
        pluginName = null;
        saveConfig();
        CPCConfigReader.reset();
        getLogger().info(unload);
    }

}
