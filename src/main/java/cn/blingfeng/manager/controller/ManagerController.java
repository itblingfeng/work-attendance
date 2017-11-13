package cn.blingfeng.manager.controller;

import cn.blingfeng.commons.pojo.WorkResult;
import cn.blingfeng.commons.utils.ManagerQueryBean;

import cn.blingfeng.commons.utils.SecurityUtils;
import cn.blingfeng.manager.service.ManagerService;
import cn.blingfeng.user.pojo.Department;
import cn.blingfeng.user.pojo.User;
import cn.blingfeng.user.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : blingfeng
 * @date : 2017/11/09
 * @description 人事管理的Controller层
 **/
@Controller
@RequestMapping("staff")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserService userService;


    @RequestMapping()
    public String staffPage(){
        return "/staff/index";
    }
    @RequiresPermissions("staff:list")
    @RequestMapping("/list")
    @ResponseBody
    public ManagerQueryBean listStaff(ManagerQueryBean managerQueryBean) {
        User user = SecurityUtils.getUser();
        managerQueryBean.setUser(user);
        ManagerQueryBean staffQuery = managerService.getStaffList(managerQueryBean);
        return staffQuery;
    }
    @RequiresPermissions("staff:remove")
    @RequestMapping("/remove")
    @ResponseBody
    public WorkResult removeStaff(Long userId){
        WorkResult result = userService.removeUserById(userId);
        return result;
    }
    @RequiresPermissions("staff:update")
    @RequestMapping("/info")
    @ResponseBody
    public WorkResult getStaffInfo(Long userId){
        User user = userService.selectUserById(userId);
        return WorkResult.ok(user);
    }
    @RequiresPermissions("staff:update")
    @RequestMapping("/depList")
    @ResponseBody
    public WorkResult getDepList(){
        List<Department> depList = managerService.getDepList();
        return WorkResult.ok(depList);
    }

    @RequiresPermissions("staff:update")
    @RequestMapping("/update")
    @ResponseBody
    public WorkResult updateStaffInfo(User user){
        WorkResult result = userService.updateByUser(user);
        return result;
    }


}
