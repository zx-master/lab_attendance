package com.zx.lab_attendance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/26 16:53
 * @Description
 */
public class Leaveclassm implements Serializable {

    private static final long serialVersionUID = -3641737554913938847L;

    private String leaveclassmId;

    private String studentId;

    private String leaveReason;

    private Integer leaveClass;

    private String leaveImg;

    private String approver;

    private String labusingId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date leavedate;

    private Integer leaveStatus;

    private Labusing labusing;

    private Users studentUser;

    private Users teacherUser;

    public Users getStudentUser() {
        return studentUser;
    }

    public void setStudentUser(Users studentUser) {
        this.studentUser = studentUser;
    }

    public Users getTeacherUser() {
        return teacherUser;
    }

    public void setTeacherUser(Users teacherUser) {
        this.teacherUser = teacherUser;
    }

    public Labusing getLabusing() {
        return labusing;
    }

    public void setLabusing(Labusing labusing) {
        this.labusing = labusing;
    }

    public String getLeaveclassmId() {
        return leaveclassmId;
    }

    public void setLeaveclassmId(String leaveclassmId) {
        this.leaveclassmId = leaveclassmId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
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
        this.approver = approver;
    }

    public String getLabusingId() {
        return labusingId;
    }

    public void setLabusingId(String labusingId) {
        this.labusingId = labusingId;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public Integer getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(Integer leaveStatus) {
        this.leaveStatus = leaveStatus;
    }
}
