package cn.blingfeng.user.controller;

import cn.blingfeng.user.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("/userInfo")
    @ResponseBody
    public User getUserInfo(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userInfo");
        return user;
    }
}
