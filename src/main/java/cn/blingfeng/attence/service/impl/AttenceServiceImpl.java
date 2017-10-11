package cn.blingfeng.attence.service.impl;

import cn.blingfeng.attence.mapper.AttendMapper;
import cn.blingfeng.attence.pojo.Attend;
import cn.blingfeng.attence.service.AttenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Author blingfeng
* @Date 2017/10/11/011
* @Description
**/
@Service("attenceServiceImpl")
public class AttenceServiceImpl implements AttenceService{
    @Autowired
    private AttendMapper attendMapper;
    @Override
    public List<Attend> getAttendInfoByUserId(Long userId) {
        List<Attend> attendInfo = attendMapper.selectByUserId(userId);
        return attendInfo;
    }
}
