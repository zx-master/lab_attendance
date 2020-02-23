package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.entity.JsonData;
import com.zx.lab_attendance.entity.Leave;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/22 22:13
 * @Description
 */
@RestController
@RequestMapping("/leave")
@Api(value = "请假信息控制器")
public class LeaveController {

    @PostMapping("/insertLeave")
    public JsonData insertLeave(Leave leave){
        return null;
    }

}
