package com.eofitg.cuteplayerchat.command;

import com.eofitg.cuteplayerchat.CutePlayerChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class HelpCommand extends AbstractCommand {
    public static final String NAME = "Help";
    public static final String DESCRIPTION = "Displays help information.";
    public static final String PERMISSION = "cuteplayerchat.help";
    public static final String USAGE = "/cuteplayerchat help";
    public static final String[] SUB_PERMISSIONS = new String[]{""};

    public HelpCommand (CommandSender sender) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE);
    }

    @Override
    public void execute (CommandSender sender, Command command, String label, String[] args) {
        if (!this.hasPermission()) {
            sender.sendMessage("§4权限不足，无法执行！");
            return;
        }
        ConfigurationSection help = CutePlayerChat.getInstance().getMessageFormatter().getMessageFile().getConfig().getConfigurationSection("help");
        for (String s : help.getKeys(false)) {
            if (!sender.hasPermission("cuteplayerchat." + s) && !s.equalsIgnoreCase("header")) continue;
            sender.sendMessage(CutePlayerChat.getInstance().getMessageFormatter().format(false, help.getName() + "." + s, new Object[0]));
        }
    }
}
