package com.zx.lab_attendance.vo;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/5 1:32
 * @Description
 */
public class UserAttendance {

    private String userName;
    private String userNumber;
    private int attendanceRecord;
    private String labusingId;
    private String studentId;
    private String teacherId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getLabusingId() {
        return labusingId;
    }

    public void setLabusingId(String labusingId) {
        this.labusingId = labusingId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public int getAttendanceRecord() {
        return attendanceRecord;
    }

    public void setAttendanceRecord(int attendanceRecord) {
        this.attendanceRecord = attendanceRecord;
    }
}
