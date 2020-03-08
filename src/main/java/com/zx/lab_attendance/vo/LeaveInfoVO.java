package com.zx.lab_attendance.vo;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/6 2:19
 * @Description
 */
public class LeaveInfoVO {
    private String userName;
    private String userNum;
    private String leaveReason;
    private Integer leaveClass;
    private String courseCode;
    private String courseDate;
    private String LeaveImg;
    private String userImg;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
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

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
