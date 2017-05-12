
/**
 * 获取我的请假历史列表
 */
$(function() {
	$("#myLeaveApplyHistoryListTable").bootstrapTable({
		contentType: "application/x-www-form-urlencoded",
		url : $("#getMyLeaveApplyHistoryListUrl").val(),
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
			field : 'businessKey',
			title : '业务key',
			align : 'center'
		},{
			field : 'processInstanceId',
			title : '流程实例ID',
			align : 'center',
		},{
			field : 'userName',
			title : '申请人',
			align : 'center'
		},
		{
			field : 'typeName',
			title : '请假类型',
			align : 'center'
		},
		{
			field : 'createTime',
			title : '申请时间',
			align : 'center'
		},
		{
			field : 'processInstanceId',
			title : '查看处理记录',
			align : 'center',
			formatter : function(value, row, index) {
				return "<button onclick=\"javascript:showHandleRecord(" + value + ")\" type=\"button\" class=\"btn btn-success\" id=\"editButton\">查看</button>";
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

/**
 * 查看处理记录
 */
function showHandleRecord(value){
	$("#leaveApplyHandleRecordThead").html("");//清空内容
	
	$("#leaveApplyHandleRecordThead").html("<tr><th>活动名称</th><th>活动类型</th><th>办理人</th><th>活动开始时间</th><th>活动结束时间</th></tr>");//加入表头
	
	//加载当前流程实例的处理记录
	$.ajax({
        type : "post",
        dataType : "json",
        url : $("#getLeaveApplyHandleRecordUrl").val(),
        data : {
        	processInstanceId : value 
        }, 
        beforeSend : function(){
        	ZENG.msgbox.show("正在加载处理记录...", 6);
        },
        success : function (data) {
            if (data.code == 200) {
            	data = data.result;
            	
            	//表格样式，不然被全局样式覆盖了
            	var tdStyle = "background-color:white;border-style: solid;border-color: rgb(221, 221, 221);border-image: initial;border-width: 0px 1px;border-bottom-width:1px;";
            	
            	for(var i = 0; i < data.length; i++){
            		$("#leaveApplyHandleRecordThead").append("<tr><td style='" + tdStyle + "'>" 
            				+ data[i].activityName + "</td><td style='" + tdStyle + "'>" 
            				+ data[i].activityType + "</td><td style='" + tdStyle + "'>" 
            				+ data[i].assignee + "</td><td style='" + tdStyle + "'>" 
            				+ data[i].startTime + "</td><td style='" + tdStyle + "'>" 
            				+ data[i].endTime + "</td></tr>");
            	}
            	
            	ZENG.msgbox._hide();
            }else{
            	ZENG.msgbox.show(data.msg, 4, 2000);
            }
        },
        
        //textStatus的值：null, timeout, error, abort, parsererror  
        //errorThrown的值：收到http出错文本，如 Not Found 或 Internal Server Error.
        error: function (XMLHttpRequest, textStatus, errorThrown) {  
        	ZENG.msgbox.show("数据加载失败，稍候重试", 5, 2000);
        }
    });
	//弹出model提示框
	$("#leaveApplyHandleRecordModal").modal("toggle");
}

