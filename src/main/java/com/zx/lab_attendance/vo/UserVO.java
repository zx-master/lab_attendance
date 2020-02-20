package com.zx.lab_attendance.vo;

import com.zx.lab_attendance.entity.Role;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/11 15:41
 * @Description
 */
public class UserVO {



    private String userNumber;

    private String email;

    /**
     * 年级和班级
     */
    private String collective;

    private String majorName;

    private String phone;

    private String userName;

    private String role;

    public String getCollective() {
        return collective;
    }

    public void setCollective(String collective) {
        this.collective = collective;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }


    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
