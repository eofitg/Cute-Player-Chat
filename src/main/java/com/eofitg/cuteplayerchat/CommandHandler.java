package com.eofitg.cuteplayerchat;

import com.eofitg.cuteplayerchat.command.AbstractCommand;
import com.eofitg.cuteplayerchat.command.HelpCommand;
import com.eofitg.cuteplayerchat.command.ListCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;

public class CommandHandler implements CommandExecutor {
    @Override @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        if(!(sender instanceof Player)) { // 检验是否是玩家信息
            return false;
        }
        if(label.equalsIgnoreCase("cuteplayerchat")||label.equalsIgnoreCase("cuteplayerchat:cuteplayerchat")) { // 获取帮助
            AbstractCommand childCommand = new HelpCommand(sender);
            if(args.length == 0) { // 默认执行/cutechat help
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
            // sender.sendMessage("§4无效的指令格式！");
            // return false;
        }
        if(label.equalsIgnoreCase("getsuff")||label.equalsIgnoreCase("cuteplayerchat:getsuff")) { // 查看后缀
            if(sender.isOp()||sender.hasPermission("cuteplayerchat.getsuff")) {
                try {
                    String playerName = sender.getName(); // 默认查看自己的聊天后缀
                    String canGetPlayerNames = "";
                    String cantGetPlayerNames = "";
                    String sendMessage1 = "";
                    String sendMessage2 = "";
                    if(args.length == 0) { // 等于0，代表没有额外指定玩家名字，那么查看自己的(/getsuff)
                        if(!ConfigReader.getSuffix(playerName).equals("")&&!ConfigReader.getSuffix(playerName).equals("EMPTY")) {
                            sender.sendMessage("§7" + playerName + "当前的聊天后缀是：" + ConfigReader.getSuffix(playerName));
                        } else {
                            sender.sendMessage("§4" + playerName + "还没有设置聊天后缀！");
                        }
                    } else {
                        // 大于0，代表额外指定了玩家名字
                        // /getsuff [playerName_1] ... [playerName_n]
                        // sender.sendMessage(Integer.toString(args.length));
                        for(int i=0; i<args.length; i++) {
                            String _playerName = args[i];
                            if(!ConfigReader.getSuffix(_playerName).equals("")&&!ConfigReader.getSuffix(_playerName).equals("EMPTY")) {
                                sender.sendMessage("§7" + _playerName + "当前的聊天后缀是：" + ConfigReader.getSuffix(_playerName));
                                if(canGetPlayerNames.equals("")) {
                                    canGetPlayerNames = _playerName;
                                    continue;
                                }
                                canGetPlayerNames = canGetPlayerNames + "，" + _playerName;
                            } else {
                                if(cantGetPlayerNames.equals("")) {
                                    cantGetPlayerNames = _playerName;
                                    continue;
                                }
                                cantGetPlayerNames = cantGetPlayerNames + "，" + _playerName;
                            }
                        }
                        if(canGetPlayerNames.equals("")) {
                            sendMessage1 = "§4所有聊天后缀获取失败！";
                        } else {
                            sendMessage1 = "§6" + canGetPlayerNames + "的聊天后缀获取成功！";
                        }
                        if(cantGetPlayerNames.equals("")) {
                            sendMessage2 = "§a全部获取成功！";
                        } else {
                            sendMessage2 = "§6" + cantGetPlayerNames + "还没有设置聊天后缀！";
                        }
                        sender.sendMessage(sendMessage1);
                        sender.sendMessage(sendMessage2);
                    }
                    return true;
                } catch (Exception e) {
                    sender.sendMessage("§4请输入正确格式的指令！");
                    return false;
                }
            }
        }
        if(label.equalsIgnoreCase("setsuff")||label.equalsIgnoreCase("cuteplayerchat:setsuff")) { // 重设后缀
            if(args.length == 0) {
                sender.sendMessage("§4未指定聊天后缀!");
                return false;
            }
            try {
                String suffix = args[0];
                String playerName = sender.getName(); // 默认更改自己的聊天后缀
                String playerNames = "";
                if(args.length == 1) { // 等于1，代表没有额外指定玩家名字，那么修改自己的(/setsuff <suffix>)
                    ConfigReader.setSuffix(playerName, suffix);
                    sender.sendMessage("§a" + playerName + "的聊天后缀已修改为：" + suffix);
                } else {
                    // 大于1，代表额外指定了玩家名字
                    // /setsuff <suffix> [playerName_1] ... [playerName_n]
                    playerNames = args[1];
                    // 将playerNames先初始化为输入的第一个玩家的名字
                    // sender.sendMessage(Integer.toString(args.length));
                    for(int i=2; i<args.length; i++) {
                        // 如果不止输入了一个玩家名，就从第二个玩家名字开始累加playerNames
                        String addPlayerName = "，" + args[i];
                        playerNames = playerNames + addPlayerName;
                    }
                    // 最终生成的playerNames就是所有想更改的玩家名字连接起来的String
                    // sender.sendMessage(playerNames);
                    for(int i=1; i<args.length; i++) {
                        String _playerName = args[i];
                        ConfigReader.setSuffix(_playerName, suffix);
                    }
                    sender.sendMessage("§a" + playerNames + "的聊天后缀已修改为：" + suffix);
                }
                return true;
            } catch (Exception e) {
                sender.sendMessage("§4请输入正确格式的指令！");
                return false;
            }
        }
        if((label.equalsIgnoreCase("delsuff")||label.equalsIgnoreCase("cuteplayerchat:delsuff"))) { // 删除后缀
            if(sender.isOp()||sender.hasPermission("cuteplayerchat.delsuff")) {
                try {
                    String playerName = sender.getName(); // 默认删除自己的聊天后缀
                    String canDelPlayerNames = "";
                    String cantDelPlayerNames = "";
                    String sendMessage1 = "";
                    String sendMessage2 = "";
                    if(args.length == 0) { // 等于0，代表没有额外指定玩家名字，那么删除自己的(/delsuff)
                        if(ConfigReader.deleteSuffix(playerName)) {
                            sender.sendMessage("§a" + playerName + "的聊天后缀已删除！");
                        } else {
                            sender.sendMessage("§4" + playerName + "还没有设置聊天后缀！");
                        }
                    } else {
                        // 大于0，代表额外指定了玩家名字
                        // /delsuff [playerName_1] ... [playerName_n]
                        // sender.sendMessage(Integer.toString(args.length));
                        for(int i=0; i<args.length; i++) {
                            String _playerName = args[i];
                            if(ConfigReader.deleteSuffix(_playerName)) {
                                if(canDelPlayerNames.equals("")) {
                                    canDelPlayerNames = _playerName;
                                    continue;
                                }
                                canDelPlayerNames = canDelPlayerNames + "，" + _playerName;
                            } else {
                                if(cantDelPlayerNames.equals("")) {
                                    cantDelPlayerNames = _playerName;
                                    continue;
                                }
                                cantDelPlayerNames = cantDelPlayerNames + "，" + _playerName;
                            }
                        }
                        if(canDelPlayerNames.equals("")) {
                            sendMessage1 = "§4没有人的聊天后缀被删除！";
                        } else {
                            sendMessage1 = "§6" + canDelPlayerNames + "的聊天后缀已删除！";
                        }
                        if(cantDelPlayerNames.equals("")) {
                            sendMessage2 = "§a全部删除成功！";
                        } else {
                            sendMessage2 = "§6" + cantDelPlayerNames + "还没有设置聊天后缀！";
                        }
                        sender.sendMessage(sendMessage1);
                        sender.sendMessage(sendMessage2);
                    }
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
