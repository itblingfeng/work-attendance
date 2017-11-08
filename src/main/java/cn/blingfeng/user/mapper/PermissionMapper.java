package cn.blingfeng.user.mapper;

import cn.blingfeng.user.pojo.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long permissionid);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long permissionid);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}