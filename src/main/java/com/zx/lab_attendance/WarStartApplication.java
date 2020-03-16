package com.zx.lab_attendance;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/15 17:05
 * @Description 使用外置tomcat启动application
 */
public class WarStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LabAttendanceApplication.class);
    }
}
