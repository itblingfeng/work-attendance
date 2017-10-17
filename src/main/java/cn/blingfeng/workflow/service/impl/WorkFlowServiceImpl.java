package cn.blingfeng.workflow.service.impl;

import cn.blingfeng.workflow.mapper.ReAttendMapper;
import cn.blingfeng.workflow.pojo.ReAttend;
import cn.blingfeng.workflow.service.WorkFlowService;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Override
    public void reAttendStart(ReAttend reAttend) {
        reAttend.setCurrentHandler("blingfeng");
        reAttend.setStatus(REATTEND_STATUS_DEAL);
        reAttendMapper.insertSelective(reAttend);
        Map<String, Object> variables = new HashMap<>(5);
        variables.put(MAP_KEY_REATTEND, reAttend);
        variables.put(MAP_KEY_CURRENT_HANDLER, reAttend.getCurrentHandler());
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(REATTEND_FLOW_START, variables);
        String processInstanceId = processInstance.getId();
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        taskService.complete(task.getId(), variables);
    }
}
