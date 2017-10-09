package cn.blingfeng.user.service;

import cn.blingfeng.user.pojo.User;

public interface UserSerivce {
    User findUserById();
    boolean checkAccount(User user);
}
