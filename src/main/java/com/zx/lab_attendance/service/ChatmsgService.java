package com.zx.lab_attendance.service;

import com.zx.lab_attendance.entity.Chatmsg;

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
    public String insert(Chatmsg chatmsg);

}
