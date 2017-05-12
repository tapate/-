
var templateMsg = {
	templateFormId : "templateForm",
	formValidateInit : function(){
		formValidate($("#" + this.templateFormId),{
	    	openId:{
	            required: true
	        },
	        orderNo:{
	        	required: true
	        },
	        goodName:{
	            required: true
	        }
	    },{
	    	openId:{
	       		required:"请输入您的微信openId"
	       	},
	       	orderNo:{
		    	required:"请输入订单号"
		    },
		    goodName:{
		    	required:"请输入商品名称"
		    }
	    });
	},
	/**
	 * 发送一条测试的微信模板消息
	 */
	send : function(){
		var valide =$("#" + templateMsg.templateFormId).validate().form();
		if(valide){
			$("#sendTestTemplateMsg").button('loading');
			var sendTemplateMsgUrl = $("#sendTemplateMsgUrl").val();
			$.ajax({
				type : "post",
				url : sendTemplateMsgUrl,
				dataType : "json",
				async : true,
				data : $("#templateForm").serialize(),
				success : function(result){
					console.log(result);
					if(result.code == 200){
						$("#sendTestTemplateMsg").button('reset');
						layer.msg('消息已成功发送到消息队列，请等待接收', {
	                        shift: 2,
	                        icon: 6
	                    });
					}
				}
			});
		}
	}
}


$(document).ready(function(){
	templateMsg.formValidateInit();
	
	//发送测试的模板消息
	$("#sendTestTemplateMsg").click(function(){
		templateMsg.send();
	});
});