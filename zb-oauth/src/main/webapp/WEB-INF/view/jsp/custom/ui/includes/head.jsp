<!-- 此页面是自定义的顶部head -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/jsp/custom/ui/includes/taglibs.jsp"%>

<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top" style="background-color: #1D2124;border-bottom: 1px solid rgba(255,255,255,0.3);">
        <div class="container-fluid cl">
            <img alt="" src="<c:url value="/loginnew/img/fly.png"/>" style="vertical-align: middle;float: left;height:35px;line-height: 35px;padding-right:10px;padding-top: 5px;padding-bottom: 5px;">
            <span  style="height: 40px;line-height: 45px;font-size: 14px;vertical-align: middle;" class="navbar-logo f-l mr-10 hidden-xs"> 提倡开源，免费分享</span> 
            <nav class="nav navbar-nav">
                <ul class="cl" style="float: right;">
                    <li class="dropDown dropDown_hover" style="margin-left: 20px;"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe602;</i> 统一登录认证系统 <i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow" style="background-color: #474956;border:solid 0px #f2f2f2;">
                            <li><a style="color: white;border:solid 1px #474956;" href="javascript:;" onclick="article_add('OA办公系统','article-add.html')"><i class="Hui-iconfont">&#xe616;</i> OA办公系统</a></li>
                            <li><a style="color: white;border:solid 1px #474956;" href="javascript:;" onclick="picture_add('资源文件管理系统','picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 资源文件管理系统</a></li>
                            <li><a style="color: white;border:solid 1px #474956;" href="javascript:;" onclick="product_add('用户管理中心系统','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 用户管理中心系统</a></li>
                            <li><a style="color: white;border:solid 1px #474956;" href="javascript:;" onclick="member_add('支付对账系统','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 支付对账系统</a></li>
                            <li><a style="color: white;border:solid 1px #474956;" href="javascript:;" onclick="member_add('文件内容系统','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 文件内容系统</a></li>
                            <li><a style="color: white;border:solid 1px #474956;" href="javascript:;" onclick="member_add('消息服务总线系统','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 消息服务总线系统</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</div>
</header>