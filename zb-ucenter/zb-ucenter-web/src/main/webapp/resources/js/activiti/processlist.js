$(function() {
	var haveUserEditPermission = $("#haveUserEditPermission").val();
	var haveUserDeletePermission = $("#haveUserDeletePermission").val();
	var showFileContentUrl = $("#showProcessResourceUrl").val();
	$("#processListTable").bootstrapTable({
		contentType: "application/x-www-form-urlencoded",
		url : $("#getProcessListUrl").val(),
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
			params['name'] = $("#processSearchForm #name").val();
			return params;
		},
		columns : [{
			field : 'id',
			title : 'ID',
			align : 'center'
		},{
			field : 'deploymentId',
			title : 'DeploymentId',
			align : 'center',
		},{
			field : 'name',
			title : 'name',
			align : 'center'
		},
		{
			field : 'resourceName',
			title : 'ResourceName',
			align : 'center',
			formatter : function(value, row, index) {
				return "<a title=\"点击可查看文件内容\" target=\"_blank\" href=\"" + showFileContentUrl + "?processDeploymentId=" + row["id"] + "&resource=" + value + "\">" + value + "</a>";
			}
		},
		{
			field : 'diagramResourceName',
			title : 'diagramResourceName',
			align : 'center',
			formatter : function(value, row, index) {
				return "<a title=\"点击可查看文件内容\" target=\"_blank\" href=\"" + showFileContentUrl + "?processDeploymentId=" + row["id"] + "&resource=" + value + "\">" + value + "</a>";
			}
		},
		{
			field : 'key',
			title : 'key',
			align : 'center'
		},
		{
			field : 'deploymentId',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				return "<button onclick=\"javascript:deleteProcess(" + value + ")\" type=\"button\" class=\"btn btn-warning\" id=\"deleteButton\">删除</button>";
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
		},
		
		//单击某一行
		//onClickRow: function (row) {  
		    //alert("选中了人员姓名是【" + row["realName"] + "】的一条记录");
		//},
	});

	// 查询
	$("#processSearchButton").click(function() {
		$('#processListTable').bootstrapTable("refresh");
	});
	
	
	
	$("#upload").submit(function(){
	    if($("#fileupload").val()==""){
	    	alert("请选择工作流文件上传");
	    	return false;
	    }
	});
});


function deleteProcess(value) {
	$.confirm({
	    icon: 'fa fa-warning',
	    content: '是否永久删除该工作流实例？',
	    confirm: function(){
	    	$.ajax({
				type : "post",
				url : $("#deleteProcessUrl").val(),
				dataType : "json",
				data : {
					"deploymentId" : value
				},
				success : function(result) {
					if (result.code == 200) {
						layer.msg('删除成功', {
							shift : 2,
							icon : 6
						});
						$('#processListTable').bootstrapTable("refresh");
					} else {
						layer.msg(result.msg, {
							shift : 2,
							icon : 5
						});
					}
				}
			});
	    },
	    cancel: function(){
	    }
	});
}