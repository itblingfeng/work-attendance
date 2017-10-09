package cn.blingfeng.login.controller;
import cn.blingfeng.user.service.UserSerivce;
import cn.blingfeng.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserSerivce userService;
    @RequestMapping()
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/checkAccount",method = RequestMethod.POST)
    public String checkAccount(User user, Model model){
        boolean userExit = userService.checkAccount(user);
//        将用户信息添加进session中
        model.addAttribute("user",user.getRealName());
//        若帐号存在则跳转首页
        if(userExit==true) {
            return "index";
        }else{
//            否则则跳转错误页
            return "error";
        }
    }
}
