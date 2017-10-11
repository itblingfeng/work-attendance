package cn.blingfeng.attence.controller;

import cn.blingfeng.attence.pojo.Attend;
import cn.blingfeng.attence.service.AttenceService;
import cn.blingfeng.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("attence")
public class AttenceController {
    @Autowired
    private AttenceService attenceService;
    @RequestMapping("/status")
    public String attenceStatus(HttpServletRequest request) {
        /**
        * @Author blingfeng
        * @Date 2017/10/11/011
        * @Description 根据用户id查询用户的考勤信息
        **/
        User user = (User) request.getSession().getAttribute("userInfo");
        List<Attend> attendInfo = attenceService.getAttendInfoByUserId(user.getId());
        request.setAttribute("attendInfo",attendInfo);
        return "attence";
    }
}