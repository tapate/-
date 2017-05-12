$(function() {
	$("#deptleaderAuditListTable").bootstrapTable({
		contentType: "application/x-www-form-urlencoded",
		url : $("#getDeptLeaderAuditListUrl").val(),
		height:500,
		method: "post",//请求方式
        pagination: true, //启动分页  
        pageSize: 10,  //每页显示的记录数 
        pageList: [5, 10, 15,20,30,50],
        sidePagination: "server", //表示服务端请求
        cache: false, // 不缓存
        //showColumns: true,//列选择按钮
        buttonsAlign: "left",//按钮对齐方式
        toolbarAlign: "right",//工具栏对齐方式
    	idField: "id",  //标识哪个字段为id主键  
    	//showRefresh: true,  //显示刷新按钮
    	exportDataType: "basic",//basic', 'all', 'selected'.
		queryParams : function(params) {
			return params;
		},
		columns : [{
			field : 'userName',
			title : '申请人',
			align : 'center'
		},{
			field : 'typeName',
			title : '请假类型',
			align : 'center',
		},{
			field : 'startDate',
			title : '请假开始时间',
			align : 'center'
		},
		{
			field : 'endDate',
			title : '请假结束时间',
			align : 'center'
		},
		{
			field : 'reason',
			title : '请假原因',
			align : 'center'
		},
		{
			field : 'taskId',
			title : '任务ID',
			align : 'center'
		},
		{
			field : 'taskName',
			title : '任务名称',
			align : 'center'
		},
		{
			field : 'processInstanceId',
			title : '流程实例ID',
			align : 'center'
		},
		{
			field : 'createTime',
			title : '任务创建时间',
			align : 'center'
		},
		{
			field : 'taskId',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				return "<button onclick=\"javascript:deptLeaderAuditHandle(" + value + ")\" type=\"button\" class=\"btn btn-success\" id=\"editButton\">处理</button>";
			}
		}],
		
		//表格加载数据自定义提示信息
		formatLoadingMessage: function () {  
		    return "数据正在努力加载中...";  
		},
		
		//没有匹配的结果自定义提示信息
		formatNoMatches: function () {  //没有匹配的结果  
		    return "无符合条件的记录";  
		},
		
		//加载出现错误
		onLoadError: function (data) {  
		    //$('#deptleaderAuditListTable').bootstrapTable('removeAll');  
		}
	});
});


function deptLeaderAuditHandle(value){
    openWindows("请假流程审批",$("#toDeptLeaderAuditHandleUrl").val(),{taskId : value},
    function(index,layero){
    	deptLeaderAuditDetailForm(index)
    });
}
