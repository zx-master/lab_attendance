package com.zx.lab_attendance.controller;

import com.zx.lab_attendance.entity.JsonData;
import com.zx.lab_attendance.service.ChatmsgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/6 1:11
 * @Description
 */
@CrossOrigin
@RestController
@RequestMapping("/Chatmsg")
@Api(value = "聊天信息控制器")
public class ChatmsgController {

    @Autowired
    private ChatmsgService chatmsgService;

    @PostMapping("/selectNoReceive")
    @ApiOperation(value = "查询未接收聊天信息", notes="List<chatmsg>")
    public JsonData selectNoReceive(HttpServletResponse response, HttpServletRequest request,@RequestParam("receiveId") String receiveId){
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(chatmsgService.selectNoReceive(receiveId));
    }

    @PostMapping("/selectChatmsgInfo")
    @ApiOperation(value = "查询消息", notes="List<chatmsgInfo>")
    public JsonData selectChatmsgInfo(HttpServletResponse response, HttpServletRequest request,@RequestParam("receiveId") String receiveId){
        response.setHeader("Access-Control-Allow-Origin","*");
        return JsonData.buildSuccess(chatmsgService.selectChatmsgInfo(receiveId));
    }

    @PostMapping("/updateChatmsgSign")
    public JsonData updateChatmsgInfo(HttpServletResponse response, HttpServletRequest request,@RequestParam("chatmsgId") String chatmsgId) {
        response.setHeader("Access-Control-Allow-Origin","*");
        chatmsgService.updateChatmsg(chatmsgId);
        return JsonData.buildSuccess("更新成功");
    }

}
