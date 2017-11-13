package cn.blingfeng.manager.service.impl;

import cn.blingfeng.commons.utils.ManagerQueryBean;
import cn.blingfeng.commons.utils.PageQueryBean;
import cn.blingfeng.manager.service.ManagerService;
import cn.blingfeng.user.mapper.DepartmentMapper;
import cn.blingfeng.user.mapper.UserMapper;
import cn.blingfeng.user.pojo.Department;
import cn.blingfeng.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : blingfeng
 * @date : 2017/11/09
 * @description
 **/
@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public ManagerQueryBean getStaffList(ManagerQueryBean managerQueryBean) {
        User user = managerQueryBean.getUser();
        /**
         * 查询员工总数
         */
        int staffCount = userMapper.selectStaffCountBydepId(user.getDepId());
        if(staffCount<=0){
            managerQueryBean.setTotalRows(0);
            return managerQueryBean;
        }
        /**
         * 查询员工列表
         */
        managerQueryBean.setTotalRows(staffCount);
        List<User> staffList = userMapper.selectStaffList(managerQueryBean);
        managerQueryBean.setItems(staffList);
        return managerQueryBean;
    }

    @Override
    public List<Department> getDepList() {
        List<Department> depList = departmentMapper.selectDepList();
        return depList;
    }
}
