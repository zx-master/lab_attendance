package com.zx.lab_attendance.enums;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/20 0:12
 * @Description 消息签收状态枚举
 */
public enum MsgSignFlagEnum {
    UNSIGN(0,"未签收"),
    SIGNED(1,"已签收");

    public final Integer type;
    public final String content;

    MsgSignFlagEnum(Integer type,String content){
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
