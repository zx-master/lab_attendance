package com.zx.lab_attendance;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

import com.github.tobato.fastdfs.FdfsClientConfig;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/14 11:04
 * @Description
 */
@Configuration
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration=RegistrationPolicy.IGNORE_EXISTING)
public class FastdfsImporter {

}
