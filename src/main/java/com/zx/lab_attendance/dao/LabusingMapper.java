package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Labusing;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LabusingMapper {
    int deleteByPrimaryKey(String labusingId);

    int insert(Labusing record);

    Labusing selectByPrimaryKey(String labusingId);

    List<Labusing> selectAll();

    int updateByPrimaryKey(Labusing record);

    List<Labusing> selectByCourseCode(String courseCode);

    List<Labusing> selectByDateForLeave(Date starttime, Date endtime);
}