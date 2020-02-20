package com.zx.lab_attendance.netty;

import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/20 0:17
 * @Description
 */
public class UserChannelRel {

    private static HashMap<String, Channel> manager = new HashMap<>();

    public static void put(String senderId,Channel channel) {
        manager.put(senderId,channel);
    }

    public static Channel get(String senderId) {
        return manager.get(senderId);
    }

}
