package cn.blingfeng.user.service.impl;

import cn.blingfeng.user.mapper.UserMapper;
import cn.blingfeng.user.pojo.User;
import cn.blingfeng.user.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserSerivce{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserById() {
        User user = userMapper.selectByPrimaryKey(1l);
        return user;
    }
}
