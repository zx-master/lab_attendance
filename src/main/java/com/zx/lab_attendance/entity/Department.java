package com.zx.lab_attendance.entity;

import java.io.Serializable;

public class Department implements Serializable {
    private String departmentId;

    private String departmentDescribe;

    private static final long serialVersionUID = 1L;

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public String getDepartmentDescribe() {
        return departmentDescribe;
    }

    public void setDepartmentDescribe(String departmentDescribe) {
        this.departmentDescribe = departmentDescribe == null ? null : departmentDescribe.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", departmentId=").append(departmentId);
        sb.append(", departmentDescribe=").append(departmentDescribe);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}