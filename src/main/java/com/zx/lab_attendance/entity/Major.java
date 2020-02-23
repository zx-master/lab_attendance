package com.zx.lab_attendance.entity;

import java.io.Serializable;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/18 19:37
 * @Description
 */
public class Major implements Serializable {
    private Integer majorId;
    private String majorName;

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
