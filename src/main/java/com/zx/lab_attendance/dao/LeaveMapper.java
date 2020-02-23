package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Leave;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveMapper {
    int deleteByPrimaryKey(String leaveId);

    int insert(Leave record);

    Leave selectByPrimaryKey(String leaveId);

    List<Leave> selectAll();

    int updateByPrimaryKey(Leave record);
}