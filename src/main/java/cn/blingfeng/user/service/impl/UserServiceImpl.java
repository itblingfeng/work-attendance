package cn.blingfeng.user.service.impl;

import cn.blingfeng.user.mapper.UserMapper;
import cn.blingfeng.user.pojo.User;
import cn.blingfeng.user.service.UserSerivce;
import com.sun.mail.smtp.DigestMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;

import java.util.List;

@Service
public class UserServiceImpl implements UserSerivce{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserById() {
        User user = userMapper.selectByPrimaryKey(1l);
        return user;
    }

    @Override
    public boolean checkAccount(User user) {
        List<User> userList = userMapper.selectUserByUsername(user.getUsername());
        if(userList==null||userList.size()==0)
            return false;
         User realUser = userList.get(0);
//        将user的密码MD5加密后与password进行比较
        if(realUser.getPassword().equals(user.getPassword())){
            return true;
        }
        return false;
    }
}
