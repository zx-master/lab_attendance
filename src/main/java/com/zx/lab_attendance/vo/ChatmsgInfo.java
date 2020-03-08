package com.zx.lab_attendance.vo;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/6 9:30
 * @Description
 */
public class ChatmsgInfo {
    private String chatmsgId;                    //聊天信息id
    private String id;                           //请假/申请数据id
    private String userId;                       //用户id
    private String userName;                     //姓名
    private String userNum;                      //学号/工号
    private String chatReason;                   //请假原因/申请用途
    private Integer leaveClass;                  //请假类别
    private String Code;                         //请假课程代号/实验室代号
    private String courseName;                   //课程名
    private String Date;                         //课程时间/使用时间
    private String LeaveImg;                     //请假图片
    private String userImg;                      //用户头像
    private Integer status;                      //处理状态
    private Integer sign;                        //回复/请求标识
    private String approverName;                     //审核人姓名
    private String approverImg;                      //审核人头像

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    public String getApproverImg() {
        return approverImg;
    }

    public void setApproverImg(String approverImg) {
        this.approverImg = approverImg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChatmsgId() {
        return chatmsgId;
    }

    public void setChatmsgId(String chatmsgId) {
        this.chatmsgId = chatmsgId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getChatReason() {
        return chatReason;
    }

    public void setChatReason(String chatReason) {
        this.chatReason = chatReason;
    }

    public Integer getLeaveClass() {
        return leaveClass;
    }

    public void setLeaveClass(Integer leaveClass) {
        this.leaveClass = leaveClass;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLeaveImg() {
        return LeaveImg;
    }

    public void setLeaveImg(String leaveImg) {
        LeaveImg = leaveImg;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
