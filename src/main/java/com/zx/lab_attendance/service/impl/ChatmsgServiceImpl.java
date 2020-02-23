package com.zx.lab_attendance.service.impl;

import com.zx.lab_attendance.dao.ChatmsgMapper;
import com.zx.lab_attendance.entity.Chatmsg;
import com.zx.lab_attendance.service.ChatmsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/20 1:02
 * @Description
 */
@Service
public class ChatmsgServiceImpl implements ChatmsgService {

    @Autowired
    private ChatmsgMapper chatmsgMapper;

    @Override
    public String insert(Chatmsg chatmsg) {
        chatmsgMapper.insert(chatmsg);
        //返回主键
        return null;
    }
}
