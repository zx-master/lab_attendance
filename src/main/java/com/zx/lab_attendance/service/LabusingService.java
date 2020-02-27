package com.zx.lab_attendance.service;

import com.zx.lab_attendance.dao.CourseandstuMapper;
import com.zx.lab_attendance.dao.LabusingMapper;
import com.zx.lab_attendance.entity.Labusing;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author zx
 * @version 1.0
 * @date 2020/2/24 1:47
 * @Description
 */
public interface LabusingService {

    List<Labusing> selectLabusingByMyCourseForTime(String studentId, String starttime, String endtime);


}
