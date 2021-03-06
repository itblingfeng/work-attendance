package cn.blingfeng.attence.controller;

import cn.blingfeng.attence.service.AttenceService;
import cn.blingfeng.commons.utils.PageQueryBean;
import cn.blingfeng.commons.utils.SecurityUtils;
import cn.blingfeng.commons.vo.QueryVo;
import cn.blingfeng.user.pojo.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
/**
 * @author : blingfeng
 * @date : 2017/10/15
 * @description 根据QeuryVo查询考勤信息
 **/
@Controller
@RequestMapping("attence")
public class AttenceController {
    @Value("SESSSION_USER")
    private  String SESSSION_USER;
    @Autowired
    private AttenceService attenceService;
    @RequiresPermissions("attence:attenceList")
    @RequestMapping("/attenceList")
    @ResponseBody
    public PageQueryBean attenceStatus(QueryVo queryVo, HttpServletRequest request) {
        User user = SecurityUtils.getUser();
        String[] queryDate = queryVo.getRangeDate().split("/");
        queryVo.setStartDate(queryDate[0]);
        queryVo.setEndDate(queryDate[1]);
        queryVo.setUserId(user.getId());
        PageQueryBean pageQuery = attenceService.getAttendInfoByUserId(queryVo);
        return pageQuery;
    }

    @RequestMapping()
    public String attence() {

        return "attence";
    }
    @RequiresPermissions("attence:approve")
    @RequestMapping("/approve")
    public String approve(){
        return "approve";
    }
}