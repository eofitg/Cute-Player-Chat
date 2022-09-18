package com.eofitg.cuteplayerchat.cmdoperation;

import com.eofitg.cuteplayerchat.CPCConfigReader;
import com.eofitg.cuteplayerchat.CutePlayerChat;

import java.util.List;

public class CommandChecker {
    public static boolean conform (String requestCommand, String commandName) {
        List<String> cmdList = CPCConfigReader.getCmdList(commandName);
        String pluginName = CutePlayerChat.getPluginName().toLowerCase();
        if (requestCommand.startsWith(pluginName + ":")) {
            // 通过去除cuteplayerchat:前缀来达到识别这一前缀的效果
            requestCommand = requestCommand.substring(pluginName.length() + 1);
        }
        if (cmdList.contains(requestCommand)) {
            return true;
        }
        return false;
    }
}
