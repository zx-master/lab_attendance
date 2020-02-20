package com.zx.lab_attendance;

import com.zx.lab_attendance.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;


/**
 * @author zx
 * @version 1.0
 * @date 2020/2/10 0:34
 * @Description
 */
public class UidTest {

    @Test
    public void getUid(){
            IdWorker idWorker = new IdWorker(0, 0);
            for (int i = 0; i < 10; i++) {
                long id = idWorker.nextId();
                System.out.println("LU" +String.valueOf(id));
//                System.out.println(StringUtils.leftPad(Long.toBinaryString(id), 64, "0"));
        }
    }
}
