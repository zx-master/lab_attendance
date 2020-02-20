package com.zx.lab_attendance.shiroConfig;

import com.zx.lab_attendance.entity.Permissions;
import com.zx.lab_attendance.entity.Role;
import com.zx.lab_attendance.entity.Users;
import com.zx.lab_attendance.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;


/**
 * @author zx
 * @version 1.0
 * @date 2020/1/31 15:17
 * @Description 自定义realm
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     *权限校验时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权 doGetAuthorizationInfo");
        Users newUser = (Users)principalCollection.getPrimaryPrincipal();
        Users users = userService.selectUserByUserNum(newUser.getUserNumber());

        List<String> roleList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();

        for (Role role : users.getRoleList()){
            roleList.add(role.getRoleName());
            for (Permissions permissions : role.getPermissionsList()){
                if(permissions != null) {
                    permissionList.add(permissions.getPermissions());
                }
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleList);
        simpleAuthorizationInfo.addStringPermissions(permissionList);

        return simpleAuthorizationInfo;
    }

    /**
     * 用户登录的时候调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证 doGetAuthenticationInfo");

        String usernumber = (String)authenticationToken.getPrincipal();

        //从token获取用户信息，token代表用户输入
        Users loginUser = userService.selectUserByUserNum(usernumber);
        System.out.println("realm校验用户：" + loginUser);

        //校验密码是否存在
        if (loginUser.getPassword() == null || "".equals(loginUser.getPassword())){
            return null;
        }

        return new SimpleAuthenticationInfo(loginUser,loginUser.getPassword(),this.getClass().getName());
    }
}
