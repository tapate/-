<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    
    <title>已部署的工作流列表</title>
	
    <!-- 静态css、js资源 -->
    <%@ include file="/WEB-INF/view/common/common.jsp"%>
    
    <script type="text/javascript" src="${ctx}/resources/js/activiti/processlist.js"></script>
</head>
<body>
<!-- 删除工作流实例url -->
<input type="hidden" name="deleteUserUrl" id="deleteProcessUrl" value="<c:url value="/activiti/deleteProcess"/>" />
<!-- 获取已部署的工作流列表url -->
<input type="hidden" name="getProcessListUrl" id="getProcessListUrl" value="<c:url value="/activiti/processList"/>" />
<!-- 查看文件内容url -->
<input type="hidden" name="showProcessResourceUrl" id="showProcessResourceUrl" value="<c:url value="/activiti/showProcessResource"/>" />
    
<div class="wrapper wrapper-content animated fadeInRight " id="processListDiv" style="width: 100%; height: 100%;">
    <div class="ibox">
        <div class="ibox-content">
            <!-- 
            <form class="form-horizontal" id="processSearchForm" method="post" onsubmit="return false">
                <div class="ibox-title">
                    <button type="button" class="btn btn-primary" id="processSearchButton">查询</button>
                </div>
                <br />
                <div class="row">
                </div>
            </form>
            -->
             
            <div class="row">
                <div class="col-md-5 col-lg-5">
                    <form id="upload" action="<c:url value="/activiti/uploadWorkFlow"/>" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="fileupload">上传流程定义文件（请上传 .bpmn 类型文件）</label> 
                            <input type="file" name="uploadFile" class="form-control" id="fileupload">
                        </div>
                        <button type="submit" class="btn btn-primary">开始上传</button>
                    </form>
                </div>
            </div>
            <br/>
            <br/>
            <div class="row">
                <!-- 表格 -->
                <div class="col-md-10 col-lg-10">
                    <table class="table table-bordered table-hover" id="processListTable"></table>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
