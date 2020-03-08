package com.zx.lab_attendance.service;

import com.zx.lab_attendance.entity.Laboratory;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/5 13:24
 * @Description
 */
public interface LaboratoryService {
    /**
     * @author: zx
     * @return:
     * @parameter:
     * @describe: 查询所有实验室信息
     */
    List<Laboratory> selectAllLab();
}
