package com.zx.lab_attendance.service.impl;

import com.zx.lab_attendance.dao.*;
import com.zx.lab_attendance.entity.*;
import com.zx.lab_attendance.service.LeaveService;
import com.zx.lab_attendance.utils.IdWorker;
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
     * @describe:
     */
    @Override
    public void insertLeave(Leave leave) {
        IdWorker idWorker = new IdWorker(0, 0);
        //获取当前系统时间
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        }

    }
}
