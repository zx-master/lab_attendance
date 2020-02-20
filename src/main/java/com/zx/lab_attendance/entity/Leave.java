package com.zx.lab_attendance.entity;

import java.io.Serializable;
import java.util.Date;

public class Leave implements Serializable {
    private String leaveId;

    private String studentId;

    private String leaveReason;

    private Integer leaveClass;

    private String leaveImg;

    private String approver;

    private Date leaveDatestart;

    private Date leaveDateend;

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
        this.leaveImg = leaveImg == null ? null : leaveImg.trim();
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