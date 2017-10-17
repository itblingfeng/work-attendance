package cn.blingfeng.workflow.controller;

import cn.blingfeng.user.pojo.User;
import cn.blingfeng.workflow.pojo.ReAttend;
import cn.blingfeng.workflow.service.WorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : blingfeng
 * @date : 2017/10/15
 * @description 工作流
 **/
@RequestMapping("workflow")
@Controller
public class WorkFlowController {
    @Autowired
    private WorkFlowService workFlowService;

    @RequestMapping(value="/reAttend",method = RequestMethod.POST)
    public String reAttend(ReAttend reAttend, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userInfo");
        reAttend.setReAttendStarter(user.getRealName());
        workFlowService.reAttendStart(reAttend);
        return "attence";
    }

    @RequestMapping("/list")
    public String listReattend() {
        return null;
    }

    @RequestMapping("/approve")
    public String approveReattend() {
        return null;
    }

}
