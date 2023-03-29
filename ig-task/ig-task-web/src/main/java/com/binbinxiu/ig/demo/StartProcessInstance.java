package com.binbinxiu.ig.demo;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class StartProcessInstance {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;

    @PostConstruct
    public void deployProcessDefinition() {
        // 从 classpath 加载流程定义文件
        InputStream inputStream = this.getClass().getResourceAsStream("/processes/holiday.bpmn20.xml");
        // 部署流程定义
        Deployment deployment = repositoryService.createDeployment()
                .addInputStream("holiday.bpmn20.xml", inputStream)
                .name("Holiday Request")
                .deploy();
        // 打印部署信息
        System.out.println("流程定义已部署，部署 ID：" + deployment.getId()+"部署 key："+deployment.getKey());
        startProcessInstance();
    }

    public void startProcessInstance() {
        // 根据流程定义 ID 启动一个流程实例

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday");
        // 打印流程实例信息
        System.out.println("流程实例已启动，流程实例 ID：" + processInstance.getId());
    }
    public void submitHolidayRequest(String employeeName, int numberOfDays) {
        // 创建流程变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("employeeName", employeeName);
        variables.put("numberOfDays", numberOfDays);
        // 根据流程定义 key 启动一个流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday", variables);
        // 打印流程实例信息
        System.out.println("请假流程实例已启动，流程实例 ID：" + processInstance.getId());
    }

    @Autowired
    private TaskService taskService;

    public void approveHolidayRequest(String taskId, boolean approved, String remarks) {
        // 创建任务变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("approved", approved);
        variables.put("remarks", remarks);
        // 完成任务
        taskService.complete(taskId, variables);
    }

    public void queryTaskByTaskId(String taskId, boolean approved, String remarks) {
    }

}
