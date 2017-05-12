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
    <title>调整请假申请</title>
    
    <script type="text/javascript" src="${ctx}/resources/js/activiti/oa/leaveapply_turndown_modify.js"></script>
</head>
<body>
<!-- 重新提交请假申请URL -->
<input type="hidden" name="leaveapplyTurndownModifyHandleUrl"  id="leaveapplyTurndownModifyHandleUrl" value="<c:url value="/oa/leaveapply/turndown/modify/handle"/>"/>
    
<div style="width: 600px; height: 500px; padding: 15px; overflow: auto;">
    <form class="form-horizontal" id="leaveapplyTurndownModifyForm">
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
            <label class="col-xs-3 control-label">是否继续申请</label>
            <div class="col-xs-5">
                <div class="input-group">
                    <select class="form-control" name="reapply" id="reapply">
                        <option value="true">重新申请</option>
                        <option value="false">不再申请</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>
