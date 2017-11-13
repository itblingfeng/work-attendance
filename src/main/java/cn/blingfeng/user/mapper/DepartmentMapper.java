package cn.blingfeng.user.mapper;

import cn.blingfeng.user.pojo.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long depId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long depId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectDepList();
}