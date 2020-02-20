package com.zx.lab_attendance.service.impl;

import com.zx.lab_attendance.dao.CollectiveMapper;
import com.zx.lab_attendance.dao.UsersMapper;
import com.zx.lab_attendance.entity.Collective;
import com.zx.lab_attendance.entity.Users;
import com.zx.lab_attendance.service.UserService;
import com.zx.lab_attendance.vo.UserVO;
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

    @Override
    public List<UserVO> allStudentUser() {
        List<Users> usersList = usersMapper.selectByAllStudent();
        List<UserVO> userVOS = new ArrayList<>();
        for (Users users : usersList){
            Collective collective = collectiveMapper.selectByPrimaryKey(users.getClassordepartment());
            UserVO userVO = new UserVO();
            userVO.setUsername(users.getUsername());
            userVO.setUserNumber(users.getUserNumber());
            userVO.setEmail(users.getEmail());
            userVO.setMajorName(users.getMajor().getMajorName());
            userVO.setCollective(collective.getDepartment().getDepartmentDescribe()+collective.getCollectiveNumber()+"班");
            userVOS.add(userVO);
        }
        return userVOS;
    }

    @Override
    public Users selectUserByUserNum(String usernumber) {
        return usersMapper.selectUserByUserNum(usernumber);
    }
}
