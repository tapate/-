$(document).ready(function(){
   /** =============================初始化日期控件 start===================================*/
   var start = {
     elem: '#startDate',
     format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
     festival: false, //显示节日
     istoday : true,
     choose: function(datas){ //选择日期完毕的回调
    	 end.min = datas; //开始日选好后，重置结束日的最小日期
    	 $("#startDate").val(datas);
     }
   };
   var end ={
     elem: '#endDate',
     format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
     festival: false, //显示节日
     istoday : true,
     min: laydate.now(), //设定最小日期为当前日期
     choose: function(datas){ //选择日期完毕的回调
    	 start.max = datas; //开始日期最大为当前日期
    	 $("#endDate").val(datas);
     }
   };
   laydate(start);  
   laydate(end);
   /** =============================初始化日期控件 end===================================*/
   
   
   
   /** =============================初始化form表单验证 start===================================*/
   formValidate("#oaApplyForm",{
	   startDate:{
           required: true
       },
       endDate:{
       	required: true
       },
       reason:{
           required: true
       }
   	},{
   		startDate:{
      		required:"请选择开始日期"
      	},
      	endDate:{
	    	required:"请选择结束日期"
	    },
	    reason:{
	    	required:"请输入请假原因"
	    }
   });
   /** =============================初始化form表单验证 end===================================*/
   
   //提交按钮事件
   $("#oaApplyDiv #oaApplySubmitBtn").click(function(){
	   var status = $("#oaApplyForm").validate().form();
	   if(status){
		   $.ajax({
	            type : "post",
	            url : $("#saveOaApplyUrl").val(),
	            data : $("#oaApplyForm").serialize(),
	            dataType : "json",
	            async : false,
	            success : function(data) {
	                if(data.code == 200){
	                    layer.msg(data.msg, {
	                        shift: 2,
	                        icon: 6
	                    });
	                }else{
	                    layer.msg(data.msg, {
	                        shift: 2,
	                        icon: 5
	                    });
	                }
	            }
	        });
	   }
   });
   
   //重置按钮事件
   $("#oaApplyDiv #oaApplyResetBtn").click(function(){
	   $("#oaApplyDiv #startDate").val("");
	   $("#oaApplyDiv #endDate").val("");
	   $("#oaApplyDiv #reason").val("");
   });
   
   
   
   
   
   
});