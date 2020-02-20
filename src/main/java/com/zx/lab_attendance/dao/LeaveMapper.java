package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Leave;
import java.util.List;

public interface LeaveMapper {
    int deleteByPrimaryKey(String leaveId);

    int insert(Leave record);

    Leave selectByPrimaryKey(String leaveId);

    List<Leave> selectAll();

    int updateByPrimaryKey(Leave record);
}