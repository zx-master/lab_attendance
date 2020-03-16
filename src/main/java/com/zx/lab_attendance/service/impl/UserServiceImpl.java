package com.zx.lab_attendance.service.impl;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zx.lab_attendance.dao.*;
import com.zx.lab_attendance.entity.*;
import com.zx.lab_attendance.service.LeaveclassmService;
import com.zx.lab_attendance.service.UserService;
import com.zx.lab_attendance.vo.UserAttendance;
import com.zx.lab_attendance.vo.UserVO;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    LeaveclassmMapper leaveclassmMapper;
    @Autowired
    CourseandstuMapper courseandstuMapper;
    @Autowired
    LabusingMapper labusingMapper;
    @Autowired
    MajorMapper majorMapper;



    @Override
    public List<UserVO> allStudentUser() {
        List<Users> usersList = usersMapper.selectByAllStudent();
        List<UserVO> userVOS = new ArrayList<>();
        for (Users users : usersList){
            UserVO userVO = new UserVO();
            userVO.setUserId(users.getUserId());
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
    public List<UserVO> allStudentTeacher() {
        List<UserVO> userVOS = new ArrayList<>();
        List<Users> usersList = usersMapper.selectByAllTeacher();
        for (Users users : usersList) {
            UserVO userVO = new UserVO();
            userVO.setUserId(users.getUserId());
            userVO.setUsername(users.getUsername());
            userVO.setUserNumber(users.getUserNumber());
            userVO.setMajorName(users.getMajor().getMajorName());
            userVO.setPhone(users.getPhone());
            userVO.setEmail(users.getEmail());
            userVO.setCollective(departmentMapper.selectByPrimaryKey(users.getClassordepartment()).getDepartmentDescribe());
            userVOS.add(userVO);
        }
        return userVOS;
    }

    public void delectUser(String userId){
      usersMapper.deleteByPrimaryKey(userId);
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

    @Override
    public List<UserAttendance> selectUserByAttendcane(String labusingid) {
        //获取请假的那些人
        List<Leaveclassm> leaveclassms = leaveclassmMapper.selectAllBylabusing(labusingid);
        Map<String,UserAttendance> map = new HashedMap();
        Labusing labusing = labusingMapper.selectByPrimaryKey(labusingid);
        List<Courseandstu> courseandstus = courseandstuMapper.selectByCourseCode(labusing.getCourseId());
        List<UserAttendance> userAttendances = new ArrayList<>();
        for (Courseandstu courseandstu :courseandstus) {
            UserAttendance userAttendance = new UserAttendance();
            userAttendance.setUserName(courseandstu.getStudentUser().getUsername());
            userAttendance.setUserNumber(courseandstu.getStudentUser().getUserNumber());
            userAttendance.setAttendanceRecord(1);
            userAttendance.setStudentId(courseandstu.getStudentId());
            userAttendance.setTeacherId(labusing.getCourse().getCourseTeacher());
            userAttendance.setLabusingId(labusing.getLabusingId());
            map.put(courseandstu.getStudentUser().getUserNumber(),userAttendance);
        }
        for (Leaveclassm leaveclassm : leaveclassms){
            if(map.containsKey(leaveclassm.getStudentUser().getUserNumber())){
                map.remove(leaveclassm.getStudentUser().getUserNumber());
                UserAttendance userAttendance = new UserAttendance();
                userAttendance.setUserName(leaveclassm.getStudentUser().getUsername());
                userAttendance.setUserNumber(leaveclassm.getStudentUser().getUserNumber());
                userAttendance.setLabusingId(labusing.getLabusingId());
                userAttendance.setStudentId(leaveclassm.getStudentId());
                userAttendance.setTeacherId(labusing.getCourse().getUser().getUserId());
                if(leaveclassm.getLeaveStatus() == 2){                    //已批准
                    userAttendance.setAttendanceRecord(leaveclassm.getLeaveClass());
                }else if(leaveclassm.getLeaveStatus() == 1){               //未处理
                    userAttendance.setAttendanceRecord(6);
                }else {                                                  //未批准
                    userAttendance.setAttendanceRecord(1);
                }
                map.put(leaveclassm.getStudentUser().getUserNumber(),userAttendance);
            }
        }
        for (UserAttendance value : map.values()) {
            userAttendances.add(value);
        }

        return userAttendances;
    }

    @Override
    public String updateUser(UserVO user) {
        Users newuser = new Users();
        newuser.setUserId(user.getUserId());
        newuser.setEmail(user.getEmail());
        newuser.setUserNumber(user.getUserNumber());
        newuser.setUsername(user.getUsername());
        String department = user.getCollective().substring(0,3);              //年级
        String collective = user.getCollective().substring(3,4);              //班级
        Department department1 = departmentMapper.selectByDescribe(department);
        Major major = majorMapper.selectByMajorName(user.getMajorName());
        newuser.setMajorId(major.getMajorId());
        if (department1==null){
            return "请检查系别和班别是否正确";
        }else{
            Collective collective1 = collectiveMapper.selectNumber(Integer.parseInt(collective),department1.getDepartmentId());
            newuser.setClassordepartment(collective1.getCollectiveId());
            usersMapper.updateUser(newuser);
            return "更新成功";
        }
    }

    @Override
    public String updateTeacher(UserVO user) {
        Users newuser = new Users();
        newuser.setUserId(user.getUserId());
        newuser.setEmail(user.getEmail());
        newuser.setUserNumber(user.getUserNumber());
        newuser.setUsername(user.getUsername());
        String department = user.getCollective();              //年级

        Department department1 = departmentMapper.selectByDescribe(department);
        Major major = majorMapper.selectByMajorName(user.getMajorName());
        newuser.setMajorId(major.getMajorId());
        if (department1==null){
            return "请检查系别和班别是否正确";
        }else{
//            Collective collective1 = collectiveMapper.selectNumber(Integer.parseInt(collective),department1.getDepartmentId());
            newuser.setClassordepartment(department1.getDepartmentId());
            usersMapper.updateUser(newuser);
            return "更新成功";
        }
    }

}
