<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    
    <title>部门领导审批</title>
	
    <!-- 静态css、js资源 -->
    <%@ include file="/WEB-INF/view/common/common.jsp"%>
    
    <script type="text/javascript" src="${ctx}/resources/js/activiti/oa/dept_leader_audit.js"></script>
</head>
<body>
<!-- 获取部门领导需要审批的请假流程列表url -->
<input type="hidden" name="getDeptLeaderAuditListUrl" id="getDeptLeaderAuditListUrl" value="<c:url value="/oa/deptleader/auditlist"/>" />
<!-- 审批请假申请页面url -->
<input type="hidden" name="toDeptLeaderAuditHandleUrl" id="toDeptLeaderAuditHandleUrl" value="<c:url value="/oa/deptleader/audit/view"/>" />
    
<div class="wrapper wrapper-content animated fadeInRight " id="deptleaderAuditListDiv" style="width: 100%; height: 100%;">
    <div class="ibox">
        <div class="ibox-content">
            <br/>
            <div class="row">
                <!-- 表格 -->
                <div class="col-md-10 col-lg-10">
                    <table class="table table-bordered table-hover" id="deptleaderAuditListTable"></table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
