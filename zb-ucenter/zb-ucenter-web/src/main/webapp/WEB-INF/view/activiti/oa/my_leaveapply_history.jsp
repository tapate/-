<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    
    <title>我的请假历史</title>
	
    <!-- 静态css、js资源 -->
    <%@ include file="/WEB-INF/view/common/common.jsp"%>
    
    <script type="text/javascript" src="${ctx}/resources/js/activiti/oa/my_leaveapply_history.js"></script>
</head>
<body>
<!-- 获取我的请假申请历史列表url -->
<input type="hidden" name="getMyLeaveApplyHistoryListUrl" id="getMyLeaveApplyHistoryListUrl" value="<c:url value="/oa/leaveapply/history/list"/>" />
<!-- 获取请假申请的处理记录 -->
<input type="hidden" name="getLeaveApplyHandleRecordUrl" id="getLeaveApplyHandleRecordUrl" value="<c:url value="/oa/leaveapply/handle/record"/>" />
    
<div class="wrapper wrapper-content animated fadeInRight " id="myLeaveApplyHistoryListDiv" style="width: 100%; height: 100%;">
    <div class="ibox">
        <div class="ibox-content">
            <br/>
            <div class="row">
                <!-- 表格 -->
                <div class="col-md-10 col-lg-10">
                    <table class="table table-bordered table-hover" id="myLeaveApplyHistoryListTable"></table>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- 请假申请处理记录，以弹出框显示记录 -->
<div class="modal fade" id="leaveApplyHandleRecordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 800px;margin-left: -15%;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">处理记录</h4>
            </div>
            <div class="modal-body">
                <table id="leaveApplyHandleRecordTable" class="table table-condensed table-hover table-striped" style="font-size: 12px;">
                    <thead id="leaveApplyHandleRecordThead">
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <!-- <button type="button" class="btn btn-default" data-dismiss="modal">我知道了</button> -->
                <button type="button" data-dismiss="modal" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
