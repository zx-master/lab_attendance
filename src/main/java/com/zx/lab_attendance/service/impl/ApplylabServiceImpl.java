package com.zx.lab_attendance.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zx.lab_attendance.dao.*;
import com.zx.lab_attendance.entity.*;
import com.zx.lab_attendance.service.ApplylabService;
import com.zx.lab_attendance.utils.IdWorker;
import com.zx.lab_attendance.vo.ApplyLabVO;
import com.zx.lab_attendance.vo.ChatmsgInfo;
import com.zx.lab_attendance.vo.LeaveclassmVO;
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
 * @date 2020/3/5 0:38
 * @Description
 */
@Service
public class ApplylabServiceImpl implements ApplylabService {

    @Autowired
    private ApplylabMapper applylabMapper;
    @Autowired
    private ChatmsgMapper chatmsgMapper;
    @Autowired
    private LabusingMapper labusingMapper;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public NewsVO insertApplyLab(Applylab applylab) {
        IdWorker idWorker = new IdWorker(0, 0);
        String applylabId = "AL" +idWorker.nextId();
        Users users = usersMapper.selectByPrimaryKey(applylab.getApplyUserid());
        applylab.setApprover("US411214137146736651");
        NewsVO newsVO = new NewsVO();
        newsVO.setContentId(applylabId);
        newsVO.setReceiverId("US411214137146736651");
        applylab.setApplylabId(applylabId);
        applylab.setApplylabdate(new Date());
        applylabMapper.insert(applylab);
        Labusing labusing = new Labusing();
        labusing.setCourseId("活动，申请人:" + users.getUsername()+"(审核中)");        //活动名称
        labusing.setLaboratoryNumber(applylab.getLaboratoryNumber());       //课室编号
        labusing.setLabusingDate(applylab.getUsingStart());           //开始使用时间
        labusing.setLabusingDateend(applylab.getUsingEnd());        //结束使用时间
        String labusingId = "LU" + idWorker.nextId();
        labusing.setLabusingId(labusingId);             //实验室使用id
        labusingMapper.insert(labusing);
        return newsVO;
    }

    @Override
    public void updateApplyLab(ChatmsgInfo applylab) {
        Applylab applylab1 = new Applylab();
        applylab1.setApplylabId(applylab.getId());
        applylab1.setApplylabStatus(applylab.getStatus());
        Chatmsg chatmsg = new Chatmsg();
        chatmsg.setChatmsgId(applylab.getChatmsgId());
        chatmsg.setReceiveSign(1);
        chatmsgMapper.updateByPrimaryKey(chatmsg);
        applylabMapper.updateByPrimaryKey(applylab1);
        if (applylab.getStatus() == 2) {             //批准，修改labusing
            Applylab timeapply = applylabMapper.selectByPrimaryKey(applylab.getId());
            Date starttime = timeapply.getUsingStart();
            Date endtime = timeapply.getUsingEnd();
            String labnum = timeapply.getLaboratoryNumber();
//            Labusing labusing = new Labusing();
//            labusing.setLabusingId(applylab.getId());
//            labusing.setCourseId(applylab.getChatReason());
            labusingMapper.updateCourseId(applylab.getChatReason(),starttime,endtime,labnum);
        }else if(applylab.getStatus() == 3){           //未批准，删除labusing
            labusingMapper.deleteByPrimaryKey(applylab.getId());
        }
    }

    @Override
    public PageInfo<ApplyLabVO> selectApplyLabByUserId(String usreId, int currentPage, int pageSize) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Page page = PageHelper.startPage(currentPage, pageSize);

        List<Applylab> applylabs = applylabMapper.selectAllByUserId(usreId);
        List<ApplyLabVO> applylabVOS = new ArrayList<>();

        for(Applylab applylab : applylabs) {
            ApplyLabVO applyLabVO = new ApplyLabVO();
            applyLabVO.setApprover(applylab.getApproverUser().getUsername());
            applyLabVO.setLabCode(applylab.getLaboratoryNumber());
            applyLabVO.setLabReason(applylab.getUsing());
            applyLabVO.setStudentName(applylab.getUser().getUsername());
            applyLabVO.setStudentNum(applylab.getUser().getUserNumber());
            applyLabVO.setApplylabdate(applylab.getApplylabdate());
            applyLabVO.setStatus(applylab.getApplylabStatus());
            String starttime = sdf.format(applylab.getUsingStart()) + " ";
            String endtime = sdf.format(applylab.getUsingEnd()) + " ";
            applyLabVO.setLabDate(starttime + "-" + endtime);
        }
        PageInfo info = new PageInfo<ApplyLabVO>(page.getResult());

        for (int i =0;i<info.getList().size();i++){
            ApplyLabVO applyLabVO = new ApplyLabVO();
            Applylab applylab  = (Applylab) info.getList().get(i);
            applyLabVO.setApprover(applylab.getApproverUser().getUsername());
            applyLabVO.setLabCode(applylab.getLaboratoryNumber());
            applyLabVO.setLabReason(applylab.getUsing());
            applyLabVO.setApplylabdate(applylab.getApplylabdate());
            applyLabVO.setStudentName(applylab.getUser().getUsername());
            applyLabVO.setStudentNum(applylab.getUser().getUserNumber());
            applyLabVO.setStatus(applylab.getApplylabStatus());
            String starttime = sdf.format(applylab.getUsingStart()) + " ";
            String endtime = sdf.format(applylab.getUsingEnd()) + " ";
            applyLabVO.setLabDate(starttime + "-" + endtime);
            info.getList().remove(i);
            info.getList().add(i,applyLabVO);
        }
        int pages = info.getPages() * 10;
        info.setPages(pages);
        return info;
    }
}
