package com.eofitg.cuteplayerchat.command;

import com.eofitg.cuteplayerchat.CutePlayerChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class SetCommand extends AbstractCommand {
    public static final String NAME = "Set";
    public static final String DESCRIPTION = "Set help.";
    public static final String PERMISSION = "cuteplayerchat.set";
    public static final String USAGE = "/cuteplayerchat set";
    public static final String[] SUB_PERMISSIONS = new String[]{""};

    public SetCommand (CommandSender sender) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE);
    }

    @Override
    public void execute (CommandSender sender, Command command, String label, String[] args) {
        if (!this.hasPermission()) {
            sender.sendMessage("§4权限不足，无法执行！");
            return;
        }
        ConfigurationSection set = CutePlayerChat.getInstance().getMessageFormatter().getMessageFile().getConfig().getConfigurationSection("set");
        for (String s : set.getKeys(false)) {
            if (!sender.hasPermission("cuteplayerchat." + s) && !s.equalsIgnoreCase("header")) continue;
            sender.sendMessage(CutePlayerChat.getInstance().getMessageFormatter().format(false, set.getName() + "." + s, new Object[0]));
        }
    }
}
