package cn.blingfeng.workflow.service.impl;

import cn.blingfeng.attence.mapper.AttendMapper;
import cn.blingfeng.attence.pojo.Attend;
import cn.blingfeng.commons.utils.PageQueryBean;
import cn.blingfeng.commons.vo.WorkFlowQueryVo;
import cn.blingfeng.workflow.mapper.ReAttendMapper;
import cn.blingfeng.workflow.pojo.ReAttend;
import cn.blingfeng.workflow.service.WorkFlowService;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : blingfeng
 * @date : 2017/10/15
 * @description
 **/
@Service
public class WorkFlowServiceImpl implements WorkFlowService {
    /**
     * 补签流id
     */
    private final String REATTEND_FLOW_START = "reattend";
    /**
     * 补签处理状态 1为处理中
     */
    private final Byte REATTEND_STATUS_DEAL = 1;
    /**
     * 补签处理状态 2为接受
     */
    private final Byte REATTEND_STATUS_ACCEPT = 2;
    /**
     * 补签处理状态 3为拒绝
     */
    private final Byte REATTEND_STATUS_REFUSE = 3;
    /**
     * 补签参数reattend map key
     */
    private final String MAP_KEY_REATTEND = "reattend";
    /**
     * 补签参数 当前处理人
     */
    private final String MAP_KEY_CURRENT_HANDLER = "current_handler";
    private final Byte ATTEND_STATUS_NORMAL = 1;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private IdentityService identityService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ReAttendMapper reAttendMapper;
    @Autowired
    private AttendMapper attendMapper;

    private final Byte ATTEND_STATUS_DEALING = 0;

    private final Byte Attend_STATUS_EXP = 2;

    @Override
    public void reAttendStart(ReAttend reAttend) throws ParseException {
        reAttend.setCurrentHandler("blingfeng");
        reAttend.setAttendDate(simpleDateFormat.parse(reAttend.getAttendDate_string()));
        reAttend.setStatus(REATTEND_STATUS_DEAL);
        try {
             reAttendMapper.insertSelective(reAttend);
             Attend attend = new Attend();
             attend.setId(reAttend.getAttendId());
             attend.setAttendStatus(ATTEND_STATUS_DEALING);
             attendMapper.updateByPrimaryKeySelective(attend);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        Map<String, Object> variables = new HashMap<>(5);
        variables.put(MAP_KEY_REATTEND, reAttend);
        variables.put(MAP_KEY_CURRENT_HANDLER, reAttend.getCurrentHandler());
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(REATTEND_FLOW_START, variables);
        String processInstanceId = processInstance.getId();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.complete(task.getId(), variables);
    }

    @Override
    public WorkFlowQueryVo listReAttend(WorkFlowQueryVo workFlowQueryVo) {
        List<Task> taskList = taskService.createTaskQuery().processVariableValueEquals(workFlowQueryVo.getUsername()).listPage(workFlowQueryVo.getStartRow(),workFlowQueryVo.getPageSize());
        List<ReAttend> reAttendList = new ArrayList<>();
        //若taskList不为空则遍历放入集合
        if(!CollectionUtils.isEmpty(taskList)){
            for(Task task:taskList){
                Map<String, Object> variable = taskService.getVariables(task.getId());
               ReAttend reAttend = (ReAttend) variable.get(MAP_KEY_REATTEND);
               reAttend.setTaskId(task.getId());
               reAttendList.add(reAttend);
            }
        }
//        手动分页
       workFlowQueryVo.setItems(reAttendList);
        workFlowQueryVo.setTotalRows(reAttendList.size());
        return workFlowQueryVo;
    }

    @Override
    public void approve(boolean result, String taskId) {
        Map<String, Object> variable = taskService.getVariables(taskId);
        ReAttend reAttend = (ReAttend) variable.get(MAP_KEY_REATTEND);
        Attend attend = new Attend();
        attend.setId(reAttend.getAttendId());
        if(result){
            reAttend.setStatus(REATTEND_STATUS_ACCEPT);
            attend.setAttendStatus(ATTEND_STATUS_NORMAL);
//            修改attence表中的状态为正常
        }else{
            reAttend.setStatus(REATTEND_STATUS_REFUSE);
            attend.setAttendStatus(Attend_STATUS_EXP);
        }
//        更新补签的状态
        reAttendMapper.updateByPrimaryKeySelective(reAttend);
        attendMapper.updateByPrimaryKeySelective(attend);
//        任务流结束
        taskService.complete(taskId);
    }
}
