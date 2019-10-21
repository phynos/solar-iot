package com.phynos.framework.activiti.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.phynos.framework.core.json.JsonResult;
import com.phynos.framework.core.json.ResultCodeEnum;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 模型管理
 * @author by Lupc
 * @date 2019/9/21.
 */
@RestController
@RequestMapping("/api/activiti/phynos/model")
public class ActivitiModelController {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    ObjectMapper objectMapper;

    /**
     * 获取所有模型
     */
    @RequestMapping("/list")
    public JsonResult modelList() {
        return JsonResult.data(repositoryService.createModelQuery().list());
    }

    /**
     * 新建一个空模型
     */
    @RequestMapping("/create")
    public JsonResult newModel() throws IOException {
        //初始化一个空模型
        Model model = repositoryService.newModel();
        //设置一些默认信息
        String name = "新建流程";
        String description = "";
        int revision = 1;
        String key = UUID.randomUUID().toString();;
        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);
        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());
        repositoryService.saveModel(model);
        String id = model.getId();
        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));

        Map<String,String> map = new HashMap<>();
        map.put("id",id);
        return JsonResult.data(map);
    }

    /**
     * 删除模型
     * @param ids
     * @return
     */
    @PostMapping("/del")
    public JsonResult deleteModel(@RequestBody List<String> ids){
        ids.forEach(id -> {
            repositoryService.deleteModel(id);
        });
        return JsonResult.code(ResultCodeEnum.OK);
    }

    /**
     * 发布模型为流程定义
     */
    @PostMapping("/deploy")
    public JsonResult deploy(String id) throws Exception {
        //获取模型
        Model modelData = repositoryService.getModel(id);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
        if (bytes == null) {
            //"模型数据为空，请先设计流程并成功保存，再进行发布。"
            return JsonResult.code(ResultCodeEnum.OPERATION_FAILED);
        }
        JsonNode modelNode = new ObjectMapper().readTree(bytes);
        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if (model.getProcesses().size() == 0) {
            //"数据模型不符要求，请至少设计一条主线流程。";
            return JsonResult.code(ResultCodeEnum.OPERATION_FAILED);
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
        //发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .category(modelData.getCategory())
                .addString(processName, new String(bpmnBytes, "UTF-8"))
                .deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);
        return JsonResult.code(ResultCodeEnum.OK);
    }

}
