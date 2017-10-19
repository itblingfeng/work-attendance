package cn.blingfeng.user.mapper;

import cn.blingfeng.user.pojo.User;

import java.util.Date;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByUsername(String username);

    List<Long> selectNoAttendUser();




}