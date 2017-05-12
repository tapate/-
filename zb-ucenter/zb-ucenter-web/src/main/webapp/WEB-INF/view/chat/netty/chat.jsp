<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    
    <title>netty在线聊天服务</title>
	
    <!-- 静态css、js资源 -->
    <%@ include file="/WEB-INF/view/common/common.jsp"%>
    
    <link rel="stylesheet" href="${ctx}/resources/css/chat/style.css" />
    <script type="text/javascript" src="${ctx}/resources/js/chat/jquery.snowfall.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/chat/chat.util.js"></script>
</head>
<body>
<div class="wrapper wrapper-content animated fadeInRight " id="userListDiv" style="width: 90%; height: 90%;">
    <div class="ibox">
        <div class="ibox-content">
            <div class="row">
                <div class="col-md-10 col-lg-10">
                    <div id="loginbox">
				        <div style="width:400px;margin:200px auto;">欢迎来到小周 Netty WebSocket聊天室！
				            <br/>
				            <br/>
				            <input type="text" style="width:250px;" placeholder="进入聊天室前，请先输入你的名称" id="nickname" name="nickname" />
				            <input type="button" style="width:50px;height: 30px;" value="进入" onclick="CHAT.login();" />
				            <div id="error-msg" style="color:red;"></div>
				        </div>
				    </div>
				    <div id="chatbox" style="display: none;">
				        <div style="background:#3d3d3d;height: 40px; width: 100%;font-size:12px;position: fixed;top: 0px;z-index: 999;">
				            <div style="line-height: 40px;color:#fff;font-size: 13px;color: yellow;font-weight: bold;">
				                <span style="text-align:left;margin-left:10px;">Netty聊天室</span>
				                <span style="float:right; margin-right:10px;">
				                    <span>当前在线<span id="onlinecount">0</span>人</span> |
				                    <span id="shownikcname">匿名</span> |
				                    <a href="javascript:;" onclick="CHAT.logout()" style="color:#fff;">退出</a>
				                </span>
				            </div>
				        </div>
				        <div id="doc" style="border:1px solid #999999;">
				            <div id="chat">
				                <div id="message" class="message">
				                    <div id="onlinemsg" style="background:#EFEFF4; font-size:12px; margin-top:40px; margin-left:10px; color:#666;">
				                    </div>
				                </div>
				                <form onsubmit="return false;">
				                    <div class="tool-box">
				                        <div class="face-box" id="face-box"></div>
				                        <span class="face" onclick="CHAT.openFace()" title="选择表情"></span>
				                        <!--
				                            <span class="img" id="tool-img-btn" title="发送图片"></span>
				                            <span class="file" id="tool-file-btn" title="上传文件"></span>
				                         -->
				                        <span class="flower" onclick="CHAT.sendFlower()" title="送鲜花"></span>
				                    </div>
				                    <div class="input-box">
				                        <div class="input" contenteditable="true" id="send-message"></div>
				                        <div class="action">
				                            <input class="button" type="button" id="mjr_send" onclick="CHAT.sendText()" value="发送"/>
				                        </div>
				                    </div>
				                    <div class="copyright">小周&copy;版权所有</div>
				               </form>
				           </div>
				       </div>
				    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
