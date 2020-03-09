package com.zx.lab_attendance.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/7 22:00
 * @Description
 */
public class ApplyLabVO {
    private String studentName;
    private String studentId;
    private String approverId;
    private String approver;
    private String studentNum;
    private String labReason;
    private String labCode;
    private String labDate;
    private Integer status;            //处理状态status
    private String id;                //applyid
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date applylabdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public Date getApplylabdate() {
        return applylabdate;
    }

    public void setApplylabdate(Date applylabdate) {
        this.applylabdate = applylabdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getLabReason() {
        return labReason;
    }

    public void setLabReason(String labReason) {
        this.labReason = labReason;
    }

    public String getLabCode() {
        return labCode;
    }

    public void setLabCode(String labCode) {
        this.labCode = labCode;
    }

    public String getLabDate() {
        return labDate;
    }

    public void setLabDate(String labDate) {
        this.labDate = labDate;
    }
}
