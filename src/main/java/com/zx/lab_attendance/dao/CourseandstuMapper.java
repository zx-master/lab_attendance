package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Courseandstu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseandstuMapper {
    int insert(Courseandstu record);

    List<Courseandstu> selectAll();

    Courseandstu selectByStudentId(String studentId,String courseCode);
}