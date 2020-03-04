package com.zx.lab_attendance.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.lab_attendance.dao.LeaveclassmMapper;
import com.zx.lab_attendance.entity.Leaveclassm;
import com.zx.lab_attendance.service.LeaveclassmService;
import com.zx.lab_attendance.utils.IdWorker;
import com.zx.lab_attendance.vo.LeaveclassmVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/27 1:20
 * @Description
 */
@Service
public class LeaveclassmServiceImpl implements LeaveclassmService {

    @Autowired
    private LeaveclassmMapper leaveclassmMapper;

    @Override
    public PageInfo<LeaveclassmVO> selectByApproverAndStu(String studentId,int currentPage,int pageSize) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Page page = PageHelper.startPage(currentPage, pageSize);

        List<Leaveclassm> leaveclassms = leaveclassmMapper.selectByApproverAndStu(studentId);
        List<LeaveclassmVO> leaveclassmVOS = new ArrayList<>();

        for(Leaveclassm leaveclassm : leaveclassms) {

            LeaveclassmVO leaveclassmVo = new LeaveclassmVO();
            leaveclassmVo.setLeaveclassmId(leaveclassm.getLeaveclassmId());
            leaveclassmVo.setCourseCode(leaveclassm.getLabusing().getCourse().getCourseName());
            leaveclassmVo.setStudentName(leaveclassm.getStudentUser().getUsername());
            leaveclassmVo.setStudentNum(leaveclassm.getStudentUser().getUserNumber());
            String starttime = sdf.format(leaveclassm.getLabusing().getLabusingDate());
            String endtime = sdf.format(leaveclassm.getLabusing().getLabusingDateend());
            leaveclassmVo.setCourseDate(starttime + "-" + endtime);
            leaveclassmVo.setLeaveReason(leaveclassm.getLeaveReason());
            leaveclassmVo.setLeaveImg(leaveclassm.getLeaveImg());
            leaveclassmVo.setLeavedate(leaveclassm.getLeavedate());
            leaveclassmVo.setCourseName(leaveclassm.getLabusing().getCourse().getCourseName());
            leaveclassmVo.setLeaveStatus(leaveclassm.getLeaveStatus());
            leaveclassmVo.setLeaveClass(leaveclassm.getLeaveClass());
            leaveclassmVOS.add(leaveclassmVo);
        }
        PageInfo info = new PageInfo<LeaveclassmVO>(page.getResult());

//        System.out.println(info.getList().get(0).getClass());
        for (int i =0;i<info.getList().size();i++){
            LeaveclassmVO leaveclassmVo = new LeaveclassmVO();
            Leaveclassm leaveclassm  = (Leaveclassm) info.getList().get(i);
            leaveclassmVo.setLeaveclassmId(leaveclassm.getLeaveclassmId());
            leaveclassmVo.setCourseCode(leaveclassm.getLabusing().getCourse().getCourseName());
            leaveclassmVo.setStudentName(leaveclassm.getStudentUser().getUsername());
            leaveclassmVo.setStudentNum(leaveclassm.getStudentUser().getUserNumber());
            leaveclassmVo.setCourseDate(leaveclassm.getLabusing().getCourse().getCoursestart() + "至" + leaveclassm.getLabusing().getCourse().getCourseend());
            leaveclassmVo.setLeaveReason(leaveclassm.getLeaveReason());
            leaveclassmVo.setLeaveImg(leaveclassm.getLeaveImg());
            leaveclassmVo.setLeavedate(leaveclassm.getLeavedate());
            leaveclassmVo.setCourseName(leaveclassm.getLabusing().getCourse().getCourseName());
            leaveclassmVo.setLeaveStatus(leaveclassm.getLeaveStatus());
            leaveclassmVo.setLeaveClass(leaveclassm.getLeaveClass());
            info.getList().remove(i);
            info.getList().add(i,leaveclassmVo);
        }
        int pages = info.getPages() * 10;
        info.setPages(pages);
//        PageInfo<LeaveclassmVO> pageInfo = new PageInfo<LeaveclassmVO>(leaveclassmVOS);
//        System.out.println(pageInfo.getPages());
        return info;
    }

    @Override
    public String insertReturnTea(Leaveclassm leaveclassm) {
        IdWorker idWorker = new IdWorker(0, 0);
        String id = "LCM" + idWorker.nextId();
        leaveclassm.setLeaveclassmId(id);
        leaveclassm.setLeavedate(new Date());
        leaveclassm.setLeaveStatus(1);
        leaveclassmMapper.insertLeaveclass(leaveclassm);
        return id;
    }
}
