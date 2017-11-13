package cn.blingfeng.login.controller;

import cn.blingfeng.commons.pojo.WorkResult;
import cn.blingfeng.user.pojo.User;
import cn.blingfeng.user.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : blingfeng
 * @date : 2017/10/15
 * @description
 **/
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private UserService userService;
    private final Integer normalCode = 200;

    @RequestMapping()
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/checkAccount", method = RequestMethod.POST)
    @ResponseBody
    public WorkResult checkAccount(User user) {
        WorkResult result = userService.checkAccount(user);
        return result;
    }

    @RequestMapping("/signOut")
    public String signOut() {
        SecurityUtils.getSubject().getSession().removeAttribute("userInfo");
        return "redirect:";
    }
    @RequestMapping("/unauthirized")
    public String unAuthorized(){
        return "unauthirized";
    }
}
