<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<script type="text/javascript">
//时间设置
function currentTime(){ 
  var d=new Date(),str=''; 
  str+=d.getFullYear()+'年'; 
  str+=d.getMonth() + 1+'月'; 
  str+=d.getDate()+'日'; 
  str+=d.getHours()+'时'; 
  str+=d.getMinutes()+'分'; 
  str+= d.getSeconds()+'秒'; 
  return str; 
} 
setInterval(function(){$('#time').html(currentTime)},1000); 
</script>
<div class="navbar navbar-inverse" style="background-color: white;border-color:white;margin-top:-1px;margin-left: -1px;margin-right: -1px;">
  <div class="navbar-inner">
   <div class="container-fluid" style="line-height: 40px;">
	  <a style="margin-left: 20px;" class="brand" href="${ctx}/index"><small><i class="icon-leaf"></i> 愚昧者怨天尤人，无能者长吁短叹，儒弱者颓然放弃。</small> </a>
	  <ul class="nav ace-nav pull-right">
		     <li class="light-blue" style="width: 280px;float: left;">
		        <a class="user-menu dropdown-toggle" style="cursor: text;">系统时间：
                    <span class="time"><em id="time" style="font-style: normal;"></em></span>
                </a>
		     </li>
			<li class="light-blue user-profile" style="width: 163px;float: left;">
				<a class="user-menu dropdown-toggle" data-toggle="dropdown">
					<img alt="FH" src="<c:url value="/resources/images/user.jpg"/>" class="nav-user-photo" />
					<span id="user_info">
						<span id="user_info"><small>Welcome</small> <shiro:principal/></span>
					</span>
					<i class="icon-caret-down"></i>
				</a>
				<ul id="user_menu" class="pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
					<!-- <li><a onclick="editUserH();" style="cursor:pointer;"><i class="icon-user"></i> 修改资料</a></li>
					<li id="systemset"><a onclick="editSys();" style="cursor:pointer;"><i class="icon-cog"></i> 系统设置</a></li>
					<li id="productCode"><a onclick="productCode();" style="cursor:pointer;"><i class="icon-cogs"></i> 代码生成</a></li>
					<li class="divider"></li> -->
					<li><a href="<c:url value="/login/logout"/>"><i class="icon-off"></i> 退出</a></li>
				</ul>
			</li>
	  </ul>
   </div>
  </div>
</div>


<!--引入属于此页面的js -->
<script type="text/javascript" src="<c:url value="/resources/js/index/head.js"/>"></script>
