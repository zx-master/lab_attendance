package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Users;
import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper {
    int deleteByPrimaryKey(String userId);

    int insert(Users record);

    Users selectByPrimaryKey(String userId);

    List<Users> selectAll();

    int updateByPrimaryKey(Users record);

    Users selectUserByUserNum(String usernumber);

    List<Users> selectByCourseId(String courseId);

    List<Users> selectByAllStudent();
}