package cn.blingfeng.workflow.service.impl;

import cn.blingfeng.workflow.service.WorkFlowService;
import org.activiti.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : blingfeng
 * @date : 2017/10/15
 * @description
 **/
@Service
public class WorkFlowServiceImpl implements WorkFlowService{
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
    @Override
    public void reAttendStart() {

    }
}
