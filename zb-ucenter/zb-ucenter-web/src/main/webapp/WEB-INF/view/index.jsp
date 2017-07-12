<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	
	<title>用户管理中心系统</title>
	<meta name="keywords" content="java后台管理系统,hui,web">
	<meta name="description" content="个人开发的java后台管理系统——用户管理中心。分布式服务的某个服务模块。更多项目介绍请浏览：http://git.oschina.net/zhoubang85/zb ">
	
	<%@ include file="/WEB-INF/view/common/common.jsp"%>
	
	<script src="http://code.jquery.com/jquery-migrate-1.1.1.js"></script>
    <script type="text/javascript" src="${ctx}/resources/lib/custom/rb-pop.js"></script>
    <script src="${ctx}/resources/lib/sockjs/0.3.4/sockjs-0.3.min.js"></script>
    
    <style type="text/css">
        *{margin:0;padding:0;}
        #pop{background:#fff;width:260px;border:1px solid #C1C1C1;font-size:12px;position: fixed;right:10px;bottom:10px;z-index: 999999999;}
        #popHead{line-height:15px;background:#f6f0f3;border-bottom:1px solid #e0e0e0;position:relative;font-size:12px;padding:0 0 0 10px;}
        #popHead h5{font-size:12px;color:#666;line-height:15px;height:15px;}
        #popHead #popClose{position:absolute;right:10px;top:1px;}
        #popHead a#popClose:hover{color:#f00;cursor:pointer;}
        #popContent{padding:5px 10px;}
        #popTitle a{line-height:24px;font-size:14px;font-family:'微软雅黑';color:#333;font-weight:bold;text-decoration:none;}
        #popTitle a:hover{color:#f60;}
        #popIntro{text-indent:20px;line-height:160%;margin:5px 0;color:#666;}
        #popMore{text-align:right;border-top:1px dotted #ccc;line-height:24px;margin:8px 0 0 0;}
        #popMore a{color:#f60;}
        #popMore a:hover{color:#f00;}
    </style>
    
    <script type="text/javascript">
        var ws = null;
        function connect() {
        	var userName = $("#userName").val();
            if ('WebSocket' in window) {
                ws= new WebSocket("ws://" + $("#serviceUrl").val() + "/websck?loginUserName=" + userName);
                //console.log("ws://" + $("#serviceUrl").val() + "/websck?loginUserName=" + userName);
            }else if ('MozWebSocket' in window) {
                ws = new MozWebSocket("ws://" + $("#serviceUrl").val() + "/websck?loginUserName=" + userName);
            }else {
                ws = new SockJS("http://" + $("#serviceUrl").val() + "/sockjs/websck?loginUserName=" + userName);
                //console.log("http://" + $("#serviceUrl").val() + "/sockjs/websck?loginUserName=" + userName);
            }
            ws.onopen = function () {
            	//console.log("消息推送服务已连接");
            };
            
            //消息监听
            ws.onmessage = function (event) {
                //console.log("收到了一条推送消息：" + event.data);
                var pop=new Pop("收到一条推送消息", "",event.data);
            };
            ws.onerror = function (evnt) {
            };
            ws.onclose = function (event) {
            	//console.log("socket服务连接已关闭");
            	var pop=new Pop("消息推送服务连接已关闭", "",event.data);
            };
        }
        
        $(document).ready(function(){
        	connect();//连接消息推送服务
        });
    </script>
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
	
	
	<!--右下角pop弹窗 end-->
    <div id="pop" style="display:none;">
        <div id="popHead">
           <a id="popClose" title="关闭">关闭</a>
           <h5>温馨提示</h5>
        </div>
        <div id="popContent">
            <dl>
                <dt id="popTitle"><a href="javascript:void(0);" target="_blank"></a></dt>
                <dd id="popIntro">欢迎您！</dd>
            </dl>
            <p id="popMore"><a href="javascript:void(0);" target="_blank">查看 »</a></p>
        </div>
    </div>
	 
	<script type="text/javascript" src="${ctx}/resources/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
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
	</script>
	
	<input type="hidden" id="serviceUrl" value="${serviceUrl}" des="socket服务器域名地址"/>
	<input type="hidden" id="userName" value="wangwu" des="当前在线用户的用户名"/>
</body>
</html>
