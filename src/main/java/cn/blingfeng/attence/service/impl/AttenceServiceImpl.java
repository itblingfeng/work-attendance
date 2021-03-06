package cn.blingfeng.attence.service.impl;

import cn.blingfeng.attence.mapper.AttendMapper;
import cn.blingfeng.attence.pojo.Attend;
import cn.blingfeng.attence.service.AttenceService;
import cn.blingfeng.commons.utils.PageQueryBean;
import cn.blingfeng.commons.vo.QueryVo;
import cn.blingfeng.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    /**
     * 1为正常 2为异常
     */
    @Value("${ATTEND_STATUS}")
    private Byte ATTEND_STATUS;
    @Value("${ATTEND_ABSENCE}")
    private Integer ATTEND_ABSENCE;
    @Value("${ATTEND_MORNING}")
    private String ATTEND_MORNING;
    @Value("${ATTEND_EVENING}")
    private String ATTEND_EVENING;
    @Value("${ATTEND_WEEK}")
    private Byte ATTEND_WEEK;


    @Override
    public PageQueryBean getAttendInfoByUserId(QueryVo queryVo) {
            /*先查询总数
            若总数为零则直接返回，否则查询
            * */
        PageQueryBean pageQueryBean = new PageQueryBean();
        int count = attendMapper.selectCountByQueryVo(queryVo);
        pageQueryBean.setCurrentPage(queryVo.getCurrentPage());
        pageQueryBean.setTotalRows(count);
        pageQueryBean.setPageSize(queryVo.getPageSize());
        if (count > 0) {
            List<Attend> attendList = attendMapper.selectByQueryVo(queryVo);
            pageQueryBean.setItems(attendList);
            pageQueryBean.setUserId(queryVo.getUserId());
        }
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
        for (Long userId : userIdList) {
            Attend attend = new Attend();
            attend.setUserId(userId);
            attend.setAttendDate(new Date());
            attend.setAbsence(ATTEND_ABSENCE);
            attend.setAttendWeek(ATTEND_WEEK);
            attendMapper.insertSelective(attend);
        }
//        更新所有异常的信息
        attendMapper.updateExceptionAttend(ATTEND_MORNING, ATTEND_EVENING);

    }

    @Override
    public Attend getAttendByAttendId(Long id) {
        Attend attend = attendMapper.selectByPrimaryKey(id);
        return attend;
    }


}
