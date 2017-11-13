package cn.blingfeng.commons.utils;

import cn.blingfeng.user.pojo.User;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author : blingfeng
 * @date : 2017/11/09
 * @description
 **/
public class SecurityUtils {
    /**
     * @param attr
     * @return 返回session属性值
     */
    @Value("${SESSSION_USER}")
    private static String SESSION_USER;

    public static Object getSessionAttr(Object attr) {
        Object attribute = org.apache.shiro.SecurityUtils.getSubject().getSession().getAttribute(attr);
        return attribute;
    }

    /**
     *
     * @return 返回登陆后的用户信息
     */
    public static User getUser() {
        User user = (User) org.apache.shiro.SecurityUtils.getSubject().getSession().getAttribute("userInfo");
        return user;
    }
}
