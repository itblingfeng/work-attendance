package cn.blingfeng.workflow.service;

import cn.blingfeng.commons.vo.WorkFlowQueryVo;
import cn.blingfeng.workflow.pojo.ReAttend;

import java.text.ParseException;
import java.util.List;

/**
 * @author : blingfeng
 * @date : 2017/10/15
 * @description
 **/
public interface WorkFlowService {
    /** 用户补签方法
     *  @param reAttend 用户的补签记录
     *  @throws ParseException  日期转化异常
     *  */
    void reAttendStart(ReAttend reAttend) throws ParseException;
    /** 列出处理人下的所有补签请求
     *  @param workFlowQueryVo 查询补签列表的条件
     *  @return 返回补签记录集合
     * */
    WorkFlowQueryVo listReAttend(WorkFlowQueryVo workFlowQueryVo);
    /** 根据传入参数来决定补签的处理结果
     *  @param result 审批的结果 true为通过 false为不通过
     *  @param taskId 审批任务的id
     * */
    void approve(boolean result,String taskId);
}
