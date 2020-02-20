package com.zx.lab_attendance.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;

import java.util.Date;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/11 22:34
 * @Description 选择课程时，返回的考勤信息
 */
public class CourseAttendance {
    /**
     * 课程编号
     */
    private String courseNum;
    /**
     * 课程名
     */
    private String courseName;
    /**
     * 课程班级
     */
    private String courseClass;
    /**
     * 日期
     */
    private Date courseDate;
    /**
     * 课堂人数
     */
    private Integer courseManNum;
    /**
     * 迟到人数
     */
    private Integer lateNum;
     /**
     * 病假人数
     */
    private Integer sickNum;
    /**
     * 事假人数
     */
    private Integer affairLeaveNum;

    /**
     * 缺勤人数
     */
    private Integer absenteeism;

    public Integer getAbsenteeism() {
        return absenteeism;
    }

    public void setAbsenteeism(Integer absenteeism) {
        this.absenteeism = absenteeism;
    }

    public String getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(String courseClass) {
        this.courseClass = courseClass;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getCourseDate() {
        return courseDate;
    }

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public void setCourseDate(Date courseDate) {
        this.courseDate = courseDate;
    }

    public Integer getCourseManNum() {
        return courseManNum;
    }

    public void setCourseManNum(Integer courseManNum) {
        this.courseManNum = courseManNum;
    }

    public Integer getLateNum() {
        return lateNum;
    }

    public void setLateNum(Integer lateNum) {
        this.lateNum = lateNum;
    }

    public Integer getSickNum() {
        return sickNum;
    }

    public void setSickNum(Integer sickNum) {
        this.sickNum = sickNum;
    }

    public Integer getAffairLeaveNum() {
        return affairLeaveNum;
    }

    public void setAffairLeaveNum(Integer affairLeaveNum) {
        this.affairLeaveNum = affairLeaveNum;
    }
}
