﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/common.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="Bookmark" href="${ctx}/resources/images/favicon.ico" >
	<link rel="Shortcut Icon" href="${ctx}/resources/images/favicon.ico" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${ctx}/resources/lib/html5shiv.js"></script>
	<script type="text/javascript" src="${ctx}/resources/lib/respond.min.js"></script>
	<![endif]-->
	
	<!--[if IE 6]>
	<script type="text/javascript" src="${ctx}/resources/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<title>用户管理中心系统</title>
	<meta name="keywords" content="java后台管理系统,hui,web">
	<meta name="description" content="个人开发的java后台管理系统——用户管理中心。分布式服务的某个服务模块。更多项目介绍请浏览：http://git.oschina.net/zhoubang85/zb ">
</head>
<body>
    
    <!-- 页面顶部¨ -->
    <%@ include file="head.jsp"%>
    
    <!-- 左侧菜单 -->
    <%@ include file="left.jsp"%>
    
		
    <div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
    <section class="Hui-article-box">
        <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
            <div class="Hui-tabNav-wp">
	            <ul id="min_title_list" class="acrossTab cl">
	                <li class="active">
                        <span title="我的桌面" data-href="welcome.html">我的桌面</span>
                        <em></em>
                    </li>
               </ul>
            </div>
            <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
        </div>
        <div id="iframe_box" class="Hui-article">
            <div class="show_iframe">
                <div style="display:none" class="loading"></div>
                <iframe scrolling="yes" frameborder="0" src="${ctx}/main"></iframe>
           </div>
        </div>
    </section>
    	
    <div class="contextMenu" id="Huiadminmenu">
        <ul>
            <li id="closethis">关闭当前 </li>
            <li id="closeall">关闭全部 </li>
       </ul>
    </div>
	
	
	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript" src="${ctx}/resources/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
	$(function(){
	    /*$("#min_title_list li").contextMenu('Huiadminmenu', {
	        bindings: {
	            'closethis': function(t) {
	                console.log(t);
	                if(t.find("i")){
	                    t.find("i").trigger("click");
	                }       
	            },
	            'closeall': function(t) {
	                alert('Trigger was '+t.id+'\nAction was Email');
	            },
	        }
	    });*/
	});
	/*个人信息*/
	function myselfinfo(){
	    layer.open({
	        type: 1,
	        area: ['300px','200px'],
	        fix: false, //不固定
	        maxmin: true,
	        shade:0.4,
	        title: '查看信息',
	        content: '<div>管理员信息</div>'
	    });
	}
	
	/*资讯-添加*/
	function article_add(title,url){
	    var index = layer.open({
	        type: 2,
	        title: title,
	        content: url
	    });
	    layer.full(index);
	}
	/*图片-添加*/
	function picture_add(title,url){
	    var index = layer.open({
	        type: 2,
	        title: title,
	        content: url
	    });
	    layer.full(index);
	}
	/*产品-添加*/
	function product_add(title,url){
	    var index = layer.open({
	        type: 2,
	        title: title,
	        content: url
	    });
	    layer.full(index);
	}
	/*用户-添加*/
	function member_add(title,url,w,h){
	    layer_show(title,url,w,h);
	}
	</script> 
</body>
</html>
