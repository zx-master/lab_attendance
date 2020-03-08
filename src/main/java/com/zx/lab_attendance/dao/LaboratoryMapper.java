package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Laboratory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratoryMapper {
    int deleteByPrimaryKey(String laboratoryId);

    int insert(Laboratory record);

    Laboratory selectByPrimaryKey(String laboratoryId);

    List<Laboratory> selectAll();

    int updateByPrimaryKey(Laboratory record);
}