<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/view/common/common.jsp"%>

<!DOCTYPE>
<html>
<head>
<title>WebSocket/SockJS 服务器推送</title>
    <style type="text/css">
        #connect-container {
            float: left;
            width: 35%;
        }
        
        #connect-container textarea{
            margin-left: 10px;
            padding: 5px;
        }
        
        #connect-container button {
            margin-left: 10px;
            margin-top: 10px;
        }
        
        #connect-container div {
            padding: 5px;
            padding-top:0px;
            margin-left: 10px;
        }

        #console-container {
            float: left;
            width: 60%;
        }

        #console {
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            height: 80%;
            overflow-y: scroll;
        }

        #console p {
            padding: 0;
            margin: 0;
        }
    </style>

    <script src="${ctx}/resources/lib/sockjs/0.3.4/sockjs-0.3.min.js"></script>

    <script type="text/javascript">
        var ws = null;

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('echo').disabled = !connected;
            if(connected){
            	$("#connect").removeClass("btn-success").addClass("btn-default");
                $("#disconnect").removeClass("btn-default").addClass("btn-danger");
                $("#echo").removeClass("btn-default").addClass("btn-primary");
            }else{
            	$("#connect").removeClass("btn-default").addClass("btn-success");
                $("#disconnect").removeClass("btn-danger").addClass("btn-default");
                $("#echo").removeClass("btn-primary").addClass("btn-default");
            }
        }

        function connect() {
        	log(1,'等待服务器连接...');
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
            };
            
            //消息监听
            ws.onmessage = function (event) {
                log(2,event.data);
            };
            ws.onerror = function (evnt) {
            };
            ws.onclose = function (event) {
                setConnected(false);
                log(1,'连接已关闭.');
                $("#connect").removeClass("btn-default").addClass("btn-success");
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
                log(3,message);
                ws.send(message);
            } else {
                alert('connection not established, please connect.');
            }
        }

        function log(type,message) {
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            
            //对不同类型消息进行颜色处理
            if(type == 1){//系统提示
            	var sp = document.createElement('span');
            	sp.append("[系统提示]: ");
            	sp.style.color="red";
            	sp.style.marginLeft="5px";
            	p.appendChild(sp);
            }else if(type == 2){
            	var sp = document.createElement('span');
                sp.append("[收到消息]: ");
                sp.style.color="orange";
                sp.style.marginLeft="5px";
                p.appendChild(sp);
            }else{
            	var sp = document.createElement('span');
                sp.append("[发送消息]: ");
                sp.style.color="green";
                sp.style.marginLeft="5px";
                p.appendChild(sp);
            }
            p.append(message);
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
            <textarea id="message" style="width: 90%;height: 300px;">hello，将我发送出去看看效果吧^_^。</textarea>
        </div>
        <div>
            <button id="connect" onclick="connect();" class="btn btn-success radius">连接服务</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();" class="btn btn-default radius">断开连接</button>
            <button id="echo" onclick="echo();" disabled="disabled" class="btn btn-default radius">发送消息</button>
        </div>
    </div>
    <div id="console-container">
        <div id="console"></div>
    </div>
</div>
</body>

</html>