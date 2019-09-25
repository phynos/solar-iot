package com.phynos.framework.activiti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tf.traffic.activiti.entity.ActivitiTaskWrapper;
import com.tf.traffic.activiti.params.request.BaseTaskVar;
import com.tf.traffic.core.json.JsonResult;
import com.tf.traffic.core.json.ResultCodeEnum;
import com.tf.traffic.core.params.IdStringParam;
import com.tf.traffic.dao.mapper.UserMapper;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author by Lupc
 * @date 2019/9/21.
 */
@RestController
@RequestMapping("/api/activiti/traffic/task")
public class ActivitiTaskController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ObjectMapper objectMapper;
    @Resource
    UserMapper userMapper;

    /**
     * 完成任务
     * @param param
     * @return
     */
    @PostMapping("/complete")
    public JsonResult completeTask(@RequestBody BaseTaskVar param) {
        String taskId = param.getTaskId();
        Map<String,Object> map = param.getVars();
        taskService.complete(taskId,map);
        return JsonResult.code(ResultCodeEnum.OK);
    }

    /**
     * 查询某人任务列表
     * @param param 用户ID
     * @return
     */
    @PostMapping("/findUserTask")
    public JsonResult findUserTask(@RequestBody IdStringParam param) {
        com.tf.traffic.dao.model.User userAdmin = userMapper.selectByPrimaryKey(param.getId());
        String userId = userAdmin == null? "" : userAdmin.getUsername();
        List<Task> taskList = taskService
                .createTaskQuery()
                .taskCandidateUser(userId)//候选人
                .orderByTaskCreateTime()
                .desc()
                .list();
        List<Task> assigneeTasks =  taskService
                .createTaskQuery()
                .taskAssignee(userId)//参与者，个人任务查询
                .orderByTaskCreateTime()
                .desc()
                .list();
        List<Task> candidateTasks = taskService
                .createTaskQuery()
                .taskCandidateUser(userId)//参与者，组任务查询
                .orderByTaskCreateTime()
                .desc()
                .list();
        taskList.addAll(assigneeTasks);
        taskList.addAll(candidateTasks);

        List<ActivitiTaskWrapper> result = new ArrayList<>();
        Set<String> taskSet = new HashSet<>();
        for(Task task : taskList){
            String taskId = task.getId();
            if (taskSet.contains(taskId)) {
                continue;
            }
            taskSet.add(taskId);
            result.add(new ActivitiTaskWrapper(task));
        }
        return JsonResult.data(result);
    }

}
