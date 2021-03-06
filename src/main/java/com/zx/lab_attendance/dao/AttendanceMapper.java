package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Attendance;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceMapper {

    int deleteByPrimaryKey(String attendanceId);

    int insert(Attendance record);

    Attendance selectByPrimaryKey(String attendanceId);

    List<Attendance> selectAll();

    int updateByPrimaryKey(Attendance record);

    List<Attendance> selectByConditions(Attendance attendance);

    List<Attendance> selectByStudentID(String studentid, String starttime,String endtime);

    List<Attendance> selectByCourseCode(String labusing,String starttime,String endtime);

    List<Attendance> selectByDateAndStaus(String date);

    List<Attendance> selectByDate(String date);

    List<Attendance> selectByStaus(String starttime,String endtime);
}