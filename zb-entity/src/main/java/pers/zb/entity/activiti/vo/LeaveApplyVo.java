package pers.zb.entity.activiti.vo;

import java.io.Serializable;

public class LeaveApplyVo implements Serializable {

    private static final long serialVersionUID = -1519908162453796274L;

    private String activityId;
    private String businessKey;
    private String executionId;
    private String processInstanceId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

}
