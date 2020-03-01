package com.zx.lab_attendance.entity;

import java.io.Serializable;
import java.util.List;

public class Users implements Serializable {
    private String userId;

    private String userNumber;

    private String email;

    private String password;

    private String classordepartment;

    private String phone;

    private Integer tssign;

    private String username;

    private String userImg;

    private List<Role> roleList;

    private Integer majorId;

    private Major major;

    private static final long serialVersionUID = 1L;

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber == null ? null : userNumber.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getClassordepartment() {
        return classordepartment;
    }

    public void setClassordepartment(String classordepartment) {
        this.classordepartment = classordepartment == null ? null : classordepartment.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getTssign() {
        return tssign;
    }

    public void setTssign(Integer tssign) {
        this.tssign = tssign;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", classordepartment='" + classordepartment + '\'' +
                ", phone='" + phone + '\'' +
                ", tssign=" + tssign +
                ", username='" + username + '\'' +
                ", userImg='" + userImg + '\'' +
                ", roleList=" + roleList +
                ", majorId=" + majorId +
                ", major=" + major +
                '}';
    }
}