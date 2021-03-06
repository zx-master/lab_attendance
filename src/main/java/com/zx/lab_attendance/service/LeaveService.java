package com.zx.lab_attendance.service;

import com.zx.lab_attendance.entity.Leave;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/19 20:22
 * @Description
 */
public interface LeaveService {
    /**
     * @author: zx
     * @return: 无返回值
     * @parameter:Leave
     * @describe: 向数据表插入数据
     */
    public void insertLeave(Leave leave);

}
