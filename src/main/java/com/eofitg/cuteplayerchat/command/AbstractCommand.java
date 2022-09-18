package com.eofitg.cuteplayerchat.command;

import com.eofitg.cuteplayerchat.CutePlayerChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand {
    private final CommandSender sender;
    private final String name;
    private final String description;
    private final String permission;
    private final String usage;
    private final String[] subPermissions;

    public AbstractCommand (CommandSender sender, String name, String description, String permission, String[] subPermissions, String usage) {
        this.sender = sender;
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.subPermissions = subPermissions;
        this.usage = usage;
    }

    public CommandSender getSender () {
        return this.sender;
    }

    public String getName () {
        return this.name;
    }

    public String getDescription () {
        return this.description;
    }

    public String getPermission () {
        return this.permission;
    }

    public String[] getSubPermissions () {
        return this.subPermissions;
    }

    public String getUsage () {
        return this.usage;
    }

    public boolean hasPermission () {
        return this.sender.hasPermission(this.permission) || this.isSenderConsole() || this.isSenderRemoteConsole();
    }

    public boolean hasPermission (String sub) {
        String permission = this.permission + "." + sub;
        return this.sender.hasPermission(permission) || this.isSenderConsole() || this.isSenderRemoteConsole();
    }

    public void sendUsage () {
        this.sender.sendMessage(CutePlayerChat.getInstance().getMessageFormatter().format(false, "error.usage.command", this.name));
        this.sender.sendMessage(CutePlayerChat.getInstance().getMessageFormatter().format(false, "error.usage.description", this.description));
        this.sender.sendMessage(CutePlayerChat.getInstance().getMessageFormatter().format(false, "error.usage.usage", this.usage));
    }

    public boolean isSenderPlayer () {
        return this.sender instanceof Player;
    }

    public boolean isSenderConsole () {
        return this.sender instanceof ConsoleCommandSender;
    }

    public boolean isSenderRemoteConsole () {
        return this.sender instanceof RemoteConsoleCommandSender;
    }

    public abstract void execute (CommandSender var1, Command var2, String var3, String[] var4);
}
