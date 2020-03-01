package com.zx.lab_attendance.service.impl;

import com.zx.lab_attendance.dao.*;
import com.zx.lab_attendance.entity.*;
import com.zx.lab_attendance.service.UserService;
import com.zx.lab_attendance.vo.UserVO;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/1/29 15:57
 * @Description
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    CollectiveMapper collectiveMapper;
    @Autowired
    DepartmentMapper departmentMapper;


    @Override
    public List<UserVO> allStudentUser() {
        List<Users> usersList = usersMapper.selectByAllStudent();
        List<UserVO> userVOS = new ArrayList<>();
        for (Users users : usersList){
            UserVO userVO = new UserVO();
            userVO.setUsername(users.getUsername());
            userVO.setUserNumber(users.getUserNumber());
            userVO.setEmail(users.getEmail());
            userVO.setMajorName(users.getMajor().getMajorName());
            //<<<<<<<<<<<<<<<<<<<<<<<<<<<<判断是老师还是学生>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            if (users.getTssign() == 2) {
                Collective collective = collectiveMapper.selectByPrimaryKey(users.getClassordepartment());
                userVO.setCollective(collective.getDepartment().getDepartmentDescribe() + collective.getCollectiveNumber() + "班");
            }else{
                Department department = departmentMapper.selectByPrimaryKey(users.getClassordepartment());
                userVO.setCollective(department.getDepartmentDescribe());
            }
            userVOS.add(userVO);
        }
        return userVOS;
    }

    @Override
    public Users selectUserByUserNum(String usernumber) {
        return usersMapper.selectUserByUserNum(usernumber);
    }

    @Override
    public void updateEmailPhoneByPrimaryKey(Users user) {
        usersMapper.updateEmailPhoneByPrimaryKey(user);
    }

    @Override
    public void updateUserImgByPrimaryKey(Users user){
        usersMapper.updateUserImgByPrimaryKey(user);
    }

    @Override
    public int checkPsw(Users user) {
        Users userInfo = usersMapper.selectByPrimaryKey(user.getUserId());
        Object result = new SimpleHash("md5",user.getPassword(),null,2);
        if (userInfo.getPassword().equals(result.toString())){
            System.out.println(userInfo.getPassword());
            System.out.println(result.toString());
            return 0;
        }else{
            return -1;
        }
    }

    @Override
    public int updateUserPwd(Users user) {
        Object result = new SimpleHash("md5",user.getPassword(),null,2);
        user.setPassword(result.toString());
        if (usersMapper.updateUserPwd(user) == 1){
            return 0;
        }else{
            return -1;
        }
    }


}
