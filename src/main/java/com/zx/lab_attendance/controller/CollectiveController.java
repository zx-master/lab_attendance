package com.zx.lab_attendance.controller;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/10 11:49
 * @Description
 */

import com.zx.lab_attendance.entity.JsonData;
import com.zx.lab_attendance.service.CollectiveService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/collective")
@Api(value = "班级信息")
public class CollectiveController {

    @Autowired
    private CollectiveService collectiveService;

    @GetMapping("/getAllCollective")
    public JsonData getAllCollective(){
         return JsonData.buildSuccess(collectiveService.selectAllCollective());
    }
}
