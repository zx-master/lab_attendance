package com.zx.lab_attendance.service;

import com.zx.lab_attendance.dao.CourseandstuMapper;
import com.zx.lab_attendance.dao.LabusingMapper;
import com.zx.lab_attendance.entity.Labusing;
import com.zx.lab_attendance.vo.CalendarVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/24 1:47
 * @Description
 */
public interface LabusingService {

    List<Labusing> selectLabusingByMyCourseForTime(String studentId, String starttime, String endtime);

    /**
     * @author: zx
     * @paramater:
     * @process:
     * @describe: 查看学生的课程表
     */
    List<CalendarVO> selectLabBystudent(String studentId);

    /**
     * @author: zx
     * @paramater:
     * @process:
     * @describe: 查询老师的课程表
     */
    List<CalendarVO> selectLabByTeacher(String teacherId);

    /**
     * @author: zx
     * @return:
     * @parameter:
     * @describe: 根据课室查询所有安排在这课室的课
     */
    List<CalendarVO> selectLabByLabNum(String labnum);

}
