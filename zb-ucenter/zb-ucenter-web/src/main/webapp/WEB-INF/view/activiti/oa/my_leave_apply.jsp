<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    
    <title>我发起的请假流程</title>
	
    <!-- 静态css、js资源 -->
    <%@ include file="/WEB-INF/view/common/common.jsp"%>
    
    <script type="text/javascript" src="${ctx}/resources/js/activiti/oa/my_leaveapply_list.js"></script>
</head>
<body>
<!-- 获取我发起的请假流程列表url -->
<input type="hidden" name="getMyLeaveApplyListUrl" id="getMyLeaveApplyListUrl" value="<c:url value="/oa/myLeaveApplyList"/>" />
<!-- 跟踪流程url -->
<input type="hidden" name="traceProcessUrl" id="traceProcessUrl" value="<c:url value="/oa/traceProcess"/>" />
    
<div class="wrapper wrapper-content animated fadeInRight " id="myLeaveApplyListDiv" style="width: 100%; height: 100%;">
    <div class="ibox">
        <div class="ibox-content">
            <br/>
            <div class="row">
                <!-- 表格 -->
                <div class="col-md-10 col-lg-10">
                    <table class="table table-bordered table-hover" id="myLeaveApplyListTable"></table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
