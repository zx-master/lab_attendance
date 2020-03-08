package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.entity.JsonData;
import com.zx.lab_attendance.entity.Leave;
import com.zx.lab_attendance.service.LeaveService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/22 22:13
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/leave")
@Api(value = "请假信息控制器")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/insertLeave")
    public JsonData insertLeave(HttpServletResponse response, HttpServletRequest request,
                                @RequestBody Leave leave){
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(leaveService.insertLeave(leave));
    }

}
