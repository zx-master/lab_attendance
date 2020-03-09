package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.dao.CollectiveMapper;
import com.zx.lab_attendance.dao.DepartmentMapper;
import com.zx.lab_attendance.entity.*;
import com.zx.lab_attendance.service.MajorService;
import com.zx.lab_attendance.service.UserService;
import com.zx.lab_attendance.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zx
 * @version 1.0
 * @date 2020/1/29 15:50
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
@Api(value = "用户信息控制器")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    CollectiveMapper collectiveMapper;
    @Autowired
    DepartmentMapper departmentMapper;

    @PostMapping("/updateUser")
    public JsonData updateUser(@RequestBody UserVO user){
        return JsonData.buildSuccess(userService.updateUser(user));
    }

    @PostMapping("/updateTeacherUser")
    public JsonData updateTeacherUser(@RequestBody UserVO user){
        return JsonData.buildSuccess(userService.updateTeacher(user));
    }

    @GetMapping("/getTeaherUser")
    @ApiOperation(value = "获取所有老师的数据", notes="返回List<UserVO>数据")
    public JsonData getTeaherUser(HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(userService.allStudentTeacher());
    }

    @PostMapping("/delectUser")
    public JsonData delectUser(@RequestParam("userId") String userId) {
        userService.delectUser(userId);
        return JsonData.buildSuccess("删除成功!");
    }

    @GetMapping("/getStudentUser")
    @ApiOperation(value = "获取所有学生的数据", notes="返回List<UserVO>数据")
    public JsonData getStudentUser(HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(userService.allStudentUser());
    }

    @PostMapping("/changeUserInfo")
    public JsonData changeUserInfo(HttpServletResponse response, HttpServletRequest request,Users user){
        response.setHeader("Access-Control-Allow-Origin","*");
        userService.updateEmailPhoneByPrimaryKey(user);
        return JsonData.buildSuccess("更新成功");
    }

    @PostMapping("/selectUserByAttendance")
    public JsonData selectUserByAttendance(HttpServletResponse response, HttpServletRequest request,@RequestParam("labusingId") String labusingId){
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(userService.selectUserByAttendcane(labusingId));
    }


    @PostMapping("/updateUserImgByPrimaryKey")
    public JsonData updateUserImgByPrimaryKey(HttpServletResponse response, HttpServletRequest request,Users user){
        response.setHeader("Access-Control-Allow-Origin","*");
        userService.updateUserImgByPrimaryKey(user);
        return JsonData.buildSuccess("更新成功");
    }

    @PostMapping("/checkPsw")
    public JsonData checkPsw(Users user){
        return JsonData.buildSuccess(userService.checkPsw(user));
    }

    @PostMapping("/changePsw")
    public JsonData changePsw(Users user){
        return JsonData.buildSuccess(userService.updateUserPwd(user));
    }

    @PostMapping("/login")
    public JsonData login(@RequestBody Users user, HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> info = new HashMap<>();
        try{
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserNumber(),user.getPassword());
            Users userInfo = userService.selectUserByUserNum(user.getUserNumber());
            System.out.println(userInfo);
            UserVO userVO = new UserVO();
            userVO.setUserId(userInfo.getUserId());
            userVO.setUserNumber(userInfo.getUserNumber());
            userVO.setUsername(userInfo.getUsername());
            userVO.setEmail(userInfo.getEmail());
            userVO.setPhone(userInfo.getPhone());
            userVO.setMajorName(userInfo.getMajor().getMajorName());
//            userVO.setRole(userInfo);
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>需要将专业，班级，年级等信息封装<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            if (userInfo.getTssign() == 2) {
                Collective collective = collectiveMapper.selectByPrimaryKey(userInfo.getClassordepartment());
                userVO.setCollective(collective.getDepartment().getDepartmentDescribe() + collective.getCollectiveNumber() + "班");
            }else if(userInfo.getTssign() == 3){
//                Department department = departmentMapper.selectByPrimaryKey(userInfo.getClassordepartment());
                userVO.setCollective("root");
            }else{
                Department department = departmentMapper.selectByPrimaryKey(userInfo.getClassordepartment());
                userVO.setCollective(department.getDepartmentDescribe());
            }
            userVO.setUserImg(userInfo.getUserImg());
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>用户角色>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            Integer roleString = userInfo.getTssign();
//            for (Role role : userInfo.getRoleList()){
//                roleString = roleString + role.getRoleName();
//            }

            userVO.setRole(roleString);
            subject.login(usernamePasswordToken);
            info.put("msg","登录成功");
            info.put("session_id",subject.getSession().getId());
            info.put("user",userVO);
            return JsonData.buildSuccess(info);
        }catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError("账号密码错误");
        }
    }


}
