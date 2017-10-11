package cn.blingfeng.attence.mapper;

import cn.blingfeng.attence.pojo.Attend;
import cn.blingfeng.commons.vo.QueryVo;

import java.util.List;

public interface AttendMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Attend record);

    int insertSelective(Attend record);

    Attend selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Attend record);

    int updateByPrimaryKey(Attend record);

    int selectCountByQueryVo(QueryVo queryVo);

    List<Attend> selectByQueryVo(QueryVo queryVo);
}