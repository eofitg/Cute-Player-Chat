package com.eofitg.cuteplayerchat.cmdoperation;

import com.eofitg.cuteplayerchat.CPCConfigReader;
import com.eofitg.cuteplayerchat.CutePlayerChat;
import com.eofitg.cuteplayerchat.command.*;
import com.eofitg.cuteplayerchat.data.PrefixReader;
import com.eofitg.cuteplayerchat.data.SuffixReader;
import com.eofitg.cuteplayerchat.messaging.MessageReader;
import com.eofitg.cuteplayerchat.operation.ChatPrefix;
import com.eofitg.cuteplayerchat.operation.ChatSuffix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;
import java.util.List;

public class CommandHandler implements CommandExecutor {
    protected String wrongFormat = MessageReader.get("tips.error.wrong-format");
    protected String withoutSuffix = MessageReader.get("tips.error.without-data.affix.suffix");
    protected String withoutPrefix = MessageReader.get("tips.error.without-data.affix.prefix");
    @Override @ParametersAreNonnullByDefault
    public boolean onCommand (CommandSender sender, Command command, String label, String args[]) {
        if (!(sender instanceof Player)) { // 检验是否是玩家信息
            return false;
        }
        if (CommandChecker.conform(label, "cuteplayerchat")) { // 获取帮助
            AbstractCommand childCommand = new HelpCommand(sender);
            if (args.length == 0) {
                // 默认执行/cutechat help
                ((AbstractCommand)childCommand).execute(sender, command, label, args);
                return true;
            }
            String childCmd = args[0].toLowerCase();
            switch (childCmd) {
                case "help" : {
                    childCommand = new HelpCommand(sender);
                    break;
                }
                case "list" : {
                    childCommand = new ListCommand(sender);
                    break;
                }
                case "get" : {
                    childCommand = new GetCommand(sender);
                    break;
                }
                case "set" : {
                    childCommand = new SetCommand(sender);
                    break;
                }
                case "delete" : {
                    childCommand = new DeleteCommand(sender);
                    break;
                }
            }
            ((AbstractCommand)childCommand).execute(sender, command, label, args);
            return true;
        }
        if (CommandChecker.conform(label, "getsuf")) {
            // 查看后缀
            if (sender.isOp()||sender.hasPermission("cuteplayerchat.getsuf")) {
                try {
                    ChatSuffix.getSuffix(sender, args);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(wrongFormat);
                    return false;
                }
            }
        }
        if (CommandChecker.conform(label, "getsufall")) {
            // 查看所有后缀
            if (sender.isOp()||sender.hasPermission("cuteplayerchat.getsufall")) {
                try {
                    String[] playerNames = SuffixReader.getUserNames();
                    ChatSuffix.getSuffix(sender, playerNames);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(wrongFormat);
                    return false;
                }
            }
        }
        if (CommandChecker.conform(label, "setsuf")) {
            // 重设后缀
            if (args.length == 0) {
                sender.sendMessage(withoutSuffix);
                return false;
            }
            try {
                ChatSuffix.setSuffix(sender, args, args[args.length - 1]);
                return true;
            } catch (Exception e) {
                sender.sendMessage(wrongFormat);
                return false;
            }
        }
        if (CommandChecker.conform(label, "setsufall")) {
            // 重设所有后缀
            if (args.length == 0) {
                sender.sendMessage(withoutSuffix);
                return false;
            }
            try {
                String suffix = args[args.length - 1];
                String[] names = SuffixReader.getUserNames();
                String[] playerNames = new String[names.length + 1];

                // add suffix to list
                System.arraycopy(names, 0, playerNames, 0, names.length);
                playerNames[names.length] = suffix;

                ChatSuffix.setSuffix(sender, playerNames, suffix);
                return true;
            } catch (Exception e) {
                sender.sendMessage(wrongFormat);
                return false;
            }
        }
        if (CommandChecker.conform(label, "delsuf")) {
            // 删除后缀
            if (sender.isOp()||sender.hasPermission("cuteplayerchat.delsuf")) {
                try {
                    ChatSuffix.deleteSuffix(sender, args);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(wrongFormat);
                    return false;
                }
            }
        }
        if (CommandChecker.conform(label, "delsufall")) {
            // 删除所有后缀
            if (sender.isOp()||sender.hasPermission("cuteplayerchat.delsufall")) {
                try {
                    String[] playerNames = SuffixReader.getUsers();
                    ChatSuffix.deleteSuffix(sender, playerNames);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(wrongFormat);
                    return false;
                }
            }
        }
        if (CommandChecker.conform(label, "getpre")) {
            // 查看前缀
            if (sender.isOp()||sender.hasPermission("cuteplayerchat.getpre")) {
                try {
                    ChatPrefix.getPrefix(sender, args);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(wrongFormat);
                    return false;
                }
            }
        }
        if (CommandChecker.conform(label, "getpreall")) {
            // 查看所有前缀
            if (sender.isOp()||sender.hasPermission("cuteplayerchat.getpreall")) {
                try {
                    String[] playerNames = PrefixReader.getUserNames();
                    ChatPrefix.getPrefix(sender, playerNames);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(wrongFormat);
                    return false;
                }
            }
        }
        if (CommandChecker.conform(label, "setpre")) {
            // 重设前缀
            if (args.length == 0) {
                sender.sendMessage(withoutPrefix);
                return false;
            }
            try {
                ChatPrefix.setPrefix(sender, args, args[args.length - 1]);
                return true;
            } catch (Exception e) {
                sender.sendMessage(wrongFormat);
                return false;
            }
        }
        if (CommandChecker.conform(label, "setpreall")) {
            // 重设所有前缀
            if (args.length == 0) {
                sender.sendMessage(withoutPrefix);
                return false;
            }
            try {
                String prefix = args[args.length - 1];
                String[] names = PrefixReader.getUserNames();
                String[] playerNames = new String[names.length + 1];

                // add prefix to list
                System.arraycopy(names, 0, playerNames, 0, names.length);
                playerNames[names.length] = prefix;

                ChatPrefix.setPrefix(sender, playerNames, prefix);
                return true;
            } catch (Exception e) {
                sender.sendMessage(wrongFormat);
                return false;
            }
        }
        if (CommandChecker.conform(label, "delpre")) {
            // 删除前缀
            if (sender.isOp()||sender.hasPermission("cuteplayerchat.delpre")) {
                try {
                    ChatPrefix.deletePrefix(sender, args);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(wrongFormat);
                    return false;
                }
            }
        }
        if (CommandChecker.conform(label, "delpreall")) {
            // 删除所有前缀
            if (sender.isOp()||sender.hasPermission("cuteplayerchat.delpreall")) {
                try {
                    String[] playerNames = PrefixReader.getUsers();
                    ChatPrefix.deletePrefix(sender, playerNames);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(wrongFormat);
                    return false;
                }
            }
        }
        return false;
    }
}
