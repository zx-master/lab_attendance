package com.zx.lab_attendance.netty;

import com.zx.lab_attendance.entity.Chatmsg;

import java.io.Serializable;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/19 23:55
 * @Description
 */
public class DataContent implements Serializable {

    private static final long serialVersionUID = -2410803226801059695L;
    /**
     * 动作类型
     */
    private Integer action;
    /**
     * 聊天内容
     */
    private Chatmsg chatmsg;
    /**
     * 扩展字段
     */
    private String extand;

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Chatmsg getChatmsg() {
        return chatmsg;
    }

    public void setChatmsg(Chatmsg chatmsg) {
        this.chatmsg = chatmsg;
    }

    public String getExtand() {
        return extand;
    }

    public void setExtand(String extand) {
        this.extand = extand;
    }
}
