package com.zx.lab_attendance.vo;

import java.util.Date;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/11 23:58
 * @Description 选择个人时的考勤信息
 */
public class OnlyAttendance {

    /**
     * 学号
     */
    private String studentNum;

    /**
     * 姓名
     */
    private String name;

    /**
     * 课程
     */
    private String courseName;

    /**
     * 日期
     */
    private Date attendancetime;

    /**
     * 考勤情况
     */
    private Integer attendance;

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getAttendancetime() {
        return attendancetime;
    }

    public void setAttendancetime(Date attendancetime) {
        this.attendancetime = attendancetime;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }
}
