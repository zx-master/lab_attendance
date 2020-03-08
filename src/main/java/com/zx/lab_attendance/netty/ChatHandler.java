package com.zx.lab_attendance.netty;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.zx.lab_attendance.entity.Chatmsg;
import com.zx.lab_attendance.enums.MsgActionEnum;
import com.zx.lab_attendance.service.ChatmsgService;
import com.zx.lab_attendance.utils.IdWorker;
import com.zx.lab_attendance.utils.JsonUtils;
import com.zx.lab_attendance.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/2 0:27
 * @Description
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /**
     * 用于记录和管理所有客户端的channel
     */
    private static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取客户端传输过来的数据
        String content = msg.text();
        System.out.println("消息：" + content);
        //获取channel
        Channel currentChannel = ctx.channel();

        //1.获取客户端发来的消息
        DataContent dataContent = JSONArray.parseObject(content,DataContent.class);
        Integer action = dataContent.getAction();
        //2.判断消息类型，根据不同的类型来处理不同业务
        if (action.equals(MsgActionEnum.CONNECT.type)) {
            String senderId = dataContent.getChatmsg().getSenderId();
            UserChannelRel.put(senderId,currentChannel);
        } else if (action.equals(MsgActionEnum.CHAT.type)) {
            IdWorker idWorker = new IdWorker(0, 0);
            String chatmsgId = "CM" + idWorker.nextId();
            Chatmsg chatmsg = dataContent.getChatmsg();
            //插入请假数据
            chatmsg.setChatmsgId(chatmsgId);
            // 保存消息到数据库，并标记为 未签收
            ChatmsgService chatmsgService = (ChatmsgService) SpringUtil.getBean("chatmsgServiceImpl");
            chatmsgService.insert(chatmsg);
//            chatmsg.setChatmsgId(null);
            //发送消息
            //从全局用户Channel关系中获取接受方的channel
            Channel receiverChannel = UserChannelRel.get(chatmsg.getReceiverId());
            if (receiverChannel == null) {
                //channel为空代表用户离线，推送消息
            } else {
                //当receiverChannel不为空的时候，从ChannelGroup去查找对应的channel是否存在
                Channel findChannel = users.find(receiverChannel.id());
                if (findChannel != null) {
                    //用户在线
                    receiverChannel.writeAndFlush(
                            new TextWebSocketFrame(
                                    String.valueOf(JSONObject.toJSON(chatmsg.getMsg()))
                            )
                    );
                } else {
                    //用户离线
                }
            }

        } else if (action.equals(MsgActionEnum.SIGNED.type)) {
            // 2.3签收消息类型，针对具体的消息进行签收，修改数据库中对应消息的签收状态[已签收]
            ChatmsgService chatmsgService = (ChatmsgService)SpringUtil.getBean("chatmsgServiceImpl");
            //拓展字段在signed类型的消息中，代表需要去签收的消息id，逗号间隔
            String msgIdsStr = dataContent.getExtand();
            String msgIds[] = msgIdsStr.split(",");

            List<String> msgIdList = new ArrayList<>();
            for (String mid : msgIds) {
                if (StringUtils.isNotBlank(mid)) {
                    msgIdList.add(mid);
                }
            }

            System.out.println(msgIdList.toString());

            if (msgIdList != null && !msgIdList.isEmpty() && msgIdList.size() >0 ){
                //批量签收
            }
        } else if (action.equals(MsgActionEnum.KEEPALIVE.type)){
            //心跳类型消息
        }


    }

    /**
     * 当客户端连接服务端之后（打开连接）
     * 获取客户端的channel，并且放到ChnnelGroup中进行管理
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        users.remove(ctx.channel());
        System.out.println("客户端断开，对应的长id：" + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生异常之后关闭连接（关闭channel），随后从ChannelGroup中移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}
