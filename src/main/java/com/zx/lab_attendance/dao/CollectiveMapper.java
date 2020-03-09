package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Collective;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectiveMapper {
    int deleteByPrimaryKey(String collectiveId);

    int insert(Collective record);

    Collective selectByPrimaryKey(String collectiveId);

    List<Collective> selectAll();

    int updateByPrimaryKey(Collective record);

    Collective selectNumber(Integer number,String departmentId);
}