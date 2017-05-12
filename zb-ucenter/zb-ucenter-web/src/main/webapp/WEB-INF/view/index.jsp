<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<!-- jsp文件头和头部 -->
	<meta charset="utf-8" />
	<title>欢迎登录系统</title>
	<meta name="description" content="overview & stats" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<%@ include file="/WEB-INF/view/common/common.jsp"%>
    
</head>
<body  class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden;">

	<!-- 页面顶部¨ -->
	<%@ include file="head.jsp"%>

	<div class="container-fluid" id="main-container">
		<a href="#" id="menu-toggler"><span></span></a>
		<!-- menu toggler -->

		<!-- 左侧菜单 -->
		<%@ include file="left.jsp"%>

		<div id="main-content" class="clearfix" style="margin-top: -5px;">
			<!--右侧部分开始-->
	        <div id="page-wrapper" class="gray-bg dashbard-1">
	            <div class="row content-tabs" style="line-height: 35px;margin-left: -33px;">
	                <button class="roll-nav roll-left J_tabLeft" style="height: 35px;"><i class="icon-backward"></i>
	                </button>
	                <nav class="page-tabs J_menuTabs">
	                    <div class="page-tabs-content">
	                        <a href="javascript:;" class="active J_menuTab" data-id="${ctx}/index">首页</a>
	                    </div>
	                </nav>
	                <button class="roll-nav roll-right J_tabRight"  style="height: 35px;"><i class="icon-forward"></i>
	                </button>
	                <button class="roll-nav roll-right dropdown J_tabClose" style="font-size: 12px;height: 35px;"><span class="dropdown-toggle" data-toggle="dropdown">关闭操作<span class="caret"></span></span>
	                    <ul class="dropdown-menu dropdown-menu-right" style="margin-left: -50px;min-width: 130px;">
	                        <!-- <li class="divider"></li> -->
	                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
	                        </li>
	                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
	                        </li>
	                    </ul>
	                </button>
	                <!-- <a href="logout" class="roll-nav roll-right J_tabExit" onclick="return PageMain.exitWiseXClient()"><i class="fa fa fa-sign-out"></i> 退出</a> -->
	            </div>
	            <div class="row J_mainContent" id="content-main">
	                <iframe id="mainFrame" data-id="${ctx}/index/" class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx}/index/" frameborder="0" ></iframe>
	            </div>
	            <div class="footer">
	                <div class="pull-right">&copy; 2016 <a href="http://www.2b2b92b.com" target="_blank">www.2b2b92b.com</a>
	                </div>
	            </div>
	        </div>
            <!--右侧部分结束-->
		</div>
		<!-- #main-content -->
	</div>
	<!--/.fluid-container#main-container-->
	
	
	<script src="<c:url value="/resources/js/ace-elements.min.js"/>"></script>
    <script src="<c:url value="/resources/js/ace.min.js"/>"></script>
</body>
</html>
