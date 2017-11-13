package cn.blingfeng.commons.utils;

import cn.blingfeng.user.pojo.User;

/**
 * @author : blingfeng
 * @date : 2017/11/09
 * @description
 **/
public class ManagerQueryBean extends PageQueryBean{
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
