
/**
 * 重新提交请假申请
 * @param parentIndex
 */
function deptLeaderAuditDetailForm(parentIndex){
    $.ajax({
        type : "post",
        url : $("#leaveapplyTurndownModifyHandleUrl").val(),
        data : $("#leaveapplyTurndownModifyForm").serialize(),
        dataType : "json",
        success : function(data) {
            if(data.code == 200){
                layer.msg("提交成功", {
                    shift: 2,
                    icon: 6
                });
                closeMsg(parentIndex);
                
                //重新加载需要重新调整的请假申请列表
                $("#leaveApplyTurndownListTable").bootstrapTable("refresh");
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