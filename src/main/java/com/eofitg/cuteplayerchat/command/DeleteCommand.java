package com.eofitg.cuteplayerchat.command;

import com.eofitg.cuteplayerchat.CPCConfigReader;
import com.eofitg.cuteplayerchat.CutePlayerChat;
import com.google.common.base.Joiner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class DeleteCommand extends AbstractCommand {
    public static final String NAME = "Delete";
    public static final String DESCRIPTION = "Delete help.";
    public static final String PERMISSION = "cuteplayerchat.delete";
    public static final String USAGE = "/cuteplayerchat delete";
    public static final String[] SUB_PERMISSIONS = new String[]{""};

    public DeleteCommand(CommandSender sender) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!this.hasPermission()) {
            sender.sendMessage("§4权限不足，无法执行！");
            return;
        }
        ConfigurationSection delete = CutePlayerChat.getInstance().getMessageFormatter().getMessageFile().getConfig().getConfigurationSection("delete");
        for (String s : delete.getKeys(false)) {
            if (!sender.hasPermission("cuteplayerchat." + s) && !s.equalsIgnoreCase("header")) continue;
            sender.sendMessage(CutePlayerChat.getInstance().getMessageFormatter().format(false, delete.getName() + "." + s, new Object[0]));
        }
    }
}
