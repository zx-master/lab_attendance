package com.zx.lab_attendance.service;

import com.zx.lab_attendance.entity.Major;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/18 20:33
 * @Description
 */
public interface MajorService {

    List<Major> selectAll();
}
