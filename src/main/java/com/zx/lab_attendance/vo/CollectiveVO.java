package com.zx.lab_attendance.vo;

import com.zx.lab_attendance.entity.Users;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/10 11:22
 * @Description
 */
public class CollectiveVO {

    //班级id
    private String collectiveId;
    //班级
    private String collective;
    //年级
    private String department;
    //班级人数
    private Integer collectiveNum;
    //专业
    private String major;
    //用户集合
    private List<UserVO> usersList;

    public List<UserVO> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<UserVO> usersList) {
        this.usersList = usersList;
    }

    public String getCollectiveId() {
        return collectiveId;
    }

    public void setCollectiveId(String collectiveId) {
        this.collectiveId = collectiveId;
    }

    public String getCollective() {
        return collective;
    }

    public void setCollective(String collective) {
        this.collective = collective;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getCollectiveNum() {
        return collectiveNum;
    }

    public void setCollectiveNum(Integer collectiveNum) {
        this.collectiveNum = collectiveNum;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
