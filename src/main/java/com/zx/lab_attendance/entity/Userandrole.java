package com.zx.lab_attendance.entity;

import java.io.Serializable;

public class Userandrole implements Serializable {
    private Integer userandroleId;

    private String userId;

    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public Integer getUserandroleId() {
        return userandroleId;
    }

    public void setUserandroleId(Integer userandroleId) {
        this.userandroleId = userandroleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userandroleId=").append(userandroleId);
        sb.append(", userId=").append(userId);
        sb.append(", roleId=").append(roleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}