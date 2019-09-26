package com.phynos.framework.activiti.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phynos.framework.core.json.JsonResult;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 流程管理
 *
 * @author by Lupc
 * @date 2019/9/21.
 */
@RestController
@RequestMapping("/api/activiti/traffic/process")
public class ActivitiProcessController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HistoryService historyService;
    @Autowired
    ProcessEngineFactoryBean processEngine;
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;

    /**
     * 启动流程
     */
    @PostMapping("/start")
    public JsonResult startProcess(@RequestBody BaseActivitiProcessRequest param) {
        String processDefinitionId = param.getProcessDefinitionId();
        Map<String,Object> vars = param.getVars();
        ProcessInstance process = runtimeService.startProcessInstanceById(processDefinitionId,vars);
        logger.debug(processDefinitionId + " : " + process.getId() + " : " + process.getProcessDefinitionId());
        Map<String,String> map = new HashMap<>();
        map.put("processDefinitionId",processDefinitionId);//流程模型ID
        map.put("processDefinitionKey",process.getProcessDefinitionKey());//流程模型KEY
        map.put("processInstanceId",process.getId());//流程实例ID
        return JsonResult.data(map);
    }

    /**
     * 获取流程图片——直接返回图片字节流
     * @param httpServletResponse
     * @param processInstanceId
     * @throws IOException
     */
    @GetMapping("/image/{processInstanceId}")
    public void getProcessImage(
            HttpServletResponse httpServletResponse,
            @PathVariable String processInstanceId) throws IOException {
        httpServletResponse.setContentType("image/png");
        JSONArray shineProImages = new JSONArray();
        InputStream imageStream = generateStream(processInstanceId, true);
        if (imageStream != null) {
            String imageCurrentNode = Base64Utils.ioToBase64(imageStream);
            if (StringUtils.isNotBlank(imageCurrentNode)) {
                shineProImages.put(imageCurrentNode);
            }
        }
        InputStream imageNoCurrentStream = generateStream(processInstanceId, false);
        if (imageNoCurrentStream != null) {
            OutputStream os = httpServletResponse.getOutputStream();
            IOUtils.copy(imageNoCurrentStream,os);
            os.flush();
            os.close();
        }
    }

    @PostMapping("/images/base64")
    public String getProcessImageBase64(
            @RequestBody IdStringParam param) throws IOException {
        String processInstanceId = param.getId();
        JSONObject result = new JSONObject();
        JSONArray shineProImages = new JSONArray();
        InputStream imageStream = generateStream(processInstanceId, true);
        if (imageStream != null) {
            String imageCurrentNode = Base64Utils.ioToBase64(imageStream);
            if (StringUtils.isNotBlank(imageCurrentNode)) {
                shineProImages.put(imageCurrentNode);
            }
        }
        InputStream imageNoCurrentStream = generateStream(processInstanceId, false);
        if (imageNoCurrentStream != null) {
            String imageNoCurrentNode = Base64Utils.ioToBase64(imageNoCurrentStream);
            if (StringUtils.isNotBlank(imageNoCurrentNode)) {
                shineProImages.put(imageNoCurrentNode);
            }
        }
        result.put("id", UUID.randomUUID().toString());
        result.put("errorNo", 0);
        result.put("images", shineProImages);
        return result.toString();
    }

    private InputStream generateStream(
            String processInstanceId,
            boolean needCurrent) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        String processDefinitionId = null;
        List<String> executedActivityIdList = new ArrayList<>();
        List<String> currentActivityIdList = new ArrayList<>();
        List<HistoricActivityInstance> historicActivityInstanceList = new ArrayList<>();
        if (processInstance != null) {
            processDefinitionId = processInstance.getProcessDefinitionId();
            if (needCurrent) {
                currentActivityIdList = this.runtimeService.getActiveActivityIds(processInstance.getId());
            }
        }
        if (historicProcessInstance != null) {
            processDefinitionId = historicProcessInstance.getProcessDefinitionId();
            historicActivityInstanceList =
                    historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
            for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                executedActivityIdList.add(activityInstance.getActivityId());
            }
        }

        if (StringUtils.isEmpty(processDefinitionId) || executedActivityIdList.isEmpty()) {
            return null;
        }

        //高亮线路id集合
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity, historicActivityInstanceList);

        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        //List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
        ProcessDiagramGenerator diagramGenerator = (ProcessDiagramGenerator) processEngineConfiguration.getProcessDiagramGenerator();
        //ProcessDiagramGenerator diagramGenerator = new DefaultProcessDiagramGenerator();
        //List<String> activeIds = this.runtimeService.getActiveActivityIds(processInstance.getId());

        InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel,
                "png",
                executedActivityIdList,
                highLightedFlows,
                processEngine.getProcessEngineConfiguration().getActivityFontName(),
                processEngine.getProcessEngineConfiguration().getLabelFontName(),
                "宋体",
                null, 1.0);

        return imageStream;
    }

    /**
     * 获取需要高亮的线
     *
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {

        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }

}
