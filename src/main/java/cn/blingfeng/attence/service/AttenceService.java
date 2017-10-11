package cn.blingfeng.attence.service;

import cn.blingfeng.attence.pojo.Attend;

import java.util.List;

public interface AttenceService {
     List<Attend> getAttendInfoByUserId(Long userId);
}
