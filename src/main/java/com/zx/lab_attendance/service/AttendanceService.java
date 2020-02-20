package com.zx.lab_attendance.service;

import com.zx.lab_attendance.entity.Attendance;
import com.zx.lab_attendance.vo.CourseAttendance;
import com.zx.lab_attendance.vo.PersonalAttendance;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/11 12:37
 * @Description
 */
public interface AttendanceService {

    /**
     * @author: zx
     * @return: 1为插入成功
     * @parameter: attendance
     * @describe:新增插入信息
     */
    int insertAttendance(Attendance attendance);

    /**
     * @author: zx
     * @return: 1为修改成功
     * @parameter: attendance表的唯一标识
     * @describe: 修改相关字段
     */
    int updateAttendance(Attendance attendance);

    /**
     * @author: zx
     * @return: List<AttendanceVO>
     * @parameter: attendance
     * @describe: 查看学生/教师/课程考勤情况
     */
    List<Attendance> selectAttendance(Attendance attendance);

    /**
     * @author: zx
     * @return: List<Map<String,List<PersonalAttendance>>>
     * @parameter: 班级id，时间段开始时间，结束时间
     * @describe: 根据班级查看时间
     */
    List<Map<String,Map<String,List<PersonalAttendance>>>> selectByTimeAndCollective(String collectiveid, String starttime, String endtime);

    /**
     * @author: zx
     * @return: List<PersonalAttendance>
     * @parameter: 学生学号/教师工号
     * @describe: 根据userNum查看个人考勤
     */
    List<PersonalAttendance> selectByUserNum(String userNum,String strattime,String endtime);

    /**
     * @author: zx
     * @return:  List<CourseAttendance>
     * @parameter: 课程代码
     * @describe: 查看课程的考勤情况
     */
    List<CourseAttendance> selectByCourseCode(String courseCode,String starttime,String endtime);

    /**
     * @author: zx
     * @return: List<Map<String,Double>>
     * @parameter:
     * @describe: 查看全校考勤上周与这周的百分比
     */
    List<Double> selectOutputPercentage() throws ParseException;

    /**
     * @author: zx
     * @return: List<Map<Integer,Integer>>
     * @parameter:
     * @describe: 查看各年级缺勤人数占比
     */
    List<Map> selectAbsenteeism(String starttime, String endtime);

}
