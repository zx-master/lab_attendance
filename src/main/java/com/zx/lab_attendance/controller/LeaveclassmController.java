package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.dao.LeaveclassmMapper;
import com.zx.lab_attendance.entity.JsonData;
import com.zx.lab_attendance.entity.Leaveclassm;
import com.zx.lab_attendance.service.LeaveclassmService;
import com.zx.lab_attendance.vo.ChatmsgInfo;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public JsonData selectByApproverAndStu(@RequestParam("studentId") String studentId,@RequestParam("pageNum") Integer pageNum){
        return JsonData.buildSuccess(leaveclassmService.selectByApproverAndStu(studentId,pageNum,7));
    }

    @PostMapping("/insertLeaveclassm")
    public JsonData insertLeaveclassm(HttpServletResponse response, HttpServletRequest request, @RequestBody Leaveclassm leaveclassm){
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(leaveclassmService.insertReturnTea(leaveclassm));
    }

    @PostMapping("/updateLeaveclassm")
    public JsonData updateLeaveclassm(HttpServletResponse response, HttpServletRequest request,@RequestBody ChatmsgInfo chatmsgInfo){
        response.setHeader("Access-Control-Allow-Origin","*");
        leaveclassmService.updateLeaveclassm(chatmsgInfo);
        return JsonData.buildSuccess("成功");
    }

}
