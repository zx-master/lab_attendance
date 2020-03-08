package com.zx.lab_attendance.service;

import com.github.pagehelper.PageInfo;
import com.zx.lab_attendance.entity.Applylab;
import com.zx.lab_attendance.vo.ApplyLabVO;
import com.zx.lab_attendance.vo.ChatmsgInfo;
import com.zx.lab_attendance.vo.NewsVO;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/5 0:37
 * @Description
 */
public interface ApplylabService {

    /**
     * @author: zx
     * @return:
     * @parameter:
     * @describe: 插入申请信息
     */
    public NewsVO insertApplyLab(Applylab applylab);

    /**
     * @author: zx
     * @return:
     * @parameter:
     * @describe: 更新数据
     */
    public void updateApplyLab(ChatmsgInfo applylab);

    /**
     * @author: zx
     * @paramater:
     * @process:
     * @describe: 查询申请数据根据userId
     */
    public PageInfo<ApplyLabVO> selectApplyLabByUserId(String usreId, int currentPage, int pageSize);


}
