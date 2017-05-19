
/**
 * 人事处理请假审批
 * @param parentIndex
 */
function hrAuditDetailForm(parentIndex){
    $.ajax({
        type : "post",
        url : $("#hrAuditHandleUrl").val(),
        data : $("#hrAuditDetailForm").serialize(),
        dataType : "json",
        success : function(data) {
            if(data.code == 200){
                layer.msg("处理成功", {
                    shift: 2,
                    icon: 6
                });
                closeMsg(parentIndex);
                
                //重新加载请假审批列表
                $("#hrAuditListTable").bootstrapTable("refresh");
            }else{
                layer.msg(data.msg, {
                    shift: 2,
                    icon: 5
                });
                closeMsg(parentIndex);
            }
        }
    });
}