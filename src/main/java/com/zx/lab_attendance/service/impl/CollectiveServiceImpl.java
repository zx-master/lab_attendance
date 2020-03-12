package com.zx.lab_attendance.service.impl;

import com.zx.lab_attendance.dao.CollectiveMapper;
import com.zx.lab_attendance.dao.UsersMapper;
import com.zx.lab_attendance.entity.Collective;
import com.zx.lab_attendance.entity.Department;
import com.zx.lab_attendance.entity.Users;
import com.zx.lab_attendance.service.CollectiveService;
import com.zx.lab_attendance.vo.CollectiveVO;
import com.zx.lab_attendance.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zx
 * @version 1.0
 * @date 2020/3/10 11:29
 * @Description
 */
@Service
public class CollectiveServiceImpl implements CollectiveService {

    @Autowired
    private CollectiveMapper collectiveMapper;
    @Autowired
    private UsersMapper usersMapper;


    @Override
    public List<CollectiveVO> selectAllCollective() {
        List<Collective> collectives = collectiveMapper.selectAll();
        List<CollectiveVO> collectiveVOS = new ArrayList<>();
        for (Collective collective : collectives) {
            CollectiveVO collectiveVO = new CollectiveVO();
            List<Users> users = usersMapper.selectByCollective(collective.getCollectiveId());
            if (users.size() != 0){
                collectiveVO.setCollectiveId(collective.getCollectiveId());
                collectiveVO.setCollective(collective.getCollectiveNumber() + "班");
                collectiveVO.setDepartment(collective.getDepartment().getDepartmentDescribe());
                collectiveVO.setCollectiveNum(users.size());
                collectiveVO.setMajor(users.get(0).getMajor().getMajorName());
                List<UserVO> userVOS = new ArrayList<>();
                for (Users user : users){
                    UserVO userVO = new UserVO();
                    userVO.setUserId(user.getUserId());
                    userVO.setUsername(user.getUsername());
                    userVO.setUserNumber(user.getUserNumber());
                    userVO.setEmail(user.getEmail());
                    userVO.setMajorName(user.getMajor().getMajorName());
                    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<判断是老师还是学生>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                    if (user.getTssign() == 2) {
                        Collective collective1 = collectiveMapper.selectByPrimaryKey(user.getClassordepartment());
                        userVO.setCollective(collective1.getDepartment().getDepartmentDescribe() + collective1.getCollectiveNumber() + "班");
                    }
                    userVOS.add(userVO);
                }
                collectiveVO.setUsersList(userVOS);
                collectiveVOS.add(collectiveVO);
            }
        }
        return collectiveVOS;
    }
}
