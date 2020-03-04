package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.entity.JsonData;
import com.zx.lab_attendance.service.LabusingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/24 1:55
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/labusing")
public class LabusingController {

    @Autowired
    private LabusingService labusingService;

    @PostMapping("/selectLabusingForLeave")
    public JsonData selectLabusingForLeave(HttpServletResponse response, HttpServletRequest request,
                                           @RequestParam(value = "studentId",required=false) String studentId,
                                           @RequestParam(value = "starttime",required=false) String starttime,
                                           @RequestParam(value = "endtime",required=false) String endtime){
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(labusingService.selectLabusingByMyCourseForTime(studentId,starttime,endtime));
    }
    @PostMapping("/selectLabByTeacher")
    public JsonData selectLabByTeacher(HttpServletResponse response, HttpServletRequest request,String teacherId) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(labusingService.selectLabByTeacher(teacherId));
    }
    @PostMapping("/selectLabByStudent")
    public JsonData selectLabByStudent(HttpServletResponse response, HttpServletRequest request,String studentId) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(labusingService.selectLabBystudent(studentId));
    }

    @PostMapping("/selectLabByLabNum")
    public JsonData selectLabByLabNum(HttpServletResponse response, HttpServletRequest request,String labnum) {
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(labusingService.selectLabByLabNum(labnum));
    }



}
