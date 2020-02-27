package com.zx.lab_attendance.service;

import com.github.pagehelper.PageInfo;
import com.zx.lab_attendance.entity.Leaveclassm;
import com.zx.lab_attendance.vo.LeaveclassmVO;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/27 1:19
 * @Description
 */
public interface LeaveclassmService {

    PageInfo<LeaveclassmVO> selectByApproverAndStu(String studentId, int currentPage, int pageSize);
}
