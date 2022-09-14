package com.eofitg.cuteplayerchat.command;

import com.eofitg.cuteplayerchat.CutePlayerChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class GetCommand extends AbstractCommand {
    public static final String NAME = "Get";
    public static final String DESCRIPTION = "Get help.";
    public static final String PERMISSION = "cuteplayerchat.get";
    public static final String USAGE = "/cuteplayerchat get";
    public static final String[] SUB_PERMISSIONS = new String[]{""};

    public GetCommand(CommandSender sender) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!this.hasPermission()) {
            sender.sendMessage("§4权限不足，无法执行！");
            return;
        }
        ConfigurationSection get = CutePlayerChat.getInstance().getMessageFormatter().getMessageFile().getConfig().getConfigurationSection("get");
        for (String s : get.getKeys(false)) {
            if (!sender.hasPermission("cuteplayerchat." + s) && !s.equalsIgnoreCase("header")) continue;
            sender.sendMessage(CutePlayerChat.getInstance().getMessageFormatter().format(false, get.getName() + "." + s, new Object[0]));
        }
    }
}
