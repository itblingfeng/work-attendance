package cn.blingfeng.attence.service.impl;

import cn.blingfeng.attence.mapper.AttendMapper;
import cn.blingfeng.attence.pojo.Attend;
import cn.blingfeng.attence.service.AttenceService;
import cn.blingfeng.commons.utils.PageQueryBean;
import cn.blingfeng.commons.vo.QueryVo;
import cn.blingfeng.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author blingfeng
 * @Date 2017/10/11/011
 * @Description
 **/
@Service
public class AttenceServiceImpl implements AttenceService {
    @Autowired
    private AttendMapper attendMapper;
    @Autowired
    private UserMapper userMapper;
//    1为正常 2为异常
    private final Byte status = 2;

    private final Integer absence = 480;

    private final String morning = "09:30:00";

    private final String evening = "18:00:00";

    private  Byte week = 2;


    @Override
    public PageQueryBean getAttendInfoByUserId(QueryVo queryVo) {
            /*先查询总数
            若总数为零则直接返回，否则查询
            * */
        PageQueryBean pageQueryBean = new PageQueryBean();
        int count = attendMapper.selectCountByQueryVo(queryVo);
        if (count <= 0)
            return null;
        List<Attend> attendList = attendMapper.selectByQueryVo(queryVo);
        pageQueryBean.setCurrentPage(queryVo.getCurrentPage());
        pageQueryBean.setTotalRows(count);
        pageQueryBean.setItems(attendList);
        pageQueryBean.setPageSize(queryVo.getPageSize());
        return pageQueryBean;
    }

    @Override
    public void checkAttend() {
        /**
         *首先检查当天没有签到的员工，若不存在记录，则插入一条记录，并置为异常状态
         * 然后检查，早上9：39之前签到，且晚上18：00以后签到的数据 置为正常
         * 晚上没有打卡也置为异常
         **/
//        查询出当天所有未签到的用户
        List<Long> userIdList = userMapper.selectNoAttendUser();
//        插入一条记录
        for(Long userId :userIdList){
            Attend attend = new Attend();
            attend.setUserId(userId);
            attend.setAttendDate(new Date());
            attend.setAbsence(absence);
            attend.setAttendWeek(week);
            attendMapper.insertSelective(attend);
        }
//        更新所有异常的信息
        attendMapper.updateExceptionAttend(morning,evening);

    }





}
