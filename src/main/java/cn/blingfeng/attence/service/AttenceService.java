package cn.blingfeng.attence.service;

import cn.blingfeng.attence.pojo.Attend;
import cn.blingfeng.commons.utils.PageQueryBean;
import cn.blingfeng.commons.vo.QueryVo;

public interface AttenceService {
   /**
    * 通过userId获取签到分页结果
    * @param queryVo 查询参数
    * @return 返回分页结果
    * */
     PageQueryBean getAttendInfoByUserId(QueryVo queryVo);
     /**
      * 定时器定时检查签到异常
      * */
     void checkAttend();
     /**
      * 通过ID查找签到记录
      * @param id 签到记录id
      * @return 返回签到记录
      * */
     Attend getAttendByAttendId(Long id);
}
