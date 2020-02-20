package com.zx.lab_attendance.entity;

import java.io.Serializable;
import java.util.Date;

public class Labusing implements Serializable {
    private String labusingId;

    private Date labusingDate;

    private Date labusingDateend;

    private String courseId;

    private String laboratoryNumber;

    private static final long serialVersionUID = 1L;

    public String getLabusingId() {
        return labusingId;
    }

    public void setLabusingId(String labusingId) {
        this.labusingId = labusingId == null ? null : labusingId.trim();
    }

    public Date getLabusingDate() {
        return labusingDate;
    }

    public void setLabusingDate(Date labusingDate) {
        this.labusingDate = labusingDate;
    }

    public Date getLabusingDateend() {
        return labusingDateend;
    }

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