package com.zx.lab_attendance.entity;

import java.io.Serializable;

public class Collective implements Serializable {
    private String collectiveId;

    private String departmentId;

    private Integer collectiveNumber;

    private Department department;

    private String tutoringUser;

    private String mentorUser;

    private static final long serialVersionUID = 1L;

    public String getTutoringUser() {
        return tutoringUser;
    }

    public void setTutoringUser(String tutoringUser) {
        this.tutoringUser = tutoringUser;
    }

    public String getMentorUser() {
        return mentorUser;
    }

    public void setMentorUser(String mentorUser) {
        this.mentorUser = mentorUser;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getCollectiveId() {
        return collectiveId;
    }

    public void setCollectiveId(String collectiveId) {
        this.collectiveId = collectiveId == null ? null : collectiveId.trim();
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId == null ? null : departmentId.trim();
    }

    public Integer getCollectiveNumber() {
        return collectiveNumber;
    }

    public void setCollectiveNumber(Integer collectiveNumber) {
        this.collectiveNumber = collectiveNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", collectiveId=").append(collectiveId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", collectiveNumber=").append(collectiveNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}