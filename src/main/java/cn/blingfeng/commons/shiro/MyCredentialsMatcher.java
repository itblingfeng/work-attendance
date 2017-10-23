package cn.blingfeng.commons.shiro;


import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.util.DigestUtils;


/**
 * @author : blingfeng
 * @date : 2017/10/19
 * @description
 **/
public class MyCredentialsMatcher extends SimpleCredentialsMatcher {
    /**
     * 该方法获取用户角色以及权限的对象 以及数据库中的正确用户密码
     * token中存储用户表单中输入的用户名密码等信息
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        /**md5加密前的密码*/
        String password = String.valueOf(usernamePasswordToken.getPassword());
        /** token令牌 将密码MD5加密 */
        Object tokenCredentials = DigestUtils.md5DigestAsHex(password.getBytes());
        /** 账户令牌 该方法可获取之前存储的密码*/
        Object accountCredentials = getCredentials(info);


        try {
            return equals(tokenCredentials, accountCredentials);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
