package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.entity.Attendance;
import com.zx.lab_attendance.entity.JsonData;
import com.zx.lab_attendance.service.AttendanceService;
import com.zx.lab_attendance.vo.CourseAttendance;
import com.zx.lab_attendance.vo.PersonalAttendance;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/11 14:16
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/attendance")
@Api(value = "考勤信息控制器")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/insertAttendance")
    public JsonData insertAttendance(Attendance attendance){
        return JsonData.buildSuccess(attendanceService.insertAttendance(attendance));
    }
    @PostMapping("/getAttendanceFromClass")
    @ApiOperation(value = "根据班级id和时间段获取考勤数据", notes="返回List<Map<String,Map<String,List<PersonalAttendance>>>>数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "collectiveid", value = "班级id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "starttime", value = "时间段开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endtime", value = "时间段结束时间", required = true, dataType = "String")
    })
    public JsonData getAttendanceFromClass(HttpServletResponse response, HttpServletRequest request,
                                           @RequestParam("collectiveid") String collectiveid,
                                           @RequestParam("starttime") String starttime,
                                           @RequestParam("endtime") String endtime) {
        response.setHeader("Access-Control-Allow-Origin","*");
//        String collectiveid = "411403073978630144";
//        String starttime = "2020-02-09 00:00:00";
//        String endtime = "2020-02-12 23:59:59";
        List<Map<String,Map<String,List<PersonalAttendance>>>> personalAttendances = attendanceService.selectByTimeAndCollective(collectiveid,starttime,endtime);
        return JsonData.buildSuccess(personalAttendances);
    }

    @PostMapping("/getAttendanceFromUser")
    @ApiOperation(value = "根据学生学号和时间段获取考勤数据", notes="List<Map<String,List<PersonalAttendance>>>")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "studentNum", value = "学生学号", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "starttime", value = "时间段开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endtime", value = "时间段结束时间", required = true, dataType = "String")
    })
    public JsonData getAttendanceFromUser(HttpServletResponse response, HttpServletRequest request,
                                          @RequestParam("studentNum") String studentNum,
                                          @RequestParam("starttime") String starttime,
                                          @RequestParam("endtime") String endtime) {
        response.setHeader("Access-Control-Allow-Origin","*");
//        String studentNum = "1640129422";
//        String starttime = "2020-02-09 00:00:00";
//        String endtime = "2020-02-14 23:59:59";
        List<PersonalAttendance> personalAttendances = attendanceService.selectByUserNum(studentNum,starttime,endtime);
        return JsonData.buildSuccess(personalAttendances);
    }

    @PostMapping("/getAttendanceFromCourse")
    @ApiOperation(value = "根据课程代码和时间段获取考勤数据", notes="List<CourseAttendance>")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "courseCode", value = "课程代码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "starttime", value = "时间段开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endtime", value = "时间段结束时间", required = true, dataType = "String")
    })
    public JsonData getAttendanceFromCourse(HttpServletResponse response, HttpServletRequest request,
                                            @RequestParam("courseCode") String courseCode,
                                            @RequestParam("starttime") String starttime,
                                            @RequestParam("endtime") String endtime) throws ParseException {
//        response.setHeader("Access-Control-Allow-Origin","*");
//        String courseCode = "C01004";
//        String starttime = "2020-02-09";
//        String endtime = "2020-02-14";
        List<CourseAttendance> attendances = attendanceService.selectByCourseCode(courseCode,starttime,endtime);
        return JsonData.buildSuccess(attendances);
    }

    @PostMapping("/selectOutputPercentage")
    @ApiOperation(value = "根据一周的对比两周的时间，作比较出勤率", notes="List<Map<String,Double>>")
    public JsonData selectOutputPercentage() throws ParseException {
        List<Double> personal =  attendanceService.selectOutputPercentage();
        return JsonData.buildSuccess(personal);
    }

    @PostMapping(value = "/selectAbsenteeism")
    @ApiOperation(value = "根据时间段，查询缺勤人数", notes="List<Map<String, Integer>>")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "starttime", value = "时间段开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "endtime", value = "时间段结束时间", required = true, dataType = "String")
    })
    public JsonData selectAbsenteeism(HttpServletResponse response, HttpServletRequest request,
                                      @RequestParam("starttime") String starttime,
                                      @RequestParam("endtime") String endtime){
//        response.setHeader("Access-Control-Allow-Origin","*");
//        String starttime = "2020-02-09 00:00:00";
//        String endtime = "2020-02-14 23:59:59";
        List<Map> mapList = attendanceService.selectAbsenteeism(starttime,endtime);
        return JsonData.buildSuccess(mapList);
    }

}
