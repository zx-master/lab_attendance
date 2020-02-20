package com.zx.lab_attendance.enums;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/20 0:07
 * @Description 发送消息的动作枚举
 */
public enum MsgActionEnum {

    CONNECT(1,"第一次（或重连）初始化连接"),
    CHAT(2,"聊天信息"),
    SIGNED(3,"消息签收"),
    KEEPALIVE(4,"客户端保持心跳");

    public final Integer type;
    public final String content;

    MsgActionEnum(Integer type,String content){
        this.type = type;
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
