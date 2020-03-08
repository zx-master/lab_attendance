package com.zx.lab_attendance.service;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.zx.lab_attendance.entity.Chatmsg;
import com.zx.lab_attendance.vo.ChatmsgInfo;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/20 0:58
 * @Description
 */
public interface ChatmsgService {

    /**
     * @author: zx
     * @return: void
     * @parameter: chatmsg
     * @describe: 插入chatmsg
     */
    public void insert(Chatmsg chatmsg);

    public List<Chatmsg> selectNoReceive(String receiveId);

    public List<ChatmsgInfo> selectChatmsgInfo(String receiveId);

    public void updateChatmsg(String chatmsgId);

}
