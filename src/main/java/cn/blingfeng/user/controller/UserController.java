package cn.blingfeng.user.controller;

import cn.blingfeng.commons.pojo.WorkResult;
import cn.blingfeng.user.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("user")
@Controller
public class UserController {
    @RequestMapping(value = "/userInfo")
    @ResponseBody
    public WorkResult getUserInfo(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userInfo");
        return WorkResult.ok(user);
    }

}
