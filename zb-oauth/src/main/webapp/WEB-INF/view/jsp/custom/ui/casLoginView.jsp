<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/view/jsp/custom/ui/includes/taglibs.jsp"%>
<title>CAS单点登录认证中心</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/loginnew/resources/static/h-ui/css/H-ui.min.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/loginnew/resources/Hui-iconfont/1.0.8/iconfont.min.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/loginnew/resources/static/h-ui.admin/skin/default/skin.css" />" id="skin" />

<script type="text/javascript" src="<c:url value="/loginnew/js/jquery-1.8.0.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/loginnew/resources/static/h-ui/js/H-ui.min.js" />"></script>

<link rel="icon" type="image/x-icon" href="<c:url value="/loginnew/img/v.ico" />" /> 
<!-- 页面顶部¨ -->
<%@ include file="/WEB-INF/view/jsp/custom/ui/includes/head.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/loginnew/css/style2.0.css" />">
<style type="text/css">
    body{
        background-image: url("<c:url value='/loginnew/img/bg.jpg' />");
        background-size:cover;
        background-color: #1F2326;
        background-repeat: repeat;
    }
    .tyg-div ul li{font-size: 25px;color:#2ec0f6;}
    .tyg-div{z-index:-1000;float:left;position:absolute;left:5%;top:20%;}
    .tyg-p{
        font-size: 14px;
        font-family: 'microsoft yahei';
        position: absolute;
        top: 135px;
        left: 60px;
    }
    .tyg-div-denglv{
        z-index:1000;float:right;position:fixed;top: 25%;right: 17%;
    }
    .tyg-div-form{
        background-color: #23305a;
        width:420px;
        height:auto;
        color:#2ec0f6;
    }
    .tyg-div-form form {padding:10px;}
    .tyg-div-form form input{
        width: 270px;
        height: 35px;
        margin: 25px 10px 0px 0px;
        border-radius:5px;
        font-size: 13px;
    }
    .tyg-div-form form button {
        cursor: pointer;
        width: 100%;
        height: 44px;
        margin-top: 25px;
        padding: 0;
        background: #009FE3;
        -moz-border-radius: 3px;
        -webkit-border-radius: 3px;
        border-radius: 3px;
        border: 1px solid #009FE3;
        -moz-box-shadow:
            0 15px 30px 0 rgba(255,255,255,.25) inset,
            0 2px 7px 0 rgba(0,0,0,.2);
        -webkit-box-shadow:
            0 15px 30px 0 rgba(255,255,255,.25) inset,
            0 2px 7px 0 rgba(0,0,0,.2);
        box-shadow:
            0 15px 30px 0 rgba(255,255,255,.25) inset,
            0 2px 7px 0 rgba(0,0,0,.2);
        font-family: 'PT Sans', Helvetica, Arial, sans-serif;
        font-size: 14px;
        font-weight: 700;
        color: #fff;
        text-shadow: 0 1px 2px rgba(0,0,0,.1);
        -o-transition: all .2s;
        -moz-transition: all .2s;
        -webkit-transition: all .2s;
        -ms-transition: all .2s;
}


#fm1 .row input[type=text],#fm1 .row input[type=password] {
    -webkit-border-radius: 3px; 
    -moz-border-radius: 3px; 
    border-radius: 3px; 
    border: 1px solid #DDDDDD; 
    background: #FFFFDD; 
    width: 370px;
    padding: 10px 5px 10px 40px;
    border: 1px solid #009fe3;
    height: 38px;
    line-height: 38px;
    margin-bottom: 5px; 
    color: #666;
    margin-left: 15px;
}
#fm1 .row input#captcha{padding: 10px 5px;width: 100px;}
#fm1 .row{margin-left: 0px;}
#fm1 .row img {vertical-align: middle;margin-top:20px;}
#fm1 .row span {position: absolute; width: 27px; height: 28px; z-index: 1;margin: 20px;}
#fm1 .pwd span {background: url(<c:url value="/images/login_pwd.png" />) no-repeat;}
#fm1 .id span {background: url(<c:url value="/images/login_id.png" />) no-repeat;}
</style>
	<c:if test="${not pageContext.request.secure}">
	  <div id="msg" class="errors">
	    <h2><spring:message code="screen.welcome.non_secure.head" /></h2>
	    <p><spring:message code="screen.welcome.non_secure.content" /></p>
	  </div>
	</c:if>
    <h2><spring:message code="screen.welcome.instructions" /></h2>
    <!-- 
    <div class="tyg-div" style="margin-left: 100px;">
	    <ul>
	        <li><div style="margin-left:20px;color: white;font-size: 20px;">我</div></li>
	        <li><div style="margin-left:20px;color: white;font-size: 20px;">一</div></li>
	        <li><div style="margin-left:20px;color: white;font-size: 20px;">直</div></li>
	        <li><div style="margin-left:20px;color: white;font-size: 20px;">在</div></li>
	        <li><div style="margin-left:20px;color: white;font-size: 20px;">思</div></li>
	        <li><div style="margin-left:20px;color: white;font-size: 20px;">考</div></li>
	        <!-- <li><div style="margin-left:20px;color: white;font-size: 20px;">并</div></li>
	        <li><div style="margin-left:20px;color: white;font-size: 20px;">努</div></li>
	        <li><div style="margin-left:20px;color: white;font-size: 20px;">力</div></li>
	        <li><div style="margin-left:20px;color: white;font-size: 20px;">整</div></li>
	        <li><div style="margin-left:20px;color: white;font-size: 20px;">着</div></li>
	    </ul>
	    <ul style="margin-left:100px;margin-top: -100px;">
            <li><div style="margin-left:20px;color: white;font-size: 20px;">哪</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">些</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">技</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">术</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">是</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">别</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">人</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">所</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">期</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">盼</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">学</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">习</div></li>
            <li><div style="margin-left:20px;color: white;font-size: 20px;">的</div></li>
        </ul>
	</div> 
	 -->
	<div id="contPar" class="contPar">
	    <div id="page1"  style="z-index:1;">
	        <div style="color: white;font-size: 40px;height: 70px;line-height: 70px;text-align: left;margin-left: 15%;">单点登录认证中心（CAS）</div>
	        <div style="font-size: 20px;color: #fff;text-align: left;margin-left: 30%;padding-top: 20px;">统一身份授权、高效会话管理</div>
	        <div class="imgGroug" style="margin-left: -470px;">
	            <ul>
	                <img alt="" class="img0 png" src="<c:url value="/loginnew/img/page1_0.png" />">
	                <img alt="" class="img1 png" src="<c:url value="/loginnew/img/page1_1.png" />">
	                <img alt="" class="img2 png" src="<c:url value="/loginnew/img/page1_2.png" />">
	            </ul>
	        </div>
	        
	        <%-- <img alt="" class="img3 png" style="margin-top: -250px;margin-left: -750px;" src="<c:url value="/loginnew/img/page1_3.jpg" />"> --%>
	    </div>
	</div>
	<div class="tyg-div-denglv">
	    <div class="tyg-div-form" style="background-color: white;">
	        <form:form method="post" id="fm1" name="fm1" commandName="${commandName}" htmlEscape="true">
	            <form:errors path="*" id="msg" style="color:red;text-align: center;font-size: 14px;font-weight:bold;float:right;padding-right:10%;padding-top:4px;" cssClass="errors" element="div" htmlEscape="false" />
	            <span style="float: left;font-size: 18px;color: #494F52;padding-left: 15px;">账户密码登录</span>
	            
	            <section class="row id">
	               <span style="margin-top: 60px;"></span>
	                <input type="text" placeholder="请输入登录账户" name="username" id="username" tabindex="1" placeholder="${userNameHolder }" accesskey="${userNameAccessKey}" path="username" autocomplete="off" htmlEscape="true" />
	            </section>
	            <section class="row pwd">
                    <span style="margin-top: 30px;"></span>
	                <input type="password" placeholder="请输入登录密码" name="password" id="password" tabindex="2" path="password" placeholder="${passwordHolder }" accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off"/>
	            </section>
	            <section class="row cap">
	                <input type="text" class="icon-user" name="captcha" style="width:50%;padding-left: 10px;" tabindex="3" placeholder="请输入验证码"/>
	                <img src="captcha.jpg" style="vertical-align:middle;height:38px;line-height: 38px;cursor: pointer;width: 32%;margin-left: 15px;border-radius:3px;" title="点击更换验证码" onclick="this.src='captcha.jpg?'+Math.random();"/>
	            </section>
	            <input id="rememberMe" name="rememberMe" value="false" type="checkbox" style="display: none;"/>
	            <button style="margin-bottom: 5px;margin-left:15px;width: 370px;" name="submit" type="submit" onclick="submitForm()" >登&nbsp;&nbsp;&nbsp;录</button>
	            
	            <input type="hidden" name="lt" value="${loginTicket}" />
                <input type="hidden" name="execution" value="${flowExecutionKey}" />
                <input type="hidden" name="_eventId" value="submit" />
	        </form:form>
	        
		    <ul style="color: green;padding-bottom: 10px;font-size: 14px;text-align: center;font-weight: bold;">
                <li>测试账号 admin &nbsp;&nbsp; 密码123456</li>
		    </ul>
	    </div>
	</div>
	
	<footer>
        <div id="copyright" style="color: white;text-align:center;margin-top: 600px;color: #9B9EA0;">
	        <p class="big" style="display: inline-block">
	          <span>项目源码---开源中国 <a style="color:#9B9EA0;" href="http://git.oschina.net/zhoubang85/zb">http://git.oschina.net/zhoubang85/zb</a> &nbsp;&nbsp;|&nbsp;&nbsp;  QQ支付技术交流群 470414533 &nbsp;&nbsp;|&nbsp;&nbsp; 联系邮箱 842324724@qq.com   </span>
	        </p>
	        <p class="copyright">
	          © 2017 www.2b2b92b.com 版权所有 ICP证：苏ICP备15040554号-2
	        </p>
	    </div>
    </footer>
<script type="text/javascript" src="<c:url value="/loginnew/js/com.js" />"></script>
<script type="text/javascript">
    function submitForm(){
    	document.forms['fm1'].submit();
    }
</script>
