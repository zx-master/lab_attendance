package com.zx.lab_attendance.entity;

import java.io.Serializable;
import java.util.Date;

public class Attendance implements Serializable {
    private String attendanceId;

    private String studentId;

    private String labusingId;

    private String teacherId;

    private Date attendanceDate;

    private Integer attendanceRecord;

    private static final long serialVersionUID = 1L;

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId == null ? null : attendanceId.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getLabusingId() {
        return labusingId;
    }

    public void setLabusingId(String labusingId) {
        this.labusingId = labusingId == null ? null : labusingId.trim();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Integer getAttendanceRecord() {
        return attendanceRecord;
    }

    public void setAttendanceRecord(Integer attendanceRecord) {
        this.attendanceRecord = attendanceRecord;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", attendanceId=").append(attendanceId);
        sb.append(", studentId=").append(studentId);
        sb.append(", labusingId=").append(labusingId);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", attendanceDate=").append(attendanceDate);
        sb.append(", attendanceRecord=").append(attendanceRecord);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}