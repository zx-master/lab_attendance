package com.zx.lab_attendance.service.impl;

import com.zx.lab_attendance.dao.ApplylabMapper;
import com.zx.lab_attendance.dao.ChatmsgMapper;
import com.zx.lab_attendance.dao.LeaveclassmMapper;
import com.zx.lab_attendance.entity.Applylab;
import com.zx.lab_attendance.entity.Chatmsg;
import com.zx.lab_attendance.entity.Leaveclassm;
import com.zx.lab_attendance.service.ChatmsgService;
import com.zx.lab_attendance.vo.ChatmsgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/20 1:02
 * @Description
 */
@Service
public class ChatmsgServiceImpl implements ChatmsgService {

    @Autowired
    private ChatmsgMapper chatmsgMapper;
    @Autowired
    private LeaveclassmMapper leaveclassmMapper;
    @Autowired
    private ApplylabMapper applylabMapper;

    @Override
    public void insert(Chatmsg chatmsg) {
        chatmsgMapper.insert(chatmsg);
    }

    @Override
    public List<Chatmsg> selectNoReceive(String receiveId) {
        return chatmsgMapper.selectNoReceive(receiveId);
    }

    @Override
    public List<ChatmsgInfo> selectChatmsgInfo(String receiveId) {
        List<Chatmsg> chatmsgs = chatmsgMapper.selectNoReceive(receiveId);
        List<ChatmsgInfo> chatmsgInfos = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Chatmsg chatmsg : chatmsgs) {
            ChatmsgInfo chatmsgInfo = new ChatmsgInfo();
            String chatClass = chatmsg.getMsg().substring(0,2);
            String chatId = chatmsg.getMsg().substring(2);
            System.out.println(chatmsg.getMsg().substring(2));
            if (chatClass.equals("请假")) {
                //查询leaveclassm的数据
                Leaveclassm leaveclassm = leaveclassmMapper.selectByPrimaryKey(chatId);
                chatmsgInfo.setUserId(leaveclassm.getStudentId());
                chatmsgInfo.setUserName(leaveclassm.getStudentUser().getUsername());
                chatmsgInfo.setUserNum(leaveclassm.getStudentUser().getUserNumber());
                chatmsgInfo.setChatReason(leaveclassm.getLeaveReason());
                chatmsgInfo.setUserImg(leaveclassm.getStudentUser().getUserImg());
                chatmsgInfo.setCode(leaveclassm.getLabusing().getCourseId());
                chatmsgInfo.setCourseName(leaveclassm.getLabusing().getCourse().getCourseName());
                chatmsgInfo.setDate(
                        sdf.format(leaveclassm.getLabusing().getLabusingDate())
                        + "-" +
                        sdf.format(leaveclassm.getLabusing().getLabusingDateend())
                );
                chatmsgInfo.setId(leaveclassm.getLeaveclassmId());
                chatmsgInfo.setLeaveImg(leaveclassm.getLeaveImg());
                chatmsgInfo.setLeaveClass(leaveclassm.getLeaveClass());
                chatmsgInfo.setChatmsgId(chatmsg.getChatmsgId());
                chatmsgInfo.setStatus(leaveclassm.getLeaveStatus());
                chatmsgInfo.setSign(1);
                chatmsgInfos.add(chatmsgInfo);
            }else if (chatClass.equals("申请")){
                Applylab applylab = applylabMapper.selectByPrimaryKey(chatId);
                chatmsgInfo.setUserId(applylab.getApplyUserid());
                chatmsgInfo.setId(applylab.getApplylabId());
                chatmsgInfo.setUserNum(applylab.getUser().getUserNumber());
                chatmsgInfo.setUserName(applylab.getUser().getUsername());
                chatmsgInfo.setUserImg(applylab.getUser().getUserImg());
                chatmsgInfo.setDate(sdf.format(applylab.getUsingStart()) + "-" + sdf.format(applylab.getUsingEnd()));
                chatmsgInfo.setCode(applylab.getLaboratoryNumber());
                chatmsgInfo.setChatmsgId(chatmsg.getChatmsgId());
                chatmsgInfo.setChatReason(applylab.getUsing());
                chatmsgInfo.setSign(1);
                chatmsgInfos.add(chatmsgInfo);
            }else if (chatClass.equals("请回")){
                Leaveclassm leaveclassm = leaveclassmMapper.selectByPrimaryKey(chatId);
                chatmsgInfo.setUserId(leaveclassm.getStudentId());
                chatmsgInfo.setUserName(leaveclassm.getStudentUser().getUsername());
                chatmsgInfo.setUserNum(leaveclassm.getStudentUser().getUserNumber());
                chatmsgInfo.setChatReason(leaveclassm.getLeaveReason());
                chatmsgInfo.setUserImg(leaveclassm.getStudentUser().getUserImg());
                chatmsgInfo.setCode(leaveclassm.getLabusing().getCourseId());
                chatmsgInfo.setChatmsgId(chatmsg.getChatmsgId());
                chatmsgInfo.setCourseName(leaveclassm.getLabusing().getCourse().getCourseName());
                chatmsgInfo.setDate(sdf.format(leaveclassm.getLabusing().getLabusingDate()) + "-" + sdf.format(leaveclassm.getLabusing().getLabusingDateend()));
                chatmsgInfo.setId(leaveclassm.getLeaveclassmId());
                chatmsgInfo.setLeaveImg(leaveclassm.getLeaveImg());
                chatmsgInfo.setLeaveClass(leaveclassm.getLeaveClass());
                chatmsgInfo.setApproverName(leaveclassm.getTeacherUser().getUsername());
                chatmsgInfo.setApproverImg(leaveclassm.getTeacherUser().getUserImg());
                chatmsgInfo.setStatus(leaveclassm.getLeaveStatus());
                chatmsgInfo.setSign(2);
                chatmsgInfos.add(chatmsgInfo);
            }else if (chatClass.equals("申回")){
                Applylab applylab = applylabMapper.selectByPrimaryKey(chatId);
                chatmsgInfo.setUserId(applylab.getApplyUserid());
                chatmsgInfo.setId(applylab.getApplylabId());
                chatmsgInfo.setUserNum(applylab.getUser().getUserNumber());
                chatmsgInfo.setUserName(applylab.getUser().getUsername());
                chatmsgInfo.setUserImg(applylab.getUser().getUserImg());
                chatmsgInfo.setDate(sdf.format(applylab.getUsingStart()) + "-" + sdf.format(applylab.getUsingEnd()));
                chatmsgInfo.setCode(applylab.getLaboratoryNumber());
                chatmsgInfo.setChatmsgId(chatmsg.getChatmsgId());
                chatmsgInfo.setChatReason(applylab.getUsing());
                chatmsgInfo.setStatus(applylab.getApplylabStatus());
                chatmsgInfo.setApproverName(applylab.getApproverUser().getUsername());
                chatmsgInfo.setApproverImg(applylab.getApproverUser().getUserImg());
                chatmsgInfo.setSign(2);
                chatmsgInfos.add(chatmsgInfo);
            }
        }
        return chatmsgInfos;
    }

    @Override
    public void updateChatmsg(String chatmsgId) {
        Chatmsg chatmsg = new Chatmsg();
        chatmsg.setChatmsgId(chatmsgId);
        chatmsgMapper.updateByPrimaryKey(chatmsg);
    }


}
