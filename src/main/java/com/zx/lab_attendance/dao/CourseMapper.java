package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    int deleteByPrimaryKey(String courseId);

    int insert(Course record);

    Course selectByPrimaryKey(String courseId);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

    Course selectByCourseCode(String courseCode);
}