package cn.blingfeng.attence.service;

import cn.blingfeng.commons.utils.PageQueryBean;
import cn.blingfeng.commons.vo.QueryVo;

public interface AttenceService {

     PageQueryBean getAttendInfoByUserId(QueryVo queryVo);
}
