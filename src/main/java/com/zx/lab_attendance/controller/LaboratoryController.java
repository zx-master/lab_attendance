package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.entity.Applylab;
import com.zx.lab_attendance.entity.JsonData;
import com.zx.lab_attendance.service.LaboratoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/5 13:29
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/laboratory")
@Api(value = "申请信息控制器")
public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @GetMapping("/selectAllLab")
    @ApiOperation(value = "新增申请数据", notes="新增成功")
    public JsonData selectAllLab(HttpServletResponse response, HttpServletRequest request){
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(laboratoryService.selectAllLab());
    }



}
