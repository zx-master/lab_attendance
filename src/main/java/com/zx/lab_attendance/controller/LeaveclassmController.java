package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.dao.LeaveclassmMapper;
import com.zx.lab_attendance.entity.JsonData;
import com.zx.lab_attendance.entity.Leaveclassm;
import com.zx.lab_attendance.service.LeaveclassmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/27 0:30
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/leaveclassm")
@Api(value = "考勤信息控制器")
public class LeaveclassmController {

    @Autowired
    private LeaveclassmService leaveclassmService;

    @PostMapping("/selectByApproverAndStu")
    @ApiOperation(value = "分页查询", notes="返回JsonData数据")
    public JsonData selectByApproverAndStu(@RequestParam("studentId") String studentId,@RequestParam("pageNum")Integer pageNum){
        return JsonData.buildSuccess(leaveclassmService.selectByApproverAndStu(studentId,pageNum,7));
    }

}
