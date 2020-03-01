package com.zx.lab_attendance.vo;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/2 0:39
 * @Description
 */
public class StudentAttendance {
    private String userId;
    private String username;
    private String userNumber;
    private String department;
    private Integer lateNum;
    private Integer affairLeaveNum;
    private Integer sickNum;
    private Integer absenteeism;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getLateNum() {
        return lateNum;
    }

    public void setLateNum(Integer lateNum) {
        this.lateNum = lateNum;
    }

    public Integer getAffairLeaveNum() {
        return affairLeaveNum;
    }

    public void setAffairLeaveNum(Integer affairLeaveNum) {
        this.affairLeaveNum = affairLeaveNum;
    }

    public Integer getSickNum() {
        return sickNum;
    }

    public void setSickNum(Integer sickNum) {
        this.sickNum = sickNum;
    }

    public Integer getAbsenteeism() {
        return absenteeism;
    }

    public void setAbsenteeism(Integer absenteeism) {
        this.absenteeism = absenteeism;
    }
}
