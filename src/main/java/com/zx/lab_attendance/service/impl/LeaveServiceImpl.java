package com.zx.lab_attendance.service.impl;

import com.zx.lab_attendance.dao.*;
import com.zx.lab_attendance.entity.*;
import com.zx.lab_attendance.service.LeaveService;
import com.zx.lab_attendance.utils.IdWorker;
import com.zx.lab_attendance.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/19 20:24
 * @Description
 */
@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private LeaveclassmMapper leaveclassmMapper;
    @Autowired
    private CourseandstuMapper courseandstuMapper;
    @Autowired
    private LabusingMapper labusingMapper;
    /**
     * @author: zx
     * @paramater:
     * @process:
     * @describe:  插入请假，并且将涉及的课程一并添加
     */
    @Override
    public List<NewsVO> insertLeave(Leave leave) {
        IdWorker idWorker = new IdWorker(0, 0);
        //获取当前系统时间
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> lists = new ArrayList<>();
        List<NewsVO> newsVOS = new ArrayList<>();
        Leaveclassm leaveclassm = new Leaveclassm();
        String leaveID = "LE" + idWorker.nextId();
        leave.setApprover("");
        leave.setLeaveId(leaveID);
        leave.setLeaveStatus(1);
        leave.setLeavedate(new Date());
        leaveMapper.insert(leave);

        List<Courseandstu> courseandstus = courseandstuMapper.selectByStudentIdNoCourse(leave.getStudentId());
        List<Labusing> labusings = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Courseandstu courseandstu : courseandstus){
            labusings.addAll(labusingMapper.selectByCourseIdAndTime(courseandstu.getCourseId(),formatter.format(leave.getLeaveDatestart()),formatter.format(leave.getLeaveDateend())));
        }
        for (Labusing labusing : labusings){
            Leaveclassm exitLeave = leaveclassmMapper.selectStudentAndLab(leave.getStudentId(),labusing.getLabusingId());
            if (exitLeave == null) {
                NewsVO newsVO = new NewsVO();
                String leaveclassmID = "LCM" + idWorker.nextId();
                leaveclassm.setApprover(labusing.getCourse().getCourseTeacher());
                leaveclassm.setLabusing(labusing);
                leaveclassm.setLabusingId(labusing.getLabusingId());
                leaveclassm.setLeaveClass(leave.getLeaveClass());
                leaveclassm.setLeavedate(leave.getLeavedate());
                leaveclassm.setLeaveImg(leave.getLeaveImg());
                leaveclassm.setLeaveclassmId(leaveclassmID);
                leaveclassm.setLeaveReason(leave.getLeaveReason());
                leaveclassm.setLeaveStatus(1);
                leaveclassm.setStudentId(leave.getStudentId());
                leaveclassmMapper.insertLeaveclass(leaveclassm);
                newsVO.setContentId(leaveclassmID);
                newsVO.setReceiverId(labusing.getCourse().getCourseTeacher());
                newsVOS.add(newsVO);
            }
        }
        return newsVOS;
    }
}
