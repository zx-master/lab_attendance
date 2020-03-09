package com.zx.lab_attendance.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.lab_attendance.dao.AttendanceMapper;
import com.zx.lab_attendance.dao.ChatmsgMapper;
import com.zx.lab_attendance.dao.LabusingMapper;
import com.zx.lab_attendance.dao.LeaveclassmMapper;
import com.zx.lab_attendance.entity.Attendance;
import com.zx.lab_attendance.entity.Chatmsg;
import com.zx.lab_attendance.entity.Labusing;
import com.zx.lab_attendance.entity.Leaveclassm;
import com.zx.lab_attendance.service.ChatmsgService;
import com.zx.lab_attendance.service.LeaveclassmService;
import com.zx.lab_attendance.utils.IdWorker;
import com.zx.lab_attendance.vo.ChatmsgInfo;
import com.zx.lab_attendance.vo.LeaveInfoVO;
import com.zx.lab_attendance.vo.LeaveclassmVO;
import com.zx.lab_attendance.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class LeaveclassmServiceImpl implements LeaveclassmService {

    @Autowired
    private LeaveclassmMapper leaveclassmMapper;
    @Autowired
    private ChatmsgMapper chatmsgMapper;
    @Autowired
    private LabusingMapper labusingMapper;
    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public PageInfo<LeaveclassmVO> selectByApproverAndStu(String studentId,int currentPage,int pageSize) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Page page = PageHelper.startPage(currentPage, pageSize);

        List<Leaveclassm> leaveclassms = leaveclassmMapper.selectByApproverAndStu(studentId);
        List<LeaveclassmVO> leaveclassmVOS = new ArrayList<>();

        for(Leaveclassm leaveclassm : leaveclassms) {
//            if (leaveclassm.getLeaveStatus() != 4) {
                LeaveclassmVO leaveclassmVo = new LeaveclassmVO();
                leaveclassmVo.setLeaveclassmId(leaveclassm.getLeaveclassmId());
                leaveclassmVo.setCourseCode(leaveclassm.getLabusing().getCourse().getCourseName());
                leaveclassmVo.setStudentName(leaveclassm.getStudentUser().getUsername());
                leaveclassmVo.setStudentNum(leaveclassm.getStudentUser().getUserNumber());
                leaveclassmVo.setApproverId(leaveclassm.getTeacherUser().getUserId());
                leaveclassmVo.setApprover(leaveclassm.getTeacherUser().getUsername());
                String starttime = sdf.format(leaveclassm.getLabusing().getLabusingDate()) + " ";
                String endtime = sdf.format(leaveclassm.getLabusing().getLabusingDateend()) + " ";
                leaveclassmVo.setCourseDate(starttime + "-" + endtime);
                leaveclassmVo.setLeaveReason(leaveclassm.getLeaveReason());
                leaveclassmVo.setLeaveImg(leaveclassm.getLeaveImg());
                leaveclassmVo.setLeavedate(leaveclassm.getLeavedate());
                leaveclassmVo.setCourseName(leaveclassm.getLabusing().getCourse().getCourseName());
                leaveclassmVo.setLeaveStatus(leaveclassm.getLeaveStatus());
                leaveclassmVo.setLeaveClass(leaveclassm.getLeaveClass());
                leaveclassmVOS.add(leaveclassmVo);
//            }
        }
        PageInfo info = new PageInfo<LeaveclassmVO>(page.getResult());


        for (int i =0;i<info.getList().size();i++){
            Leaveclassm leaveclassm  = (Leaveclassm) info.getList().get(i);
//            if (leaveclassm.getLeaveStatus() != 4) {
                LeaveclassmVO leaveclassmVo = new LeaveclassmVO();
                leaveclassmVo.setLeaveclassmId(leaveclassm.getLeaveclassmId());
                leaveclassmVo.setApproverId(leaveclassm.getTeacherUser().getUserId());
                leaveclassmVo.setCourseCode(leaveclassm.getLabusing().getCourse().getCourseName());
                leaveclassmVo.setApprover(leaveclassm.getTeacherUser().getUsername());
                leaveclassmVo.setStudentName(leaveclassm.getStudentUser().getUsername());
                leaveclassmVo.setStudentNum(leaveclassm.getStudentUser().getUserNumber());
                String starttime = sdf.format(leaveclassm.getLabusing().getLabusingDate()) + " ";
                String endtime = sdf.format(leaveclassm.getLabusing().getLabusingDateend()) + " ";
                leaveclassmVo.setCourseDate(starttime + "-" + endtime);
                leaveclassmVo.setLeaveReason(leaveclassm.getLeaveReason());
                leaveclassmVo.setLeaveImg(leaveclassm.getLeaveImg());
                leaveclassmVo.setLeavedate(leaveclassm.getLeavedate());
                leaveclassmVo.setCourseName(leaveclassm.getLabusing().getCourse().getCourseName());
                leaveclassmVo.setLeaveStatus(leaveclassm.getLeaveStatus());
                leaveclassmVo.setLeaveClass(leaveclassm.getLeaveClass());
                info.getList().remove(i);
                info.getList().add(i, leaveclassmVo);
//            }
        }
        int pages = info.getPages() * 10;
        info.setPages(pages);
        return info;
    }

    /**
     * @author: zx
     * @paramater:
     * @process:
     * @describe: 插入数据，返回接收者老师的id和内容id
     */
    @Override
    public NewsVO insertReturnTea(Leaveclassm leaveclassm) {
        IdWorker idWorker = new IdWorker(0, 0);
        NewsVO newsVO = new NewsVO();
        String id = "LCM" + idWorker.nextId();
        leaveclassm.setLeaveclassmId(id);
        leaveclassm.setLeavedate(new Date());
        leaveclassm.setLeaveStatus(1);
        Leaveclassm exitLeave = leaveclassmMapper.selectStudentAndLab(leaveclassm.getStudentId(),leaveclassm.getLabusingId());
        if ( exitLeave.getLeaveclassmId() != "") {
            Labusing labusing = labusingMapper.selectByPrimaryKey(leaveclassm.getLabusingId());
            leaveclassm.setApprover(labusing.getCourse().getCourseTeacher());
            leaveclassmMapper.insertLeaveclass(leaveclassm);
            newsVO.setContentId(id);
            newsVO.setReceiverId(labusing.getCourse().getCourseTeacher());
            return newsVO;
        }else{
            return null;
        }

    }

    @Override
    public List<LeaveInfoVO> selectLeaveInfo(String userId) {
        List<Chatmsg> chatmsgs = chatmsgMapper.selectNoReceive(userId);
        return null;
    }

    @Override
    public void updateLeaveclassm(ChatmsgInfo chatmsgInfo) {
        Leaveclassm leaveclassmInfo = leaveclassmMapper.selectByPrimaryKey(chatmsgInfo.getId());
        Leaveclassm leaveclassm = new Leaveclassm();
        leaveclassm.setLeaveclassmId(chatmsgInfo.getId());
        leaveclassm.setLeaveStatus(chatmsgInfo.getStatus());
        leaveclassmMapper.updateLeaveclassm(leaveclassm);
        Chatmsg chatmsg = new Chatmsg();
        chatmsg.setChatmsgId(chatmsgInfo.getChatmsgId());
        chatmsgMapper.updateByPrimaryKey(chatmsg);
        if (chatmsgInfo.getStatus() == 2) {
            IdWorker idWorker = new IdWorker(0, 0);
            String id = "AD" + idWorker.nextId();
            Attendance attendance = new Attendance();
            attendance.setAttendanceId(id);
            attendance.setLabusingId(leaveclassmInfo.getLabusingId());
            attendance.setTeacherId(chatmsgInfo.getApproverId());
            attendance.setStudentId(chatmsgInfo.getUserId());
            attendance.setAttendanceRecord(leaveclassmInfo.getLeaveStatus());
            attendance.setAttendanceDate(new Date());
            attendanceMapper.insert(attendance);
        }else if(chatmsgInfo.getStatus() == 4) {
            Leaveclassm leaveclassm1 = leaveclassmMapper.selectByPrimaryKey(chatmsgInfo.getId());
            attendanceMapper.deleteByLabusingAndUser(leaveclassm1.getLabusingId(),chatmsgInfo.getUserId());
        }

    }


}
