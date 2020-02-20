package com.zx.lab_attendance.service.impl;

import com.zx.lab_attendance.dao.AttendanceMapper;
import com.zx.lab_attendance.dao.LabusingMapper;
import com.zx.lab_attendance.dao.LeaveMapper;
import com.zx.lab_attendance.entity.Attendance;
import com.zx.lab_attendance.entity.Labusing;
import com.zx.lab_attendance.entity.Leave;
import com.zx.lab_attendance.service.LeaveService;
import com.zx.lab_attendance.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/19 20:24
 * @Description
 */
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private LabusingMapper labusingMapper;
    @Autowired
    private AttendanceMapper attendanceMapper;
    /**
     * @author: zx
     * @paramater:
     * @process:
     * @describe:
     */
    @Override
    public void insertLeave(Leave leave) {
        IdWorker idWorker = new IdWorker(0, 0);
        List<Labusing> labusings = labusingMapper.selectByDateForLeave(leave.getLeaveDatestart(),leave.getLeaveDateend());
        for (Labusing labusing : labusings) {
            Attendance attendance = new Attendance();
            String attendanceID= "AD" + idWorker.nextId();
            attendance.setAttendanceId(attendanceID);
            attendance.setStudentId(leave.getStudentId());
            attendance.setLabusingId(labusing.getLabusingId());
            attendance.setAttendanceRecord(leave.getLeaveClass());
            attendanceMapper.insert(attendance);
        }
        String leaveID = "LE" + idWorker.nextId();
        leave.setLeaveId(leaveID);
        leave.setLeaveStatus(1);
        leaveMapper.insert(leave);

    }
}
