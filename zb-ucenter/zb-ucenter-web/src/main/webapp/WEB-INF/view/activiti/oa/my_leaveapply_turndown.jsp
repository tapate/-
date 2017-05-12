<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    
    <title>请假申请调整</title>
	
    <!-- 静态css、js资源 -->
    <%@ include file="/WEB-INF/view/common/common.jsp"%>
    
    <script type="text/javascript" src="${ctx}/resources/js/activiti/oa/leaveapply_turndown.js"></script>
</head>
<body>
<!-- 获取被驳回需要重新调整的请假申请列表url -->
<input type="hidden" name="getMyLeaveApplyTurndownListUrl" id="getMyLeaveApplyTurndownListUrl" value="<c:url value="/oa/leaveapply/turndown/list"/>" />
<!-- 打开请假申请调整页面url -->
<input type="hidden" name="toLeaveApplyModifyViewUrl" id="toLeaveApplyModifyViewUrl" value="<c:url value="/oa/leaveapply/modify/view"/>" />
    
<div class="wrapper wrapper-content animated fadeInRight " id="deptleaderAuditListDiv" style="width: 100%; height: 100%;">
    <div class="ibox">
        <div class="ibox-content">
            <br/>
            <div class="row">
                <!-- 表格 -->
                <div class="col-md-10 col-lg-10">
                    <table class="table table-bordered table-hover" id="leaveApplyTurndownListTable"></table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
