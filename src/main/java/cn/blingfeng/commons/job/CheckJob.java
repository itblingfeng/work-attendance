package cn.blingfeng.commons.job;

import cn.blingfeng.attence.service.AttenceService;
import org.springframework.beans.factory.annotation.Autowired;

public class CheckJob {
    @Autowired
    private AttenceService attenceService;
    /**
    * @Author blingfeng
    * @Date 2017/10/11/011
    * @Description 定时任务 检查数据库中签到异常的数据 进行修改
    **/
    public void checkAttend(){
        /**
        *首先检查当天没有签到的员工，若不存在记录，则插入一条记录，并置为异常状态
         * 然后检查，早上9：39之前签到，且晚上18：00以后签到的数据 置为正常
         * 早上迟到，晚上早退 计算缺勤时间
         * 晚上没有打卡也置为异常
         **/
        System.out.println("hello");
        attenceService.checkAttend();

    }
}
