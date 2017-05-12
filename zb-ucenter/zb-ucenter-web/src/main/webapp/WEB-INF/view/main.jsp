<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE html>
<head>
    <meta name="description" content="overview & stats" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <%@ include file="/WEB-INF/view/common/common.jsp"%>
    <link href="${ctx}/resources/css/style.css" rel="stylesheet">
    
    <script type="text/javascript">
        $(document).ready(function() {

            $(".t_Record").width($(window).width() - 320);
            //当文档窗口发生改变时 触发  
            $(window).resize(function() {
                $(".t_Record").width($(window).width() - 320);
            });
            
            var projectDebuggingTip = $("#projectDebuggingTip").val();
            if(projectDebuggingTip != "" && projectDebuggingTip != undefined && projectDebuggingTip == 1){
            	//alert("网站开发者此时正在调试、更新当前网站，您在操作的时候可能会出现一些小问题^_^");
            	$("#projectDebuggingTipModal").modal("toggle")
            }
        });
    </script>
</head>
<body>
	<div class="page-content clearfix">
		<div class="alert alert-block alert-success" style="margin-left: 10px;">
			<button type="button" class="close" data-dismiss="alert">
				<i class="icon-remove"></i>
			</button>
			<i class="icon-ok green"></i>欢迎访问<strong class="orange">&nbsp;JAVA技术后台管理系统(v3.0)</strong>，你本次登陆时间为<strong class="orange">&nbsp;${curTime}</strong>，登陆IP：<strong class="orange">&nbsp;${clientIp}</strong>.
		</div>
		
		<!-- 
		<div class="state-overview clearfix">
			<div class="col-lg-3 col-sm-6">
				<section class="panel">
					<a target="_blank" href="http://git.oschina.net/zhoubang85/" title="http://git.oschina.net/zhoubang85/">
						<div class="symbol terques">
							<i class="icon-github-sign"></i>
						</div>
						<div class="value">
							<h3 style="margin-left: -30px">我的开源中国</h3>
							<p style="margin-left: -40px;margin-top: 10px;">http://git.oschina.net/zhoubang85/</p>
						</div>
					</a>
				</section>
			</div>
			<div class="col-lg-3 col-sm-6">
                <section class="panel">
                    <a target="_blank" href="http://git.oschina.net/zhoubang85/zb-server/" title="http://git.oschina.net/zhoubang85/zb-server/">
                        <div class="symbol red">
                            <i class=" icon-globe"></i>
                        </div>
                        <div class="value">
                            <h3 style="margin-left: -30px">项目永久域名</h3>
                            <p style="margin-left: -40px;margin-top: 10px;">www.2b2b92b.com</p>
                        </div>
                    </a>
                </section>
            </div>
            <div class="col-lg-3 col-sm-6">
                <section class="panel">
                    <a target="_blank" href="http://git.oschina.net/zhoubang85/zb-server/" title="http://git.oschina.net/zhoubang85/zb-server/">
                        <div class="symbol yellow">
                            <i class="icon-twitter"></i>
                        </div>
                        <div class="value">
                            <h3 style="margin-left: -30px">项目免费开源</h3>
                            <p style="margin-left: -40px;margin-top: 10px;">提倡开源、免费分享</p>
                        </div>
                    </a>
                </section>
            </div>
            <div class="col-lg-3 col-sm-6">
                <section class="panel">
                    <a target="_blank" href="http://git.oschina.net/zhoubang85/zb-server/" title="http://git.oschina.net/zhoubang85/zb-server/">
                        <div class="symbol darkblue">
                            <i class="icon-thumbs-up"></i>
                        </div>
                        <div class="value">
                            <h2 style="margin-left: -30px">${sourceCount}</h2>
                            <p style="margin-left: -40px;margin-top: 10px;">项目源码、技术资源免费分享数量累计</p>
                        </div>
                    </a>
                </section>
            </div>
		</div>
		 -->
	</div>


    <!-- 项目处于管理员调试、修改状态，弹出提示框 -->
	<div class="modal fade" id="projectDebuggingTipModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">友情提示</h4>
				</div>
				<div class="modal-body">网站开发者此时正在调试当前网站，浏览的时候如果出现数据不显示，不要担心哈^_^</div>
				<div class="modal-footer">
					<!-- <button type="button" class="btn btn-default" data-dismiss="modal">我知道了</button> -->
					<button type="button" data-dismiss="modal" class="btn btn-primary">我知道了</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 标识项目是否处于管理员调试、修改状态，弹出提示框 -->
	<input type="hidden" id="projectDebuggingTip" value="${projectDebuggingTip}"/>
</body>
</html>
