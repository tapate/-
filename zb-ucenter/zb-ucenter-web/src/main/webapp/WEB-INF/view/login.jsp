<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYpE html>
<html>
<head>
<meta charset="UTF-8">
<title>shiro权限认证登录中心</title>
<meta charset="UTF-8" />

<style type="text/css">
body {
	/*background: #ebebeb;*/
	background: white;
	font-family: "Helvetica Neue", "Hiragino Sans GB", "Microsoft YaHei", "\9ED1\4F53", Arial, sans-serif;
	color: #222;
	font-size: 12px;
}

* {
	padding: 0px;
	margin: 0px;
}

.top_div {
	/*background: #008ead;*/
	background: white;
	width: 100%;
	height: 400px;
}

.ipt {
	border: 1px solid #d3d3d3;
	padding: 10px 10px;
	width: 290px;
	border-radius: 4px;
	padding-left: 35px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}

.ipt:focus {
	border-color: #66afe9;
	outline: 0;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
		rgba(102, 175, 233, .6)
}

.u_logo {
	background: url("<c:url value='/resources/images/login/username-1.jpg'/>") no-repeat;
	padding: 10px 10px;
	position: absolute;
	top: 41px;
	left: 42px;
}

.p_logo {
	background: url("<c:url value='/resources/images/login/password.png'/>") no-repeat;
	padding: 10px 10px;
	position: absolute;
	top: 12px;
	left: 40px;
}

a {
	text-decoration: none;
}

.tou {
	background: url("<c:url value='/resources/images/login/tou.png'/>") no-repeat;
	width: 97px;
	height: 92px;
	position: absolute;
	top: -87px;
	left: 140px;
}

.left_hand {
	background: url("<c:url value='/resources/images/login/left_hand.png'/>") no-repeat;
	width: 32px;
	height: 37px;
	position: absolute;
	top: -38px;
	left: 150px;
}

.right_hand {
	background: url("<c:url value='/resources/images/login/right_hand.png'/>") no-repeat;
	width: 32px;
	height: 37px;
	position: absolute;
	top: -38px;
	right: -64px;
}

.initial_left_hand {
	background: url("<c:url value='/resources/images/login/hand.png'/>") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -12px;
	left: 100px;
}

.initial_right_hand {
	background: url("<c:url value='/resources/images/login/hand.png'/>") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -12px;
	right: -112px;
}

.left_handing {
	background: url("<c:url value='/resources/images/login/left-handing.png'/>") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -24px;
	left: 139px;
}

.right_handinging {
	background: url("<c:url value='/resources/images/login/right_handing.png'/>") no-repeat;
	width: 30px;
	height: 20px;
	position: absolute;
	top: -21px;
	left: 210px;
}

/* input框自动填充内容背景颜色为黄色解决方法，参考：http://www.w3cfuns.com/notes/17783/c7e9fe25bf61e3adfc2a37d3f8e39dc9.html */
input:-webkit-autofill { box-shadow: 0 0 0px 1000px white inset !important;}

