package cn.blingfeng.manager.service;

import cn.blingfeng.commons.utils.ManagerQueryBean;
import cn.blingfeng.commons.utils.PageQueryBean;
import cn.blingfeng.user.pojo.Department;

import java.util.List;

/**
 * @author : blingfeng
 * @date : 2017/11/09
 * @description
 **/
public interface ManagerService {
    ManagerQueryBean getStaffList(ManagerQueryBean managerQueryBean);
    List<Department> getDepList();
}
