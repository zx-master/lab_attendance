package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Permissions;
import java.util.List;

public interface PermissionsMapper {
    int deleteByPrimaryKey(Integer permissionsId);

    int insert(Permissions record);

    Permissions selectByPrimaryKey(Integer permissionsId);

    List<Permissions> selectAll();

    int updateByPrimaryKey(Permissions record);
}