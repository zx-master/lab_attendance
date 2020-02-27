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
}
