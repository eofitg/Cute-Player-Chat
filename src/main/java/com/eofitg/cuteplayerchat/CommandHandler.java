package com.eofitg.cuteplayerchat;

import com.eofitg.cuteplayerchat.command.AbstractCommand;
import com.eofitg.cuteplayerchat.command.HelpCommand;
import com.eofitg.cuteplayerchat.command.ListCommand;
import com.eofitg.cuteplayerchat.operation.ChatSuffix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class CommandHandler implements CommandExecutor {
    @Override @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        if(!(sender instanceof Player)) { // 检验是否是玩家信息
            return false;
        }
        if(label.equalsIgnoreCase("cuteplayerchat")||label.equalsIgnoreCase("cuteplayerchat:cuteplayerchat")) { // 获取帮助
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
        if(label.equalsIgnoreCase("getsuff")||label.equalsIgnoreCase("cuteplayerchat:getsuff")) {
            // 查看后缀
            if(sender.isOp()||sender.hasPermission("cuteplayerchat.getsuff")) {
                try {
                    ChatSuffix.getSuffix(sender, args);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage("§4请输入正确格式的指令！");
                    return false;
                }
            }
        }
        if(label.equalsIgnoreCase("getsuffall")||label.equalsIgnoreCase("cuteplayerchat:getsuffall")) {
            // 查看所有后缀
            if(sender.isOp()||sender.hasPermission("cuteplayerchat.getsuffall")) {
                try {
                    String playerNames[] = ConfigReader.users.toArray(new String[0]);
                    ChatSuffix.getSuffix(sender, playerNames);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage("§4请输入正确格式的指令！");
                    return false;
                }
            }
        }
        if(label.equalsIgnoreCase("setsuff")||label.equalsIgnoreCase("cuteplayerchat:setsuff")) {
            // 重设后缀
            if(args.length == 0) {
                sender.sendMessage("§4未指定聊天后缀!");
                return false;
            }
            try {
                ChatSuffix.setSuffix(sender, args, args[args.length - 1]);
                return true;
            } catch (Exception e) {
                sender.sendMessage("§4请输入正确格式的指令！");
                return false;
            }
        }
        if(label.equalsIgnoreCase("setsuffall")||label.equalsIgnoreCase("cuteplayerchat:setsuffall")) {
            // 重设所有后缀
            if(args.length == 0) {
                sender.sendMessage("§4未指定聊天后缀!");
                return false;
            }
            try {
                String suffix = args[args.length - 1];
                List<String> names = ConfigReader.users;
                names.add(suffix);
                String playerNames[] = names.toArray(new String[0]);
                ChatSuffix.setSuffix(sender, playerNames, suffix);
                return true;
            } catch (Exception e) {
                sender.sendMessage("§4请输入正确格式的指令！");
                return false;
            }
        }
        if((label.equalsIgnoreCase("delsuff")||label.equalsIgnoreCase("cuteplayerchat:delsuff"))) {
            // 删除后缀
            if(sender.isOp()||sender.hasPermission("cuteplayerchat.delsuff")) {
                try {
                    ChatSuffix.deleteSuffix(sender, args);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage("§4请输入正确格式的指令！");
                    return false;
                }
            }
        }
        if((label.equalsIgnoreCase("delsuffall")||label.equalsIgnoreCase("cuteplayerchat:delsuffall"))) {
            // 删除所有后缀
            if(sender.isOp()||sender.hasPermission("cuteplayerchat.delsuffall")) {
                try {
                    String playerNames[] = ConfigReader.users.toArray(new String[0]);
                    ChatSuffix.deleteSuffix(sender, playerNames);
                    return true;
                } catch (Exception e) {
                    sender.sendMessage("§4请输入正确格式的指令！");
                    return false;
                }
            }
        }
        return false;
    }
}
