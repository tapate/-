<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    
    <title>OA请假申请</title>
	
    <!-- 静态css、js资源 -->
    <%@ include file="/WEB-INF/view/common/common.jsp"%>
    
    <script type="text/javascript" src="${ctx}/resources/js/activiti/oa/oa_apply.js"></script>
</head>
<body>
<div  class="col-md-10 col-lg-10" id="oaApplyDiv">
    <br/>
    <br/>
    
    <!-- 保存OA申请数据url -->
    <input type="hidden" id="saveOaApplyUrl" name="saveOaApplyUrl" value="<c:url value="/oa/saveOaApply"/>"/>
    
    <form class="form-horizontal" id="oaApplyForm">
        <div class="form-group">
            <label class="col-xs-1 control-label">请假类型</label>
            <div class="col-xs-5">
                <div class="input-group">
                    <select class="form-control" name="type" id="type">
                        <c:forEach items="${leaveTypeList}" var="type">
                            <option value="${type}">${type.description}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-1 control-label">开始日期</label>
            <div class="col-xs-5">
                <input type="text" class="form-control" placeholder="请选择开始日期" name="startDate" id="startDate" value="">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-1 control-label">结束日期</label>
            <div class="col-xs-5">
                <input type="text" class="form-control" placeholder="请选择结束日期" name="endDate" id="endDate" value="">
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-1 control-label">请假原因</label>
            <div class="col-xs-5">
                <textarea rows="5" cols="50" id="reason" name="reason"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-xs-1 control-label"></label>
            <div class="col-xs-5">
                <div class="btn btn-primary" data-toggle="modal" id="oaApplySubmitBtn">提交</div>
                <div class="btn btn-primary" data-toggle="modal" id="oaApplyResetBtn">重置</div>
            </div>
        </div>
    </form>
</div>

</body>
</html>
