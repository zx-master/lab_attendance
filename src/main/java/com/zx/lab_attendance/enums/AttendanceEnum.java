package com.zx.lab_attendance.enums;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/13 9:29
 * @Description
 */
public enum AttendanceEnum {
    NORMAL(1,"正常"),
    LATE(2,"迟到"),
    SICKLEAVE(3,"病假"),
    AFFAIRLEAVE(4,"事假"),
    ABSENTEEISM(5,"缺勤");

    public final Integer type;
    private final String content;

    AttendanceEnum(int type, String content) {
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
