package com.zx.lab_attendance.service.impl;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.zx.lab_attendance.dao.CourseMapper;
import com.zx.lab_attendance.dao.CourseandstuMapper;
import com.zx.lab_attendance.dao.LabusingMapper;
import com.zx.lab_attendance.entity.Course;
import com.zx.lab_attendance.entity.Courseandstu;
import com.zx.lab_attendance.entity.Labusing;
import com.zx.lab_attendance.entity.Users;
import com.zx.lab_attendance.service.LabusingService;
import com.zx.lab_attendance.vo.CalendarVO;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/24 1:47
 * @Description
 */
@Service
public class LabusingServiceImpl implements LabusingService {

    @Autowired
    CourseandstuMapper courseandstuMapper;
    @Autowired
    LabusingMapper labusingMapper;
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Labusing> selectLabusingByMyCourseForTime(String studentId, String starttime, String endtime) {
        List<Courseandstu> courseandstus = courseandstuMapper.selectByStudentIdNoCourse(studentId);
        List<Labusing> labusings = new ArrayList<>();
        for (Courseandstu courseandstu : courseandstus){
            labusings.addAll(labusingMapper.selectByCourseIdAndTime(courseandstu.getCourseId(),starttime,endtime));
        }
        return labusings;
    }

    /**
     * @author: zx
     * @return:
     * @parameter:
     * @describe: 查询学生的课程表
     */
    @Override
    public List<CalendarVO> selectLabBystudent(String studentId) {
        List<Courseandstu> courseandstus = courseandstuMapper.selectByStudentIdNoCourse(studentId);
        List<CalendarVO> calendarVOS = new ArrayList<>();
        String [] color = new String[7];
        color[1] = "aquamarine";
        color[2] = "gold";
        color[3] = "seagreen";
        color[4] = "teal";
        color[5] = "salmon";
        color[6] = "brown";
        color[0] = "Orange";
        for (Courseandstu courseandstu : courseandstus) {
            List<Labusing> labusings = labusingMapper.selectByCourseCode(courseandstu.getCourseId());
            Course course = courseMapper.selectByCourseCode(courseandstu.getCourseId());
            for (int i =0; i<labusings.size(); i++) {
                CalendarVO calendarVO = new CalendarVO();
                calendarVO.setId(labusings.get(i).getLabusingId());
                calendarVO.setTitle(course.getCourseName() + course.getCourseCode() +"[" + course.getUser().getUsername() + "]" + labusings.get(i).getLaboratoryNumber());
                calendarVO.setStart(labusings.get(i).getLabusingDate());
                calendarVO.setEnd(labusings.get(i).getLabusingDateend());
                switch ((i+7) % 7) {
                    case 0:
                        calendarVO.setColor(color[0]);
                        break;
                    case 1:
                        calendarVO.setColor(color[1]);
                        break;
                    case 2:
                        calendarVO.setColor(color[2]);
                        break;
                    case 3:
                        calendarVO.setColor(color[3]);
                        break;
                    case 4:
                        calendarVO.setColor(color[4]);
                        break;
                    case 5:
                        calendarVO.setColor(color[5]);
                        break;
                    case 6:
                        calendarVO.setColor(color[6]);
                        break;
                }
                calendarVOS.add(calendarVO);
            }
        }
        return calendarVOS;
    }

    /**
     * @author: zx
     * @return:
     * @parameter:
     * @describe: 查询老师的课程表
     */
    @Override
    public List<CalendarVO> selectLabByTeacher(String teacherId) {
        List<Course> courses = courseMapper.selectByUserId(teacherId);
        List<CalendarVO> calendarVOS = new ArrayList<>();
        String [] color = new String[7];
        color[1] = "aquamarine";
        color[2] = "gold";
        color[3] = "seagreen";
        color[4] = "teal";
        color[5] = "salmon";
        color[6] = "brown";
        color[0] = "Orange";
        for (Course course : courses){
            List<Labusing> labusings = labusingMapper.selectByCourseCode(course.getCourseCode());
            for (int i = 0;i<labusings.size();i++) {
                CalendarVO calendarVO = new CalendarVO();
                calendarVO.setId(labusings.get(i).getLabusingId());
                calendarVO.setTitle(course.getCourseName() + course.getCourseCode() +"[" + course.getUser().getUsername() + "]" + labusings.get(i).getLaboratoryNumber());
                calendarVO.setStart(labusings.get(i).getLabusingDate());
                calendarVO.setEnd(labusings.get(i).getLabusingDateend());
                switch ((i+7) % 7) {
                    case 0:
                        calendarVO.setColor(color[0]);
                        break;
                    case 1:
                        calendarVO.setColor(color[1]);
                        break;
                    case 2:
                        calendarVO.setColor(color[2]);
                        break;
                    case 3:
                        calendarVO.setColor(color[3]);
                        break;
                    case 4:
                        calendarVO.setColor(color[4]);
                        break;
                    case 5:
                        calendarVO.setColor(color[5]);
                        break;
                    case 6:
                        calendarVO.setColor(color[6]);
                        break;
                }
                calendarVOS.add(calendarVO);
            }
        }
        return calendarVOS;
    }


    /**
     * @author: zx
     * @paramater:
     * @process:
     * @describe: 查询某课室的所有课程安排
     */
    @Override
    public List<CalendarVO> selectLabByLabNum(String labnum) {
        List<Labusing> labusings = labusingMapper.selectByLabNum(labnum);
        List<CalendarVO> calendarVOS = new ArrayList<>();
        String [] color = new String[7];
        color[1] = "aquamarine";
        color[2] = "gold";
        color[3] = "seagreen";
        color[4] = "teal";
        color[5] = "salmon";
        color[6] = "brown";
        color[0] = "Orange";
        for (int i = 0;i<labusings.size();i++) {
            CalendarVO calendarVO = new CalendarVO();
            calendarVO.setId(labusings.get(i).getLabusingId());
            calendarVO.setStart(labusings.get(i).getLabusingDate());
            calendarVO.setEnd(labusings.get(i).getLabusingDateend());
            calendarVO.setTitle(labusings.get(i).getCourse().getCourseName() + labusings.get(i).getCourse().getCourseCode() + "[" +labusings.get(i).getCourse().getUser().getUsername()+ "]" + labusings.get(i).getLaboratoryNumber());
            switch ((i+7) % 7) {
                case 0:
                    calendarVO.setColor(color[0]);
                    break;
                case 1:
                    calendarVO.setColor(color[1]);
                    break;
                case 2:
                    calendarVO.setColor(color[2]);
                    break;
                case 3:
                    calendarVO.setColor(color[3]);
                    break;
                case 4:
                    calendarVO.setColor(color[4]);
                    break;
                case 5:
                    calendarVO.setColor(color[5]);
                    break;
                case 6:
                    calendarVO.setColor(color[6]);
                    break;
            }
            calendarVOS.add(calendarVO);
        }
        return calendarVOS;
    }


}
