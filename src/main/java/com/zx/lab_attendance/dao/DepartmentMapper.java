package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Department;
import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String departmentId);

    int insert(Department record);

    Department selectByPrimaryKey(String departmentId);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);
}