.login_qq{display:block;width:100px;height:30px;padding-left:20px; line-height:30px;border-radius:3px;border:1px solid #37b5f9; color:#37b5f9; background:url(<c:url value='/resources/images/login_qq.jpg'/>) no-repeat 28px 8px #fff;margin-left:10px;}
.login_wx{display:block;width:100px;height:30px;padding-left:20px; line-height:30px;border-radius:3px;border:1px solid #00c800; color:#00c800; background:url(<c:url value='/resources/images/login_wx.jpg'/>) no-repeat 28px 8px #fff;margin-left:10px;}
</style>

<link href="<c:url value="/resources/css/jquery-letterfx.min.css"/>" rel="stylesheet" />

<script src="<c:url value="/resources/js/jquery-1.7.2.js"/>" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.tips.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-letterfx.min.js"/>"></script>
<script type="text/javascript">
	//是否开启验证码登录
	var openVerifyCode = false;

	//是否开启ajax登录
	var openAjaxLogin = true;
    
	$(function() {
		//得到焦点
		$("#password").focus(function() {
			$("#left_hand").animate({
				left : "150",
				top : " -38"
			}, {
				step : function() {
					if (parseInt($("#left_hand").css("left")) > 140) {
						$("#left_hand").attr("class", "left_hand");
					}
				}
			}, 2000);
			$("#right_hand").animate({
				right : "-64",
				top : "-38px"
			}, {
				step : function() {
					if (parseInt($("#right_hand").css("right")) > -70) {
						$("#right_hand").attr("class", "right_hand");
					}
				}
			}, 2000);
		});
		//失去焦点
		$("#password").blur(function() {
			$("#left_hand").attr("class", "initial_left_hand");
			$("#right_hand").attr("class", "initial_right_hand");
			
			$("#left_hand").animate({
                left : "100",
                top : " -12"
            }, {
                step : function() {
                    if (parseInt($("#left_hand").css("left")) <= 140) {
                        $("#left_hand").attr("class", "initial_left_hand");
                    }
                }
            }, 2000);
			
			$("#right_hand").animate({
                right : "-112",
                top : "-12"
            }, {
                step : function() {
                    if (parseInt($("#right_hand").css("right")) <= -70) {
                        $("#right_hand").attr("class", "initial_right_hand");
                    }
                }
            }, 2000);
		});
		
		$("#userName").focus();
		
		//字体动画效果
		$(".top_div").letterfx({"fx":"wave","letter_end":"rewind","words":false,"timing":180});
	});
	
	$(document).keyup(function(event) {
        if (event.keyCode == 13) {
            $("#loginLink").trigger("click");
        }
    });
	
	function reset(){
		$("#userName").val("");
		$("#password").val("");
		$("#userName").focus();
	}
	
	//客户端校验
    function check() {
        if ($("#userName").val() == "") {
            $("#userName").tips({
                side : 2,
                msg : '请输入登录账户名',
                bg : '#68B500',
                time : 3
            });

            $("#userName").focus();
            return false;
        } else {
            $("#userName").val(jQuery.trim($('#userName').val()));
        }

        if ($("#password").val() == "") {
            $("#password").tips({
                side : 2,
                msg : '请输入登录密码',
                bg : '#68B500',
                time : 3
            });
            $("#password").focus();
            return false;
        }
        
        $("#loginbox").tips({
            side : 1,
            msg : '正在登录 , 请稍后 ...',
            bg : '#68B500',
            time : 3
        });
        return true;
    }
	
    //服务器校验
    function severCheck(){
        if(check()){
            //使用ajax登录
            if(openAjaxLogin){
                var userName = $("#userName").val();
                var password = $("#password").val();
                
                $.ajax({
                    type: "post",
                    url: "<c:url value='/login/login'/>",
                    data: {"userName" : userName, "password" : password , "openAjaxLogin" : openAjaxLogin},
                    dataType:'json',
                    cache: false,
                    success: function(data){
                        if(200 == data.code){
                            window.location.href="<c:url value='/index'/>";
                        }else if("usererror" == data.msg){
                            $("#userName").tips({
                                side : 1,
                                msg : "用户名或密码有误",
                                bg : '#FF5080',
                                time : 3
                            });
                            
                            $("#userName").focus();
                        }else{
                            $("#userName").tips({
                                side : 1,
                                msg : data.msg,
                                bg : '#FF5080',
                                time : 3
                            });
                            
                            $("#userName").focus();
                        }
                    }
                });
            }
        }
    }
</script>
</head>
<body style="height:100%;">
	<div class="top_div" style="text-align: center;line-height: 250px;color: white;font-family: 楷体;font-size: 35px;">每一份精彩，都是为您</div>
	<div id="loginbox" style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(0,174,255); border-image: none; width: 400px; height: 200px; text-align: center;">
		<div style="width: 165px; height: 96px; position: absolute;">
			<div class="tou"></div>
			<div class="initial_left_hand" id="left_hand"></div>
			<div class="initial_right_hand" id="right_hand"></div>
		</div>

		<p style="padding: 30px 0px 10px; position: relative;">
			<span class="u_logo"></span> <input class="ipt" type="text" placeholder="请输入用户名" name="userName" id="userName" value="admin">
		</p>

		<p style="position: relative;">
		      <span class="p_logo"></span> 
		      <input class="ipt" id="password" name="password" type="password" onfocus="this.type='password'" placeholder="请输入密码" value="123456">
		</p>
		<div style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(218,218,218); border-top-width: 1px; border-top-style: solid;">
			<p style="margin: 0px 35px 20px 45px;">
				<span style="float: left;">
				    <!-- <a style="color: rgb(204, 204, 204);" href="#">忘记密码?</a> -->
				</span>
                <span style="float: right;">
				    <!-- <a style="color: rgb(204, 204, 204); margin-right: 10px;" href="#">注册</a> -->
				    <a  onclick="reset();" style="background: rgb(243,159,31); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(243,159,31); border-image: none; color: rgb(255, 255, 255); font-weight: bold;" href="javascript:void(0);">重置</a>
					<a id="loginLink" onclick="severCheck();" style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;margin-left: 10px;" href="javascript:void(0);">登录</a>
                </span>
			</p>
		</div>
	</div>
	<div style="text-align: center;">
		<!-- <p style="color: #36AC55;font-size: 12px;margin-top: 20px;display: inline-flex;display: -webkit-inline-box;"><a href="#" class="login_qq">QQ登录</a><a href="#" class="login_wx">微信登录</a></p> -->
		<p style="color: #36AC55;font-size: 12px;margin-top: 20px;font-weight: bold;">提供3个登录账户：admin、user、caiwu，密码均为：123456</p>
	</div>
</body>
</body>
</html>