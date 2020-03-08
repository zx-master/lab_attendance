package com.zx.lab_attendance.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Labusing implements Serializable {
    private String labusingId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date labusingDate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date labusingDateend;

    private String courseId;

    private String laboratoryNumber;

    private Course course;

    private static final long serialVersionUID = 1L;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getLabusingId() {
        return labusingId;
    }

    public void setLabusingId(String labusingId) {
        this.labusingId = labusingId == null ? null : labusingId.trim();
    }

    public Date getLabusingDate() {
        return labusingDate;
    }
//    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public void setLabusingDate(Date labusingDate) {
        this.labusingDate = labusingDate;
    }

    public Date getLabusingDateend() {
        return labusingDateend;
    }
//    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public void setLabusingDateend(Date labusingDateend) {
        this.labusingDateend = labusingDateend;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId == null ? null : courseId.trim();
    }

    public String getLaboratoryNumber() {
        return laboratoryNumber;
    }

    public void setLaboratoryNumber(String laboratoryNumber) {
        this.laboratoryNumber = laboratoryNumber == null ? null : laboratoryNumber.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", labusingId=").append(labusingId);
        sb.append(", labusingDate=").append(labusingDate);
        sb.append(", labusingDateend=").append(labusingDateend);
        sb.append(", courseId=").append(courseId);
        sb.append(", laboratoryNumber=").append(laboratoryNumber);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}