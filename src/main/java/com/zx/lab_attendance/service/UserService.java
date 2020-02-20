package com.zx.lab_attendance.service;

import com.zx.lab_attendance.entity.Users;
import com.zx.lab_attendance.vo.UserVO;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/1/29 15:57
 * @Description
 */

public interface UserService {

    public List<UserVO> allStudentUser();

    public Users selectUserByUserNum(String usernumber);

}
