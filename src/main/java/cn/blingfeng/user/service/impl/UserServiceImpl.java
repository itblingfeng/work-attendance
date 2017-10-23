package cn.blingfeng.user.service.impl;

import cn.blingfeng.commons.pojo.WorkResult;
import cn.blingfeng.user.mapper.UserMapper;
import cn.blingfeng.user.pojo.User;
import cn.blingfeng.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public WorkResult checkAccount(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        if(user.getRemeberMe()!=null){
            token.setRememberMe(true);
        }else{
            token.setRememberMe(false);
        }
        try {
            subject.login(token);
            subject.getSession().setTimeout(18000000);
        }catch(Exception e){
            return WorkResult.error(400,e.getMessage());
        }
        return WorkResult.ok();

    }

    @Override
    public User selectUserByUsername(String username) {
        User user = userMapper.selectUserByUsername(username);
        return user;
    }

    @Override
    public Boolean checkUserExistByUsername(String username) {
        Integer count = userMapper.checkUserExistByUsername(username);
        return count == 1;
    }
}
