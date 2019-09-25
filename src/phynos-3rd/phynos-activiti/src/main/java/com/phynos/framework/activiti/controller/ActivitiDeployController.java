package com.phynos.framework.activiti.controller;

import com.tf.traffic.activiti.entity.ProcessDefinitionWrapper;
import com.tf.traffic.core.json.JsonList;
import com.tf.traffic.core.json.JsonResult;
import com.tf.traffic.core.json.ResultCodeEnum;
import com.tf.traffic.core.params.BaseParam;
import com.tf.traffic.core.params.IdStringParam;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by Lupc
 * @date 2019/9/21.
 */
@RestController
@RequestMapping("/api/activiti/traffic/deploy")
public class ActivitiDeployController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngineFactoryBean processEngine;

    /**
     * 查询 流程定义
     * @param definition
     * @param param
     * @return
     */
    @RequestMapping("/list")
    public JsonResult findProcessDefination(@RequestParam(required = false) ProcessDefinition definition, BaseParam param) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitionList = null;
        if (definition != null) {
            if (!StringUtils.isEmpty(definition.getDeploymentId())) {
                processDefinitionQuery.deploymentId(definition.getDeploymentId());
            }
            if (!StringUtils.isEmpty(definition.getName())) {
                processDefinitionQuery.processDefinitionNameLike("%" + definition.getName() + "%");
            }
        }
        processDefinitionList = processDefinitionQuery.listPage(param.getOffset(), param.getPageSize());
        long count = repositoryService.createDeploymentQuery().count();
        List<ProcessDefinitionWrapper> list = new ArrayList<>();
        processDefinitionList.forEach(processDefinition -> list.add(new ProcessDefinitionWrapper(processDefinition)));

        JsonList jl = JsonList.create((int) count, param.getPageIndex(), param.getPageSize(), list);
        return JsonResult.data(jl);
    }

    /**
     * 删除已部署
     * @param param 流程部署ID
     * @return
     */
    @RequestMapping("/del")
    public JsonResult delDeploy(@RequestBody IdStringParam param) {
        String deploymentId = param.getId();
        repositoryService.deleteDeployment(deploymentId, true);
        return JsonResult.code(ResultCodeEnum.OK);
    }

    /**
     * 查询流程定义 的图片
     * @param httpServletResponse
     * @param processDefinitionId
     * @throws IOException
     */
    @GetMapping("/image/{processDefinitionId}")
    public void getImage(
            HttpServletResponse httpServletResponse,
            @PathVariable String processDefinitionId
    ) throws IOException {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
        OutputStream outputStream = httpServletResponse.getOutputStream();
        IOUtils.copy(inputStream,outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
