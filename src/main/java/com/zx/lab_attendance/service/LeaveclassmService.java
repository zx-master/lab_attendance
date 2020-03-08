package com.zx.lab_attendance.service;

import com.github.pagehelper.PageInfo;
import com.zx.lab_attendance.entity.Leaveclassm;
import com.zx.lab_attendance.vo.ChatmsgInfo;
import com.zx.lab_attendance.vo.LeaveInfoVO;
import com.zx.lab_attendance.vo.LeaveclassmVO;
import com.zx.lab_attendance.vo.NewsVO;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/27 1:19
 * @Description
 */
public interface LeaveclassmService {

    PageInfo<LeaveclassmVO> selectByApproverAndStu(String studentId, int currentPage, int pageSize);

    NewsVO insertReturnTea(Leaveclassm leaveclassm);

    List<LeaveInfoVO> selectLeaveInfo(String userId);

    void updateLeaveclassm(ChatmsgInfo chatmsgInfo);

}
