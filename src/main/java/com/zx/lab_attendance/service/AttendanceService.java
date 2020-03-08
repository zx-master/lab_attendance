package com.zx.lab_attendance.service;

import com.zx.lab_attendance.entity.Attendance;
import com.zx.lab_attendance.vo.CourseAttendance;
import com.zx.lab_attendance.vo.PersonalAttendance;
import com.zx.lab_attendance.vo.StudentAttendance;
import com.zx.lab_attendance.vo.UserAttendance;

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
     * @return:
     * @parameter:
     * @describe: 新增考勤信息
     */
    void insertAttendanceList(List<UserAttendance> attendances);

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

    /**
     * @author: zx
     * @return:
     * @parameter:
     * @describe: 查询所有课程的考勤
     */
    List<CourseAttendance> selectAllAttendanceCourse();


    /**
     * @author: zx
     * @return:
     * @parameter:
     * @describe: 查看所有学生的考勤统计
     */
    List<StudentAttendance> selectAllStuAttendance();

    /**
     * @author: zx
     * @return:
     * @parameter:
     * @describe: 查询学生的某课程的考勤记录
     */
    List<PersonalAttendance> selectStudentCollect(String courseCode,String userNum,String starttime,String endtime);

    /**
     * @author: zx
     * @return:
     * @parameter:
     * @describe: 查询教师的某课程的考勤记录
     */
    List<CourseAttendance> selectTeacherCollect(String courseCode,String userNum,String starttime,String endtime);

    /**
     * @author: zx
     * @paramater:
     * @process:
     * @describe: 统计老师的所有课的数据
     */
    List<CourseAttendance> selectTeacherAllCourse(String userId);

    /**
     * @author: zx
     * @return:
     * @parameter:
     * @describe: 统计学生的所有课的数据
     */
    List<CourseAttendance> selectStudentAllCourse(String userId);
}
