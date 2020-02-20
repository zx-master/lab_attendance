package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Laboratory;
import java.util.List;

public interface LaboratoryMapper {
    int deleteByPrimaryKey(String laboratoryId);

    int insert(Laboratory record);

    Laboratory selectByPrimaryKey(String laboratoryId);

    List<Laboratory> selectAll();

    int updateByPrimaryKey(Laboratory record);
}