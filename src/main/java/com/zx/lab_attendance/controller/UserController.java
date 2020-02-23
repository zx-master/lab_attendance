package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.entity.JsonData;
import com.zx.lab_attendance.entity.Users;
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

    @GetMapping("/getStudentUser")
    @ApiOperation(value = "获取所有学生的数据", notes="返回List<UserVO>数据")
    public JsonData getStudentUser(HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(userService.allStudentUser());
    }

    @PostMapping("/login")
    public JsonData login(@RequestBody Users user, HttpServletRequest request, HttpServletResponse response){
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> info = new HashMap<>();
        try{
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserNumber(),user.getPassword());
            Users userInfo = userService.selectUserByUserNum(user.getUserNumber());
            UserVO userVO = new UserVO();
            userVO.setUserId(userInfo.getUserId());
            userVO.setUserNumber(userInfo.getUserNumber());
            userVO.setUsername(userInfo.getUsername());
            userVO.setEmail(userInfo.getEmail());
            userVO.setPhone(userInfo.getPhone());

            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>需要将专业，班级，年级等信息封装<<<<<<<<<<<<<<<<<<<<<<<<<<<<

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
