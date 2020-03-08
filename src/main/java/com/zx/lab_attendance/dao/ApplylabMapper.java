package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Applylab;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplylabMapper {
    int deleteByPrimaryKey(String applylabId);

    int insert(Applylab record);

    Applylab selectByPrimaryKey(String applylabId);

    List<Applylab> selectAll();

    int updateByPrimaryKey(Applylab record);

    List<Applylab> selectAllByUserId(String userid);
}