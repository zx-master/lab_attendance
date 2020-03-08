package com.zx.lab_attendance.service.impl;

import com.zx.lab_attendance.dao.LaboratoryMapper;
import com.zx.lab_attendance.entity.Laboratory;
import com.zx.lab_attendance.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/5 13:26
 * @Description
 */
@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    LaboratoryMapper laboratoryMapper;

    @Override
    public List<Laboratory> selectAllLab() {
        return laboratoryMapper.selectAll();
    }
}
