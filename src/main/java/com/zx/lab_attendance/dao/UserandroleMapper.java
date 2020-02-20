package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Userandrole;
import java.util.List;

public interface UserandroleMapper {
    int deleteByPrimaryKey(Integer userandroleId);

    int insert(Userandrole record);

    Userandrole selectByPrimaryKey(Integer userandroleId);

    List<Userandrole> selectAll();

    int updateByPrimaryKey(Userandrole record);
}