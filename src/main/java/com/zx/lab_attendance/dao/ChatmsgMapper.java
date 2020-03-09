package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Chatmsg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatmsgMapper {
    int deleteByPrimaryKey(String chatmsgId);

    int insert(Chatmsg record);

    Chatmsg selectByPrimaryKey(String chatmsgId);

    List<Chatmsg> selectAll();

    int updateByPrimaryKey(Chatmsg record);

    List<Chatmsg> selectNoReceive(String receiveId);

    Chatmsg selectByChatMsg(String msg);
}