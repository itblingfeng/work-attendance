package cn.blingfeng.commons.shiro;

import cn.blingfeng.user.pojo.Permission;
import cn.blingfeng.user.pojo.Role;
import cn.blingfeng.user.pojo.User;
import cn.blingfeng.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author : blingfeng
 * @date : 2017/10/19
 * @description
 **/
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.selectUserByUsername(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        /**
         * 查询该用户的角色以及角色所拥有的所有权限
         */
        for (Role role : user.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            for (Permission permission : role.getPermissionList()) {
                authorizationInfo.addStringPermission(permission.getPermission());
             }

        }
        return authorizationInfo;
    }

    /**
     * 用户登陆认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        User user = userService.selectUserByUsername(username);
        if(user==null){
            return null;
        }else{
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username,user.getPassword(),this.getName());
            SecurityUtils.getSubject().getSession().setAttribute("userInfo",user);
            return authenticationInfo;
        }

    }
}
