package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Chatmsg;
import java.util.List;

public interface ChatmsgMapper {
    int deleteByPrimaryKey(String chatmsgId);

    int insert(Chatmsg record);

    Chatmsg selectByPrimaryKey(String chatmsgId);

    List<Chatmsg> selectAll();

    int updateByPrimaryKey(Chatmsg record);
}