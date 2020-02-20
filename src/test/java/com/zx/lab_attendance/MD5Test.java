package com.zx.lab_attendance;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/1 16:53
 * @Description
 */
public class MD5Test {

    @Test
    public void testMD5(){
        //加密算法
        String hashName = "md5";
        //密码明文
        String pwd = "123222";
        //加密函数，使用shiro自带
        Object result = new SimpleHash(hashName,pwd,null,2);

        System.out.println(result);
    }
}
