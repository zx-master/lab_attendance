package com.zx.lab_attendance.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/27 21:04
 * @Description
 */
public class LeaveclassmVO {
    private String leaveclassmId;
    private String studentName;
    private String studentNum;
    private String approver;
    private String approverId;
    private String leaveReason;
    private Integer leaveClass;
    private String courseName;
    private String courseCode;
    private String courseDate;
    private String LeaveImg;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date leavedate;
    private Integer leaveStatus;

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getLeaveclassmId() {
        return leaveclassmId;
    }

    public void setLeaveclassmId(String leaveclassmId) {
        this.leaveclassmId = leaveclassmId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public String getLeaveImg() {
        return LeaveImg;
    }

    public void setLeaveImg(String leaveImg) {
        LeaveImg = leaveImg;
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
