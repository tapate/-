<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath %>"/>

<!DOCTYPE>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>请假申请审批</title>
    
    <script type="text/javascript" src="${ctx}/resources/js/activiti/oa/hr_audit_detail.js"></script>
</head>
<body>
<!-- 处理请假申请URL -->
<input type="hidden" name="hrAuditHandleUrl"  id="hrAuditHandleUrl" value="<c:url value="/oa/hr/audit/handle"/>"/>
    
<div style="width: 600px; height: 500px; padding: 15px; overflow: auto;">
    <form class="form-horizontal" id="hrAuditDetailForm">
        <!-- 任务ID -->
        <input type="hidden" name="taskId"  id="taskId" value="${taskId}"/>
        
        <div class="form-group">
            <label class="col-xs-3 control-label">申请人</label>
            <div class="col-xs-5">
                <input type="text" class="form-control" name="userName" id="userName" value="${applyDetail.userName}" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 control-label">申请时间</label>
            <div class="col-xs-5">
                <input type="text" class="form-control" name="description" id="createTime" value="${createTime}" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 control-label">开始日期</label>
            <div class="col-xs-5">
                <input type="text" class="form-control" name="startDate" id="startDate" value="${startDate}" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 control-label">结束日期</label>
            <div class="col-xs-5">
                <input type="text" class="form-control" name="endDate" id="endDate" value="${endDate}" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 control-label">请假类型</label>
            <div class="col-xs-5">
                <input type="text" class="form-control" name="typeName" id="typeName" value="${applyDetail.typeName}" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 control-label">请假事由</label>
            <div class="col-xs-5">
                <input type="text" class="form-control" name="reason" id="reason" value="${applyDetail.reason}" readonly="readonly">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-3 control-label">审批意见</label>
            <div class="col-xs-5">
                <div class="input-group">
                    <select class="form-control" name="hrApproveResult" id="hrApproveResult">
                        <c:forEach items="${approvalOpinion}" var="ao">
                            <option value="${ao}">${ao.description}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>
