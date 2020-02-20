package com.zx.lab_attendance;

import com.zx.lab_attendance.netty.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/2 0:14
 * @Description
 */
@Component
public class NettyBoot implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null){
            try{
                WSServer.getInstance().start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
