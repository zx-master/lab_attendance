package com.zx.lab_attendance.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zx.lab_attendance.entity.Course;

import java.util.Date;
import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/11 21:53
 * @Description 点击班级展示班里每个同学的考勤情况
 */
public class PersonalAttendance {
    /**
     * attendance_id
     */
    private String attendanceid;
    /**
     * 时间
     */
    private Date attendancetime;
    /**
     * 姓名
     */
    private String name;
    /**
     * 学号
     */
    private String studentNum;
    /**
     * 专业
     */
    private String majorName;
    /**
     * 考勤状态
     */
    private Integer attendanceState;
    /**
     * 课程科目
     */
    private Course course;

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getAttendanceState() {
        return attendanceState;
    }

    public void setAttendanceState(Integer attendanceState) {
        this.attendanceState = attendanceState;
    }

    public String getAttendanceid() {
        return attendanceid;
    }

    public void setAttendanceid(String attendanceid) {
        this.attendanceid = attendanceid;
    }

    public Course getCourseList() {
        return course;
    }

    public void setCourseList(Course course) {
        this.course = course;
    }

    public Date getAttendancetime() {
        return attendancetime;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public void setAttendancetime(Date attendancetime) {
        this.attendancetime = attendancetime;
    }

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


    @Override
    public String toString() {
        return "PersonalAttendance{" +
                "attendanceid='" + attendanceid + '\'' +
                ", attendancetime=" + attendancetime +
                ", name='" + name + '\'' +
                ", studentNum='" + studentNum + '\'' +
                ", attendanceState=" + attendanceState +
                ", course=" + course +
                '}';
    }
}
