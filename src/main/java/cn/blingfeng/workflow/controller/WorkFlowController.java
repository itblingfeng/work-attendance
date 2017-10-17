package cn.blingfeng.workflow.controller;

import cn.blingfeng.attence.pojo.Attend;
import cn.blingfeng.attence.service.AttenceService;
import cn.blingfeng.commons.pojo.WorkResult;
import cn.blingfeng.commons.vo.WorkFlowQueryVo;
import cn.blingfeng.user.pojo.User;
import cn.blingfeng.workflow.pojo.ReAttend;
import cn.blingfeng.workflow.service.WorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private AttenceService attenceService;
    private final String SESSION_USERINFO_ATTR = "userInfo";


    @RequestMapping(value = "/reAttend", method = RequestMethod.POST)
    public String reAttend(ReAttend reAttend, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SESSION_USERINFO_ATTR);
        reAttend.setReAttendStarter(user.getRealName());
        Attend attend = attenceService.getAttendByAttendId(reAttend.getAttendId());
        reAttend.setReAttendMor(attend.getAttendMorning());
        reAttend.setReAttendEve(attend.getAttendEvening());
        try {
            workFlowService.reAttendStart(reAttend);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "attence";
    }

    @RequestMapping("/list")
    @ResponseBody
    public WorkFlowQueryVo listReattend(HttpServletRequest request, WorkFlowQueryVo workFlowQueryVo) {
        User user = (User) request.getSession().getAttribute(SESSION_USERINFO_ATTR);
        workFlowQueryVo.setUsername(user.getUsername());
        WorkFlowQueryVo queryResult = workFlowService.listReAttend(workFlowQueryVo);
        return queryResult;
    }

    @RequestMapping("/approve")
    @ResponseBody
    public WorkResult approveReattend(String taskId, boolean approve_result, HttpServletRequest request) {
        workFlowService.approve(approve_result, taskId);
        return WorkResult.ok();
    }

}
