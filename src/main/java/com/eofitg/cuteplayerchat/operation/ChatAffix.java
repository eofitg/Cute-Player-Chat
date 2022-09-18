package com.eofitg.cuteplayerchat.operation;

import com.eofitg.cuteplayerchat.CutePlayerChat;

public class ChatAffix {

    protected static String format (String key, Object... args) {
        return CutePlayerChat.getInstance().getMessageFormatter().format(false, key, args);
    }

}
