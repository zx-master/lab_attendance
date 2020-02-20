package com.zx.lab_attendance.service.impl;

import com.zx.lab_attendance.dao.MajorMapper;
import com.zx.lab_attendance.entity.Major;
import com.zx.lab_attendance.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/18 20:34
 * @Description
 */
@Service
public class MajorServiceImpl implements MajorService {

    @Autowired
    private MajorMapper majorMapper;

    @Override
    public List<Major> selectAll() {
        return majorMapper.selectAll();
    }
}
