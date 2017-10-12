package cn.blingfeng.attence.controller;

import cn.blingfeng.attence.pojo.Attend;
import cn.blingfeng.attence.service.AttenceService;
import cn.blingfeng.commons.utils.PageQueryBean;
import cn.blingfeng.commons.vo.QueryVo;
import cn.blingfeng.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("attence")
public class AttenceController {
    @Autowired
    private AttenceService attenceService;

    /**
     * @Author blingfeng
     * @Date 2017/10/11/011
     * @Description 根据QeuryVo查询考勤信息
     **/
    @RequestMapping("/attenceList")
    @ResponseBody
    public PageQueryBean attenceStatus(QueryVo queryVo, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("userInfo");
        String[] queryDate = queryVo.getRangeDate().split("/");
        queryVo.setStartDate(queryDate[0]);
        queryVo.setEndDate(queryDate[1]);
        queryVo.setUserId(user.getId());
        PageQueryBean pageQuery = attenceService.getAttendInfoByUserId(queryVo);
        pageQuery.setUserId(user.getId());
        return pageQuery;
    }

    @RequestMapping()
    public String attence() {
        return "attence";
    }
}