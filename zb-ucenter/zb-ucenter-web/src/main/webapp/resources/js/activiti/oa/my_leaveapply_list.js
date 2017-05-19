$(function() {
	$("#myLeaveApplyListTable").bootstrapTable({
		contentType: "application/x-www-form-urlencoded",
		url : $("#getMyLeaveApplyListUrl").val(),
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
			field : 'executionId',
			title : '执行ID',
			align : 'center'
		},{
			field : 'processInstanceId',
			title : '流程实例ID',
			align : 'center',
		},{
			field : 'name',
			title : '当前节点',
			align : 'center'
		},
		{
			field : 'businessKey',
			title : '业务key',
			align : 'center'
		},
		{
			field : 'processInstanceId',
			title : '查看详情',
			align : 'center',
			formatter : function(value, row, index) {
				return "<a target=\"_blank\" href=\"" + $("#traceProcessUrl").val() + "/" + value + "\">查看详情</a>";
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
		    //$('#processListTable').bootstrapTable('removeAll');  
		}
	});
});

