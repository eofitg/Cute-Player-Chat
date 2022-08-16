package com.eofitg.cuteplayerchat.command;

import com.eofitg.cuteplayerchat.ConfigReader;
import com.eofitg.cuteplayerchat.CutePlayerChat;
import com.google.common.base.Joiner;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListCommand extends AbstractCommand{
    public static final String NAME = "List";
    public static final String DESCRIPTION = "List all users.";
    public static final String PERMISSION = "cuteplayerchat.list";
    public static final String USAGE = "/cuteplayerchat list";
    public static final String[] SUB_PERMISSIONS = new String[]{""};

    public ListCommand(CommandSender sender) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!this.hasPermission()) {
            sender.sendMessage("§4权限不足，无法执行！");
            return;
        }
        List<String> userNames = ConfigReader.config.getStringList("data.users");
        String users = Joiner.on(", ").join((Iterable)userNames);
        sender.sendMessage(CutePlayerChat.getInstance().getMessageFormatter().format("list.list", userNames.size(), users));
    }

}
