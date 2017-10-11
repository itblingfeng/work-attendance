package cn.blingfeng.attence.service.impl;

import cn.blingfeng.attence.mapper.AttendMapper;
import cn.blingfeng.attence.pojo.Attend;
import cn.blingfeng.attence.service.AttenceService;
import cn.blingfeng.commons.utils.PageQueryBean;
import cn.blingfeng.commons.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author blingfeng
 * @Date 2017/10/11/011
 * @Description
 **/
@Service("attenceServiceImpl")
public class AttenceServiceImpl implements AttenceService {
    @Autowired
    private AttendMapper attendMapper;

    @Override
    public PageQueryBean getAttendInfoByUserId(QueryVo queryVo) {
            /*先查询总数
            若总数为零则直接返回，否则查询
            * */
        PageQueryBean pageQueryBean = new PageQueryBean();
        int count = attendMapper.selectCountByQueryVo(queryVo);
        if (count<=0)
            return null;
        List<Attend> attendList = attendMapper.selectByQueryVo(queryVo);
        pageQueryBean.setCurrentPage(queryVo.getCurrentPage());
        pageQueryBean.setTotalRows(count);
        pageQueryBean.setItems(attendList);
        pageQueryBean.setPageSize(queryVo.getPageSize());
        return pageQueryBean;
    }
}
