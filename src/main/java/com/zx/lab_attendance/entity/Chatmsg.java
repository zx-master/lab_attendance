package com.zx.lab_attendance.entity;

import java.io.Serializable;

public class Chatmsg implements Serializable {
    private String chatmsgId;

    private String receiverId;

    private String senderId;

    private String msg;

    private Integer receiveSign;

    private static final long serialVersionUID = 1L;

    public String getChatmsgId() {
        return chatmsgId;
    }

    public void setChatmsgId(String chatmsgId) {
        this.chatmsgId = chatmsgId == null ? null : chatmsgId.trim();
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId == null ? null : receiverId.trim();
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId == null ? null : senderId.trim();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    public Integer getReceiveSign() {
        return receiveSign;
    }

    public void setReceiveSign(Integer receiveSign) {
        this.receiveSign = receiveSign;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", chatmsgId=").append(chatmsgId);
        sb.append(", receiverId=").append(receiverId);
        sb.append(", senderId=").append(senderId);
        sb.append(", msg=").append(msg);
        sb.append(", receiveSign=").append(receiveSign);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}