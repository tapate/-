<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    
    <title>微信模板消息</title>
	
    <!-- 静态css、js资源 -->
    <%@ include file="/WEB-INF/view/common/common.jsp"%>
    
    <script type="text/javascript" src="${ctx}/resources/js/wechat/template/templateMsgSend.js"></script>
</head>
<body>
<input type="hidden" id="sendTemplateMsgUrl" value="${ctx}/wechat/template/send"/>

<div class="wrapper wrapper-content animated fadeInRight " id="userListDiv" style="width: 100%; height: 100%;">
    <div class="ibox">
         <div class="row">
            <div class="alert alert-block alert-success" style="margin-left: 20px;">
	            <button type="button" class="close" data-dismiss="alert">
	                <i class="icon-remove"></i>
	            </button>
	            <i class="icon-volume-up green"></i><span class="green" style="margin-left: 10px;">为了方便学习，这里模拟了一个订单同步的功能，通过发送MQ消息，实现订单同步成功的微信模板消息通知。</span>
	        </div>
	        <div class="alert alert-block alert-success" style="margin-left: 20px;">
                <button type="button" class="close" data-dismiss="alert">
                    <i class="icon-remove"></i>
                </button>
                <i class="icon-share-alt orange"></i>
                <span class="orange" style="margin-left: 10px;">【业务流程如下】：</span><br/>
                <span style="color: green;font-size: 12px;margin-left: 30px;">&nbsp;&nbsp;1、接收订单数据，调用MQ消息发送者，发送一条订单同步的消息通知</span><br>
	            <span style="color: green;font-size: 12px;margin-left: 30px;">&nbsp;&nbsp;2、MQ消费者接收到订单同步的消息通知之后，解析消息数据，调用微信模板接口，发送微信模板消息通知</span><br>
	            <span style="color: green;font-size: 12px;margin-left: 30px;">&nbsp;&nbsp;3、将订单入库</span><br><br>
	            <span style="color: green;font-size: 12px;margin-left: 30px;">【说明】：目前只是发送消息通知，暂未保存订单数据到数据库中。</span><br><br>
            </div>
            <div class="alert alert-block alert-danger" style="margin-left: 20px;">
	            <button type="button" class="close" data-dismiss="alert">
	                <i class="icon-remove"></i>
	            </button>
	            <i class="icon-warning-sign"></i><strong style="margin-left: 10px;">发送模板消息之前，请先按照步骤操作：</strong>
	        </div>
         </div>
        <div class="ibox-content">
            <div style="margin-top:20px;margin-right:30px;">
                <ul id="myTab" class="nav nav-tabs">
                   <li class="active"><a href="#subscribe" id="subscribeCard" data-toggle="tab"><i class="icon-double-angle-right"></i>&nbsp;&nbsp;第一步：关注公众号</a></li>
                   <li><a href="#initOrder" id="initOrderDataCard"  data-toggle="tab"><i class="icon-double-angle-right"></i>&nbsp;&nbsp;第二步：输入openid、模拟一条测试订单数据</a></li>
                   <li><a href="#dataBaseOrderList" id="dataBaseOrderListCard"  data-toggle="tab"><i class="icon-double-angle-right"></i>&nbsp;&nbsp;第三步：查看库中订单同步记录</a></li>
                </ul>
                
                <div id="myTabContent" class="tab-content">
                   
                   <!--第一步：关注公众号-->
                   <div class="tab-pane fade in active" id="subscribe">
                        <div class="row">
                            <div class="row">
                                <div class="col-md-12">
                                    <hr class="border-dash">
                                </div>
                            </div>
                            <div class="col-md-10 col-lg-10" style="font-size: 14px;">
			                    <img alt="扫码关注此测试公众号" height="120px" width="120px" src="${ctx}/resources/images/wechat_qrcode.jpg">
			                    <span style="vertical-align: middle;float: left;margin-right: 50px;margin-top: 10px;">
			                    <h4 style="color:green;">测试微信模板消息之前，请先获取您的微信openid。</h4><br/><br/>【操作步骤】：<br/><br/>
			                        1、请先关注页面中的测试微信公众号，请扫码关注（关注后才能获取您的openid）。<br/><br/>
			                        2、关注后，您会收到一条微信消息，里面将会有您的openid。<br/><br/>
			                        3、将openid填入到需要发送的模板消息的接收人输入框中。<br/><br/>
			                        4、点击“发送模板消息”按钮，发送模板消息。<br/><br/>
			                        5、等待接收微信模板消息。<br/><br/>
			                        6、如若正常接收，已经ok。<br/><br/>
			                    </span>
			                    <div class="col-md-1 hidden-sm hidden-xs" style="border-right: 1px dashed #ccc; height: 400px;margin-top: -20;width: 0;padding:0;margin:0 3%;"></div>
			                </div>
			                <div class="row">
			                    <div class="col-md-12">
			                        <hr class="border-dash">
			                    </div>
			                </div>
                        </div>
                   </div>
                   
                    <!--第二步：输入openid、模拟一条测试订单数据-->
                    <div class="tab-pane fade" id="initOrder" style="min-width: 1300px;">
                        <div class="row">
                            <div class="row">
                                <div class="col-md-12">
                                    <hr class="border-dash">
                                </div>
                            </div>
                            <form id="templateForm">
                                <div class="col-md-13 col-lg-13" style="font-size: 14px;min-width: 1300px;">
                                    <span style="float: right;width: 500px;">
                                        <div class="col-md-1 hidden-sm hidden-xs" style="border-right: 1px dashed #ccc; height: 500px;margin-top: -20;width: 0;padding:0;margin:0 3%;"></div>
                                        <div class="form-group">
                                            <div class="col-xs-7">
                                                <span style="color: green;font-size: 12px;">说明：点击发送模板消息按钮后，您将会收到一条以下内容的微信模板消息：</span>
                                                <img alt="" src="${ctx}/resources/images/templateMsgImg.jpg"  style="margin-top: 10px;width: 300px;height:450px;">
                                            </div>
                                        </div>
                                    </span>
                                    <span style="float: right;width: 400px;">
                                        <label style="color: red;font-size: 14px;" class="col-xs-10 control-label">请模拟一条订单数据：</label>
                                        <div class="form-group">
                                            <label class="col-xs-4 control-label" style="margin-top: 20px;text-align: right;">订单号：</label>
                                            <div class="col-xs-5">
                                                <input type="text" style="margin-top: 20px;" class="form-control" placeholder="请输入订单号" name="orderNo" id="orderNo" value="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-xs-4 control-label" style="margin-top: 20px;text-align: right;" >订单总金额(分)：</label>
                                            <div class="col-xs-5">
                                                <input type="text" style="margin-top: 20px;"  class="form-control" placeholder="请输入订单总金额" name="totalFee" id="totalFee" value="5000" readonly="readonly">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-xs-4 control-label" style="margin-top: 20px;text-align: right;" >商品名称：</label>
                                            <div class="col-xs-5">
                                                <input type="text" style="margin-top: 20px;"  class="form-control" placeholder="请输入商品名称" name="goodName" id="goodName" value="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-xs-4 control-label" style="margin-top: 20px;text-align: right;" >订单来源：</label>
                                            <div class="col-xs-5">
							                    <select  class="form-control" name="orderSource" id="orderSource" style="margin-top: 20px;" >
							                        <c:forEach items="${orderSource}" var="os">
		                                                <option value="${os}">${os.description}</option>
		                                            </c:forEach>
							                    </select>
							                  </div>
							            </div>
							            <div class="form-group">
                                            <label class="col-xs-4 control-label">&nbsp;</label>
                                            <div class="col-xs-5">
                                                <button type="button" style="margin-top: 20px;" class="btn btn-primary font14 form-control-static pull-left" id="sendTestTemplateMsg" data-loading-text="开始发送模板消息..."><i class="icon-ok"></i>&nbsp;点击发送模板消息</button>
                                              </div>
                                        </div>
	                                </span>
	                                <span style="float: left;margin-right: 0px;margin-top: 10px;width: 300px;">
	                                   <h5 style="color: green;">请将收到的微信消息中的openid输入到下面的文本框内。</h5>
	                                   <h5 style="color: green;">点击【测试发送模板消息】按钮，即可发送一条模板消息到您的微信中。</h5><br/><br/>
	                                   <div class="form-group">
                                            <label class="col-xs-9 control-label">请输入您的微信openId：</label>
                                            <div class="col-xs-9">
                                                <input type="text" class="form-control" placeholder="请输入您的openId" name="openId" id="openId" value="">
                                            </div>
                                        </div>
	                                </span>
	                            </div>
	                            <div class="col-md-1 hidden-sm hidden-xs" style="border-right: 1px dashed #ccc; height: 500px;margin-top: -20;width: 0;padding:0;margin:0 3%;"></div>
                           </form>
                        </div>
                   </div>
                   
                   
                   <!--第三步：查看库中订单同步记录-->
                   <div class="tab-pane fade" id="dataBaseOrderList">
                        <div class="row">
                            <div class="row">
                                <div class="col-md-12">
                                    <hr class="border-dash">
                                </div>
                            </div>
                            <span>（敬请期待）</span>
                            <!-- <table class="table table-bordered table-hover" id="accordingCustomerTable"> </table> -->
                            <div class="row">
                                <div class="col-md-12">
                                    <hr class="border-dash">
                                </div>
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
