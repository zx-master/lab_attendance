package com.zx.lab_attendance.entity;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable {
    private String courseId;

    private String courseName;

    private String courseCode;

    private String courseTeacher;

    private Integer courseOfweek;

    private Integer courseTotal;

    private Date coursestart;

    private Date courseend;

    @Override
    public boolean equals(Object obj) {
        Course c = (Course) obj;
        return courseCode.equals(c.courseCode);
    }
    @Override
    public int hashCode() {
        String in = courseCode;
        return in.hashCode();
    }

    public Date getCoursestart() {
        return coursestart;
    }

    public void setCoursestart(Date coursestart) {
        this.coursestart = coursestart;
    }

    public Date getCourseend() {
        return courseend;
    }

    public void setCourseend(Date courseend) {
        this.courseend = courseend;
    }

    private static final long serialVersionUID = 1L;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode == null ? null : courseCode.trim();
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher == null ? null : courseTeacher.trim();
    }

    public Integer getCourseOfweek() {
        return courseOfweek;
    }

    public void setCourseOfweek(Integer courseOfweek) {
        this.courseOfweek = courseOfweek;
    }

    public Integer getCourseTotal() {
        return courseTotal;
    }

    public void setCourseTotal(Integer courseTotal) {
        this.courseTotal = courseTotal;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseId=").append(courseId);
        sb.append(", courseName=").append(courseName);
        sb.append(", courseCode=").append(courseCode);
        sb.append(", courseTeacher=").append(courseTeacher);
        sb.append(", courseOfweek=").append(courseOfweek);
        sb.append(", courseTotal=").append(courseTotal);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}