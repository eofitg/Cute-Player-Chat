package com.eofitg.cuteplayerchat.operation;

import com.eofitg.cuteplayerchat.data.PrefixReader;
import org.bukkit.command.CommandSender;

public class ChatPrefix {
    public static void getPrefix(CommandSender sender, String args[]) {
        String playerName = sender.getName(); // 默认查看自己的聊天前缀
        String canGetPlayerNames = "";
        String cantGetPlayerNames = "";
        String sendMessage1 = "";
        String sendMessage2 = "";
        if(args.length == 0) { // 等于0，代表没有额外指定玩家名字，那么查看自己的(/getpre)
            if(!PrefixReader.getPrefix(playerName).equals("")&&!PrefixReader.getPrefix(playerName).equals("EMPTY")) {
                sender.sendMessage("§7" + playerName + "当前的聊天前缀是：" + PrefixReader.getPrefix(playerName));
            } else {
                sender.sendMessage("§4" + playerName + "还没有设置聊天前缀！");
            }
        } else {
            // 大于0，代表额外指定了玩家名字
            // /getsuf [playerName_1] ... [playerName_n]
            // sender.sendMessage(Integer.toString(args.length));
            for(int i=0; i<args.length; i++) {
                String _playerName = args[i];
                if(!PrefixReader.getPrefix(_playerName).equals("")&&!PrefixReader.getPrefix(_playerName).equals("EMPTY")) {
                    sender.sendMessage("§7" + _playerName + "当前的聊天前缀是：" + PrefixReader.getPrefix(_playerName));
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
                sendMessage1 = "§4所有聊天前缀获取失败！";
            } else {
                sendMessage1 = "§6" + canGetPlayerNames + "的聊天前缀获取成功！";
            }
            if(cantGetPlayerNames.equals("")) {
                sendMessage2 = "§a全部获取成功！";
            } else {
                sendMessage2 = "§4" + cantGetPlayerNames + "还没有设置聊天前缀！";
            }
            sender.sendMessage(sendMessage1);
            sender.sendMessage(sendMessage2);
        }
    }

    public static void setPrefix(CommandSender sender, String args[], String prefix) {
        String playerName = sender.getName(); // 默认更改自己的聊天前缀
        String playerNames = "";
        if(args.length == 1) { // 等于1，代表没有额外指定玩家名字，那么修改自己的(/setsuf <prefix>)
            PrefixReader.setPrefix(playerName, prefix);
            sender.sendMessage("§a" + playerName + "的聊天前缀已修改为：" + prefix);
        } else {
            // 大于1，代表额外指定了玩家名字
            // /setpre [playerName_1] ... [playerName_n] <prefix>
            playerNames = args[0];
            // 将playerNames先初始化为输入的第一个玩家的名字
            // sender.sendMessage(Integer.toString(args.length));
            for(int i=1; i<args.length - 1; i++) {
                // 如果不止输入了一个玩家名，就从第二个玩家名字开始累加playerNames
                String addPlayerName = "，" + args[i];
                playerNames = playerNames + addPlayerName;
            }
            // 最终生成的playerNames就是所有想更改的玩家名字连接起来的String
            // sender.sendMessage(playerNames);
            for(int i=0; i<args.length - 1; i++) {
                String _playerName = args[i];
                PrefixReader.setPrefix(_playerName, prefix);
            }
            sender.sendMessage("§a" + playerNames + "的聊天前缀已修改为：" + prefix);
        }
    }

    public static void deletePrefix(CommandSender sender, String args[]) {
        String playerName = sender.getName(); // 默认删除自己的聊天前缀
        String canDelPlayerNames = "";
        String cantDelPlayerNames = "";
        String sendMessage1 = "";
        String sendMessage2 = "";
        if(args.length == 0) { // 等于0，代表没有额外指定玩家名字，那么删除自己的(/delpre)
            if(PrefixReader.deletePrefix(playerName)) {
                sender.sendMessage("§a" + playerName + "的聊天前缀已删除！");
            } else {
                sender.sendMessage("§4" + playerName + "还没有设置聊天前缀！");
            }
        } else {
            // 大于0，代表额外指定了玩家名字
            // /delpre [playerName_1] ... [playerName_n]
            // sender.sendMessage(Integer.toString(args.length));
            for(int i=0; i<args.length; i++) {
                String _playerName = args[i];
                if(PrefixReader.deletePrefix(_playerName)) {
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
                sendMessage1 = "§4没有人的聊天前缀被删除！";
            } else {
                sendMessage1 = "§7" + canDelPlayerNames + "的聊天前缀已删除！";
            }
            if(cantDelPlayerNames.equals("")) {
                sendMessage2 = "§a全部删除成功！";
            } else {
                sendMessage2 = "§4" + cantDelPlayerNames + "还没有设置聊天前缀！";
            }
            sender.sendMessage(sendMessage1);
            sender.sendMessage(sendMessage2);
        }
    }
}
