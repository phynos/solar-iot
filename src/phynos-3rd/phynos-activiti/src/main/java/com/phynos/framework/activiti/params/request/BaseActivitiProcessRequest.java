package com.phynos.framework.activiti.params.request;

import java.util.Map;

/**
 * 新建流程实例 基类参数
 * @author by Lupc
 * @date 2019/9/22.
 */
public class BaseActivitiProcessRequest {

    private String processDefinitionId;
    private String keyName;
    private Map<String,Object> vars;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public Map<String, Object> getVars() {
        return vars;
    }

    public void setVars(Map<String, Object> vars) {
        this.vars = vars;
    }
}
