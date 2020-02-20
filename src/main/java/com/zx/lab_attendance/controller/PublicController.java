package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.entity.JsonData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/1 12:02
 * @Description
 */
@Api
@RestController
@RequestMapping("pub")
public class PublicController {

    @ApiOperation(value = "未登录", notes="没登录提示的数据")
    @GetMapping("need_login")
    public JsonData needLogin(){
        return JsonData.buildError("需要登录");
    }

    @GetMapping("not_permit")
    public JsonData notPermission(){
        return JsonData.buildError("无权限");
    }

}
