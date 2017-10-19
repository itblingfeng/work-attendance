package cn.blingfeng.user.service;

import cn.blingfeng.commons.pojo.WorkResult;
import cn.blingfeng.user.pojo.User;

public interface UserService {
    WorkResult checkAccount(User user);

    User selectUserByUsername(String username);
}
