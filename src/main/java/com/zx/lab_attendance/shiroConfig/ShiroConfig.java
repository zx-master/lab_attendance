package com.zx.lab_attendance.shiroConfig;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zx
 * @version 1.0
 * @date 2020/1/31 17:36
 * @Description
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("执行 shiroFilterFactoryBean.shiroFilter");

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * shrio内置的过滤器，可实现权限相关的拦截器
         *      常用的过滤器
         *          anon:无需认证（登录）就可以访问
         *          authc:必须认证才可以访问
         *          user:使用RememberMe功能才可直接访问
         *          perms:该资源必须得到资源权限才可以访问
         *          role:该资源必须得到资源权限才可以访问
         *                  filterChainDefinitionMap
         */
        //需要登录，没有登录的则跳转登录界面。如果不是前后端分离则跳转页面/xxx.jsp
        shiroFilterFactoryBean.setLoginUrl("/pub/need_login");

        //登录成功跳转页面，前后端分离没有这个
        //shiroFilterFactoryBean.setSuccessUrl("/");

        //登录没权限，跳转这个接口， 先验证登录-》再验证是否有权限
        shiroFilterFactoryBean.setUnauthorizedUrl("/pub/not_permit");

        //拦截器路径，坑一，部分路径无法拦截，时有时无；因为同学使用的hashmap，无序的，应该改为linkHashmap
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();

        //退出过滤器
        filterChainDefinitionMap.put("/logout","logout");

        //匿名可以访问，也就是游客模式
        filterChainDefinitionMap.put("/pub/**","anon");

        //登录接口的访问游客模式
        filterChainDefinitionMap.put("/user/login","anon");

        //登录用户才可以访问
        filterChainDefinitionMap.put("/authc/**","authc");

        //教师角色才能访问--考勤
//        filterChainDefinitionMap.put("/attendance/insertAttendanceList","roles[teacher]");

        //教师角色才能访问--申请教室
//        filterChainDefinitionMap.put("/applylab/insertApplylab","roles[teacher]");

        //教师角色才能访问--审核请假
//        filterChainDefinitionMap.put("/leaveclassm/updateLeaveclassm","roles[teacher]");

        //管理员角色才访问--修改学生信息
//        filterChainDefinitionMap.put("/user/updateUser","roles[root]");

        //管理员角色才访问--修改教室信息
//        filterChainDefinitionMap.put("/user/updateTeacherUser","roles[root]");

        //管理员角色才访问--审核申请实验室
//        filterChainDefinitionMap.put("/applylab/updateApplylab","roles[root]");

        //有编辑权限才可以访问
//        filterChainDefinitionMap.put("/authc/getAllUser","perms[read_attendance]");

        //坑二：过滤链是顺序执行，从上而下，一般讲/** 放到最下面
        //authc : url定义必须通过认证才可以访问
        //anon ： url可以匿名访问
        filterChainDefinitionMap.put("/**","anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;

    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //如果不是前后端分离，则不用设置
        securityManager.setSessionManager(sessionManager());

        //使用自定义的cacheManager
        securityManager.setCacheManager(cacheManager());

        //设置realm（推荐放到最后，某些情况可能不生效）
        securityManager.setRealm(customRealm());

        return securityManager;
}

    /**
     * 自定义realm
     * @return
     */
    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
        //密码加解密，数据库的密码需要为密文
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        return customRealm;
    }

    /**
     * 密码加解密
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        //设置散列算法：使用MD5算法
        credentialsMatcher.setHashAlgorithmName("md5");

        //散列次数，好比散列2次，相当于MD5（MD5（XXX））
        credentialsMatcher.setHashIterations(2);

        return credentialsMatcher;
    }

    /**
     * 自定义sessionManager
     * @return
     */
    @Bean
    public SessionManager sessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();

        //会话超时时间设定，默认30分钟，单位为毫秒
//        customSessionManager.setGlobalSessionTimeout(200000);
        //配置session持久化
        customSessionManager.setSessionDAO(redisSessionDAO());

        return customSessionManager;
    }

    /**
     * 配置redismanager
     */
    public RedisManager getRedisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1");
        redisManager.setPort(6379);
        return redisManager;
    }

    /**
     * 匹配具体cache实现类
     * @return
     */
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        //设置redis唯一标识key
        redisCacheManager.setPrincipalIdFieldName("userId");
        return redisCacheManager;
    }

    /**
     * 自定义session持久化
     * @return
     */
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());
        return redisSessionDAO;
    }

}
