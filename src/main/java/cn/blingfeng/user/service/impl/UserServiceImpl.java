package cn.blingfeng.user.service.impl;

import cn.blingfeng.commons.pojo.WorkResult;
import cn.blingfeng.user.mapper.UserMapper;
import cn.blingfeng.user.pojo.User;
import cn.blingfeng.user.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserSerivce {
    @Autowired
    private UserMapper userMapper;

    @Override
    public WorkResult checkAccount(User user) {
        List<User> userList = userMapper.selectUserByUsername(user.getUsername());
        if (userList == null || userList.size() == 0) {
            return WorkResult.error(400, "用户名或密码错误");
        }
        User realUser = userList.get(0);
//        将user的密码MD5加密后与password进行比较
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
         if(md5Password.equals(realUser.getPassword())){
             realUser.setPassword(null);
             return WorkResult.ok(realUser);
         }
         return WorkResult.error(400,"用户名或密码错误");
    }
}
