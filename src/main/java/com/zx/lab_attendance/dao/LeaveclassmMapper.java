package com.zx.lab_attendance.dao;



import com.zx.lab_attendance.entity.Chatmsg;
import com.zx.lab_attendance.entity.Labusing;
import com.zx.lab_attendance.entity.Leaveclassm;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/26 16:58
 * @Description
 */
@Repository
public interface LeaveclassmMapper  {

    //新增数据
    void insertLeaveclass(Leaveclassm leaveclassm);
    //分页查询根据接收人查询数据
    List<Leaveclassm> selectByApproverAndStu(String studentId);

    //查询根据labusingid
    List<Leaveclassm> selectAllBylabusing(String labusingid);

    //删除未处理的请假
    void delectLabusing(String labusingId);

    Leaveclassm selectByPrimaryKey(String LeaveclassmId);

    void updateLeaveclassm(Leaveclassm leaveclassm);

    Leaveclassm selectStudentAndLab(String studentId, String labusingId);
}
