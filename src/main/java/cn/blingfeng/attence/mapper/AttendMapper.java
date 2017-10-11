package cn.blingfeng.attence.mapper;

import cn.blingfeng.attence.pojo.Attend;

import java.util.List;

public interface AttendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);
    List<Attend> selectByUserId(Long userId);
}