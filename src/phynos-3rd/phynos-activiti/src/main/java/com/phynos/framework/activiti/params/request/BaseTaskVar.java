package com.phynos.framework.activiti.params.request;

import java.util.Map;

/**
 * 提交任务 基本参数类
 * @author by Lupc
 * @date 2019/9/23.
 */
public class BaseTaskVar {

    private String taskId;
    private Map<String,Object> vars;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Map<String, Object> getVars() {
        return vars;
    }

    public void setVars(Map<String, Object> vars) {
        this.vars = vars;
    }
}
