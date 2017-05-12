<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath %>"/>
<!DOCTYPE>
<html>
<head>
<title>WebSocket/SockJS 服务器推送</title>
    <style type="text/css">
        #connect-container {
            float: left;
            width: 400px
        }

        #connect-container div {
            padding: 5px;
        }

        #console-container {
            float: left;
            margin-left: 15px;
            width: 400px;
        }

        #console {
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            height: 600px;
            overflow-y: scroll;
            padding: 5px;
            width: 1200px;
        }

        #console p {
            padding: 0;
            margin: 0;
        }
    </style>

    <script src="${ctx}/resources/js/sockjs-0.3.min.js"></script>

    <script type="text/javascript">
        var ws = null;

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('echo').disabled = !connected;
        }

        function connect() {
        	
            if ('WebSocket' in window) {
                ws= new WebSocket("ws://${serviceUrl}/websck?loginUserName=admin");
                console.log("ws://${serviceUrl}/websck");
            }else if ('MozWebSocket' in window) {
                ws = new MozWebSocket("ws://${serviceUrl}/websck?loginUserName=admin");
            }else {
                ws = new SockJS("http://${serviceUrl}/sockjs/websck?loginUserName=admin");
                console.log("http://${serviceUrl}/sockjs/websck");
            }
            ws.onopen = function () {
                setConnected(true);
                log('[系统提示]: 连接已打开.');
            };
            ws.onmessage = function (event) {
                log('[接收到消息]: ' + event.data);
            };
            ws.onerror = function (evnt) {
            };
            ws.onclose = function (event) {
                setConnected(false);
                log('[系统提示]: 连接已关闭.');
            };
        }

        function disconnect() {
            if (ws != null) {
                ws.close();
                ws = null;
            }
            setConnected(false);
        }

        function echo() {
            if (ws != null) {
                var message = document.getElementById('message').value;
                log('[发送消息]: ' + message);
                ws.send(message);
            } else {
                alert('connection not established, please connect.');
            }
        }

        function log(message) {
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            console.appendChild(p);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
        }
    </script>
</head>
<body>
<div>
<br>
    <div id="connect-container">
        <div>
            <button id="connect" onclick="connect();">连接</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
        </div>
        <div>
            <textarea id="message" style="width: 350px">你好这是一条消息!</textarea>
        </div>
        <div>
            <button id="echo" onclick="echo();" disabled="disabled">发送消息</button>
        </div>
    </div>
    <div id="console-container">
        <div id="console"></div>
    </div>
</div>
</body>

</html>