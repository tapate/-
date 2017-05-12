
/**
 * 部门经理处理请假审批
 * @param parentIndex
 */
function deptLeaderAuditDetailForm(parentIndex){
    $.ajax({
        type : "post",
        url : $("#deptLeaderAuditHandleUrl").val(),
        data : $("#deptLeaderAuditDetailForm").serialize(),
        dataType : "json",
        success : function(data) {
            if(data.code == 200){
                layer.msg("处理成功", {
                    shift: 2,
                    icon: 6
                });
                closeMsg(parentIndex);
                
                //重新加载请假审批列表
                $("#deptleaderAuditListTable").bootstrapTable("refresh");
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