package com.zx.lab_attendance;

import com.github.pagehelper.PageHelper;
import com.zx.lab_attendance.utils.SpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Properties;

@SpringBootApplication
@EntityScan
@MapperScan(basePackages ="com.zx.lab_attendance.dao")
@EnableScheduling
public class LabAttendanceApplication {

    @Bean
    public SpringUtil getSpringUtil() {
        return new SpringUtil();
    }

    //配置mybatis的分页插件pageHelper
     @Bean
      public PageHelper pageHelper(){
                PageHelper pageHelper = new PageHelper();
                Properties properties = new Properties();
               properties.setProperty("offsetAsPageNum","true");
               properties.setProperty("rowBoundsWithCount","true");
                properties.setProperty("reasonable","false");
                properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
                pageHelper.setProperties(properties);
                return pageHelper;
           }


    public static void main(String[] args) {
        SpringApplication.run(LabAttendanceApplication.class, args);
    }

}
