package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Perandroles;
import java.util.List;

public interface PerandrolesMapper {
    int deleteByPrimaryKey(Integer perandrolesId);

    int insert(Perandroles record);

    Perandroles selectByPrimaryKey(Integer perandrolesId);

    List<Perandroles> selectAll();

    int updateByPrimaryKey(Perandroles record);
}