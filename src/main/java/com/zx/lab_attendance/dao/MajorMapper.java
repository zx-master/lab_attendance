package com.zx.lab_attendance.dao;

import com.zx.lab_attendance.entity.Major;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/18 20:06
 * @Description
 */
@Repository
public interface MajorMapper {

    Major selectByPrimaryKey(Integer majorId);

    List<Major> selectAll();

}
