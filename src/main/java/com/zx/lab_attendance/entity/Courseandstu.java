package com.zx.lab_attendance.entity;

import java.io.Serializable;

public class Courseandstu implements Serializable {
    private String courseandstuId;

    private String studentId;

    private String courseId;

    private String courseNumber;

    private Integer fulltime;

    private static final long serialVersionUID = 1L;

    public Integer getFulltime() {
        return fulltime;
    }

    public void setFulltime(Integer fulltime) {
        this.fulltime = fulltime;
    }

    public String getCourseandstuId() {
        return courseandstuId;
    }

    public void setCourseandstuId(String courseandstuId) {
        this.courseandstuId = courseandstuId == null ? null : courseandstuId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber == null ? null : courseNumber.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", courseandstuId=").append(courseandstuId);
        sb.append(", studentId=").append(studentId);
        sb.append(", courseId=").append(courseId);
        sb.append(", courseNumber=").append(courseNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}