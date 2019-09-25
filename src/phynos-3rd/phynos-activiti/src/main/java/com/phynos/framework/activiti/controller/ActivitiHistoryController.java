package com.phynos.framework.activiti.controller;

import com.tf.traffic.core.json.JsonResult;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 历史流程
 * @author by Lupc
 * @date 2019/9/22.
 */
@RestController
@RequestMapping("/api/activiti/traffic/history")
public class ActivitiHistoryController {

    @Autowired
    private HistoryService historyService;


    @PostMapping("/task")
    public JsonResult getHistoryTask(){
        List<HistoricTaskInstance> historicTaskInstances = historyService
                .createHistoricTaskInstanceQuery()
                .finished()
                .list();
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            System.out.println(historicTaskInstance.getAssignee());
            System.out.println(historicTaskInstance.getName());
            System.out.println(historicTaskInstance.getId());
        }
        return JsonResult.data(historicTaskInstances);
    }

    @PostMapping("/activity")
    public JsonResult getHistoryActivity(){
        List<HistoricActivityInstance> historicActivityInstances = historyService
                .createHistoricActivityInstanceQuery()
                .list();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            System.out.println(historicActivityInstance.getActivityName());
        }
        return JsonResult.data(historicActivityInstances);
    }

}
