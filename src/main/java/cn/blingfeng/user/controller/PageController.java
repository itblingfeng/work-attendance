package cn.blingfeng.user.controller;

import cn.blingfeng.user.service.UserSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
ssssss
@Controller
public class PageController {
    @Autowired
    private UserSerivce userSerivce;
    @RequestMapping("/user")
    public String User(){
        return "user";
    }
}
