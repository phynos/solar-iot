package com.phynos.framework.activiti.entity;

import org.activiti.engine.repository.Deployment;

import java.util.Date;

/**
 * 流程部署 封装
 * @author by Lupc
 * @date 2019/9/22.
 */
public class ActivitiDeploymentWrapper {

    private String id;
    private String name;
    private Date deploymentTime;
    private String category;
    private String tenantId;

    public ActivitiDeploymentWrapper() {
    }

    public ActivitiDeploymentWrapper(Deployment deployment) {
        this.id = deployment.getId();
        this.name = deployment.getName();
        this.deploymentTime = deployment.getDeploymentTime();
        this.category = deployment.getCategory();
        this.tenantId = deployment.getTenantId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
        this.deploymentTime = deploymentTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

}
