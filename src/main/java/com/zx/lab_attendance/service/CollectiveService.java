package com.zx.lab_attendance.service;

import com.zx.lab_attendance.vo.CollectiveVO;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/10 11:21
 * @Description
 */
public interface CollectiveService {

    public List<CollectiveVO> selectAllCollective();

}
