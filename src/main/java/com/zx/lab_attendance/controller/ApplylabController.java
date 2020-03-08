package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.entity.Applylab;
import com.zx.lab_attendance.entity.JsonData;
import com.zx.lab_attendance.service.ApplylabService;
import com.zx.lab_attendance.vo.ChatmsgInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/5 0:43
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/applylab")
@Api(value = "申请信息控制器")
public class ApplylabController {

    @Autowired
    private ApplylabService applylabService;


    @PostMapping("/insertApplylab")
    @ApiOperation(value = "新增申请数据", notes="新增成功")
    public JsonData insertApplylab(HttpServletResponse response, HttpServletRequest request, @RequestBody Applylab applylab){
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(applylabService.insertApplyLab(applylab));
    }

    @PostMapping("/updateApplylab")
    @ApiOperation(value = "更新申请数据", notes="更新成功")
    public JsonData updateApplylab(HttpServletResponse response, HttpServletRequest request, @RequestBody ChatmsgInfo ChatmsgInfo){
        response.setHeader("Access-Control-Allow-Origin","*");
        applylabService.updateApplyLab(ChatmsgInfo);
        return JsonData.buildSuccess("更新成功");
    }

    @PostMapping("/selectApplyLabByUserId")
    @ApiOperation(value = "分页查询", notes="返回JsonData数据")
    public JsonData selectApplyLabByUserId(@RequestParam("studentId") String studentId,@RequestParam("pageNum") Integer pageNum){
        return JsonData.buildSuccess(applylabService.selectApplyLabByUserId(studentId,pageNum,7));
    }

}
