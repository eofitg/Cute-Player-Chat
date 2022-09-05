package com.eofitg.cuteplayerchat.cmdoperation;

import com.eofitg.cuteplayerchat.CPCConfigReader;
import com.eofitg.cuteplayerchat.command.AbstractCommand;
import com.eofitg.cuteplayerchat.command.HelpCommand;
import com.eofitg.cuteplayerchat.command.ListCommand;
import com.eofitg.cuteplayerchat.data.SuffixReader;
import com.eofitg.cuteplayerchat.messaging.MessageReader;
import com.eofitg.cuteplayerchat.operation.ChatSuffix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class CommandHandler implements CommandExecutor {
    protected String wrongFormat = MessageReader.get("tips.error.wrong-format");
    protected String withoutData = MessageReader.get("tips.error.without-data.suffix");
    @Override @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        if(!(sender instanceof Player)) { // 检验是否是玩家信息
            return false;
        }
        if(CommandChecker.conform(label, "cuteplayerchat")) { // 获取帮助
            AbstractCommand childCommand = new HelpCommand(sender);
            if(args.length == 0) {
                // 默认执行/cutechat help
                ((AbstractCommand)childCommand).execute(sender, command, label, args);
                return true;
            }
            String childCmd = args[0].toLowerCase();
            switch (childCmd) {
                case "help" : {
                    // sender.sendMessage("这是一条帮助！");
                    childCommand = new HelpCommand(sender);
                    break;
                }
                case "list" : {
                    childCommand = new ListCommand(sender);
                    break;
                }
            }
            ((AbstractCommand)childCommand).execute(sender, command, label, args);
            return true;
        }
        if(CommandChecker.conform(label, "getsuff")) {
            // 查看后缀
            if(sender.isOp()||sender.hasPermission("cuteplayerchat.getsuff")) {
                try {
                    ChatSuffix.getSuffix(sender, args);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(wrongFormat);
                    return false;
                }
            }
        }
        if(CommandChecker.conform(label, "getsuffall")) {
            // 查看所有后缀
            if(sender.isOp()||sender.hasPermission("cuteplayerchat.getsuffall")) {
                try {
                    String playerNames[] = SuffixReader.users.toArray(new String[0]);
                    ChatSuffix.getSuffix(sender, playerNames);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(wrongFormat);
                    return false;
                }
            }
        }
        if(CommandChecker.conform(label, "setsuff")) {
            // 重设后缀
            if(args.length == 0) {
                sender.sendMessage(withoutData);
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
        if(CommandChecker.conform(label, "setsuffall")) {
            // 重设所有后缀
            if(args.length == 0) {
                sender.sendMessage(withoutData);
                return false;
            }
            try {
                String suffix = args[args.length - 1];
                List<String> names = CPCConfigReader.getUsers("suffix");
                names.add(suffix);
                String playerNames[] = names.toArray(new String[0]);
                ChatSuffix.setSuffix(sender, playerNames, suffix);
                return true;
            } catch (Exception e) {
                sender.sendMessage(wrongFormat);
                return false;
            }
        }
        if(CommandChecker.conform(label, "delsuff")) {
            // 删除后缀
            if(sender.isOp()||sender.hasPermission("cuteplayerchat.delsuff")) {
                try {
                    ChatSuffix.deleteSuffix(sender, args);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage(wrongFormat);
                    return false;
                }
            }
        }
        if(CommandChecker.conform(label, "delsuffall")) {
            // 删除所有后缀
            if(sender.isOp()||sender.hasPermission("cuteplayerchat.delsuffall")) {
                try {
                    String playerNames[] = CPCConfigReader.getUsers("suffix").toArray(new String[0]);
                    ChatSuffix.deleteSuffix(sender, playerNames);
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
