package cn.blingfeng.login.controller;

import cn.blingfeng.commons.pojo.WorkResult;
import cn.blingfeng.user.service.UserSerivce;
import cn.blingfeng.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserSerivce userService;
    @RequestMapping()
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/checkAccount", method = RequestMethod.POST)
    @ResponseBody
    public WorkResult checkAccount(User user, HttpServletRequest request) {
        WorkResult result = userService.checkAccount(user);
        if (result.getStatus() == 200) {
            request.getSession().setAttribute("userInfo", result.getData());
        }
        return result;
    }
}
