package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Applylab;
import java.util.List;

public interface ApplylabMapper {
    int deleteByPrimaryKey(String applylabId);

    int insert(Applylab record);

    Applylab selectByPrimaryKey(String applylabId);

    List<Applylab> selectAll();

    int updateByPrimaryKey(Applylab record);
}