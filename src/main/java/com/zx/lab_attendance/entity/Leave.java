package com.zx.lab_attendance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Leave implements Serializable {
    private String leaveId;

    private String studentId;

    private String leaveReason;

    private Integer leaveClass;

    private String leaveImg;

    private String approver;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date leaveDatestart;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date leaveDateend;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date leavedate;

    private Integer leaveStatus;

    private static final long serialVersionUID = 1L;

    public Integer getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(Integer leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId == null ? null : leaveId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason == null ? null : leaveReason.trim();
    }

    public Integer getLeaveClass() {
        return leaveClass;
    }

    public void setLeaveClass(Integer leaveClass) {
        this.leaveClass = leaveClass;
    }

    public String getLeaveImg() {
        return leaveImg;
    }

    public void setLeaveImg(String leaveImg) {
        this.leaveImg = leaveImg;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public Date getLeaveDatestart() {
        return leaveDatestart;
    }

    public void setLeaveDatestart(Date leaveDatestart) {
        this.leaveDatestart = leaveDatestart;
    }

    public Date getLeaveDateend() {
        return leaveDateend;
    }

    public void setLeaveDateend(Date leaveDateend) {
        this.leaveDateend = leaveDateend;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", leaveId=").append(leaveId);
        sb.append(", studentId=").append(studentId);
        sb.append(", leaveReason=").append(leaveReason);
        sb.append(", leaveClass=").append(leaveClass);
        sb.append(", leaveImg=").append(leaveImg);
        sb.append(", approver=").append(approver);
        sb.append(", leaveDatestart=").append(leaveDatestart);
        sb.append(", leaveDateend=").append(leaveDateend);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}