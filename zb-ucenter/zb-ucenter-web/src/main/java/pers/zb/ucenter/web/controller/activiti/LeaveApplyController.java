package pers.zb.ucenter.web.controller.activiti;

import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.zb.common.util.AjaxResult;
import pers.zb.common.util.JQDatatableResult;
import pers.zb.common.util.Pager;
import pers.zb.common.util.enums.activiti.ApprovalOpinion;
import pers.zb.common.util.enums.activiti.LeaveType;
import pers.zb.common.util.util.DateUtil;
import pers.zb.common.util.util.JsonUtil;
import pers.zb.entity.activiti.qo.LeaveApplyQo;
import pers.zb.entity.activiti.vo.DeptLeaderAuditVo;
import pers.zb.entity.activiti.vo.HrAuditVo;
import pers.zb.entity.activiti.vo.LeaveApplyHistoryVo;
import pers.zb.entity.activiti.vo.LeaveApplyVo;
import pers.zb.entity.sys.SysUser;
import pers.zb.ucenter.rpc.api.activiti.LeaveApplyService;
import pers.zb.ucenter.rpc.api.user.UserService;


/** =============================== 【该类方法索引，方便定向具体方法体】 =========================================== */
/**
   01、进入请假申请页面   toOaApplyView
   
   02、保存请假申请记录   saveOaApply
   03、进入我发起的请假流程页面   toMyLeaveApplyView
   04、获取自己发起的请假流程列表  myLeaveApplyList
   
   05、跟踪查看请假申请流程进度   traceprocess
   
   06、进入请假列表审批页面(部门经理)    toDeptleaderAuditView
   07、获取部门领导需要审批的请假列表    getDeptLeaderAuditList
   08、进入请假审批处理页面(部门经理)    toDeptleaderAuditHandleView
   09、部门经理审批请假申请    deptleaderAuditComplete
   
   10、进入被驳回的请假申请列表页面    toMyLeaveApplyTurndownView
   11、获取被驳回需要重新调整的请假申请列表    leaveApplyTurndownList
   12、进入请假申请调整页面    toLeaveApplyModifyView
   13、调整请假申请    leaveapplyTurndownModify
   14、进入 我的请假申请历史 页面    toMyLeaveApplyHistoryView
   15、获取我的请假历史列表    leaveApplyHistoryList
   16、获取请假申请处理记录    leaveApplyHandleRecord
   
   17、进入请假列表审批页面(人事)    toHrAuditView
   18、获取人事需要审批的请假列表    getHrAuditList
   19、进入请假审批处理页面(人事)    toHrAuditHandleView
   20、人事审批请假申请    hrAuditComplete
 * */
/** =============================== 【该类方法索引，方便定向具体方法体】 =========================================== */



/**
 * 
 * @description OA请假申请
 * 
 * @author zhoubang
 * @date 2017年3月21日 下午3:21:05
 *
 */
@Controller
@RequestMapping("/oa")
public class LeaveApplyController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LeaveApplyService leaveApplyService;

    @Autowired
    private UserService userService;

    @Autowired
    private RuntimeService runtimeService;
    
    @Autowired
    private RepositoryService repositoryService;
    
    @Autowired
    private HistoryService historyService;
    
    /**
     * 
     * @description 进入请假申请页面
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午1:52:40 
     * 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("/toOaApplyView")
    public String toOaApplyView(ModelMap map) {
        map.put("leaveTypeList", LeaveType.values());// 请假类型
        return "/activiti/oa/oa_apply";
    }

    /**
     * 
     * @description 保存请假申请记录
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午1:53:26 
     * 
     * @param leaveApplyQo
     * @return
     */
    @RequestMapping(value = "/saveOaApply", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult<String> saveOaApply(LeaveApplyQo leaveApplyQo) {
        logger.debug("OA请假申请，leaveApplyQo:" + JsonUtil.toJson(leaveApplyQo));
        
        AjaxResult<String> result = new AjaxResult<String>();

        Subject currentUser = SecurityUtils.getSubject();// 登录对象
        if (currentUser == null) {
            result.setCode(404);
            result.setMsg("请先登录");
            return result;
        }
        
        try {
            // 获取当前登录用户
            SysUser user = userService.getUserByName(String.valueOf(currentUser.getPrincipal()));

            // 提交OA请假申请，并启动流程
            String processInstanceId = leaveApplyService.startWorkflow(leaveApplyQo, user);

            result.setMsg("请假申请成功：流程ID（" + processInstanceId + "）已启动");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("请假申请提交失败");
        }
        return result;
    }

    /**
     * 
     * @description 进入我发起的请假流程页面
     * 
     * @author zhoubang 
     * @date 2017年3月30日 下午7:36:04 
     * 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("/toMyLeaveApplyView")
    public String toMyLeaveApplyView(ModelMap map) {
        return "/activiti/oa/my_leave_apply";
    }
    
    
    /**
     * 
     * @description 获取自己发起的请假流程列表
     * 
     * @author zhoubang 
     * @date 2017年3月31日 下午5:15:10 
     * 
     * @param pager
     * @param leaveApplyQo
     * @return
     * @throws Exception
     */
    @RequestMapping("/myLeaveApplyList")
    @ResponseBody
    public JQDatatableResult<LeaveApplyVo> myLeaveApplyList(Pager<LeaveApplyVo> pager, LeaveApplyQo leaveApplyQo) throws Exception {
        // 获取当前登录用户
        Subject currentUser = SecurityUtils.getSubject();// 登录对象
        SysUser user = userService.getUserByName(String.valueOf(currentUser.getPrincipal()));
        
        leaveApplyQo.setUserId(user.getId());
        logger.debug("获取我发起的请假流程列表，leaveApplyQo:" + JsonUtil.toJson(leaveApplyQo));
        
        pager = leaveApplyService.getMyLeaveApplyList(pager, leaveApplyQo);
        
        JQDatatableResult<LeaveApplyVo> result = new JQDatatableResult<LeaveApplyVo>();
        result.setData(pager.getRows());
        result.setRecordsTotal(pager.getTotal().intValue());
        result.setDraw(pager.getDraw());
        result.setRecordsFiltered(pager.getTotal().intValue());
        return result;
    }
    
    
    /**
     * 
     * @description 跟踪查看请假申请流程进度
     * 
     * @author zhoubang 
     * @date 2017年3月31日 下午5:22:37 
     * 
     * @param executionId
     * @param response
     * @throws Exception
     */
    @RequestMapping("/traceProcess/{executionId}")
    public void traceprocess(@PathVariable("executionId")String executionId,HttpServletResponse response) throws Exception{
        /** 由于dubbo对于服务要求传输的对象都要序列化，而activiti有些对象没有序列化，所以就不能写在serviceimpl里面，不然会报没有序列化的异常。这还是有点坑的。 */
        
        ProcessInstance process = runtimeService.createProcessInstanceQuery().processInstanceId(executionId).singleResult();
        BpmnModel bpmnmodel = repositoryService.getBpmnModel(process.getProcessDefinitionId());
        List<String> activeActivityIds=runtimeService.getActiveActivityIds(executionId);
        DefaultProcessDiagramGenerator gen=new DefaultProcessDiagramGenerator();
        
        // 获得历史活动记录实体（通过启动时间正序排序，不然有的线可以绘制不出来）  
        List<HistoricActivityInstance> historicActivityInstances = historyService
                .createHistoricActivityInstanceQuery().executionId(executionId)  
                .orderByHistoricActivityInstanceStartTime().asc().list();  
        
        // 计算活动线  
        List<String> highLightedFlows = leaveApplyService.getHighLightedFlows((ProcessDefinitionEntity)((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(process.getProcessDefinitionId()),historicActivityInstances); 
        
        InputStream in = gen.generateDiagram(bpmnmodel, "png", activeActivityIds,highLightedFlows,"宋体","宋体",null,1.0);
        //InputStream in = gen.generateDiagram(bpmnmodel, "png", activeActivityIds);
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(in, output);
    }
    
    
    
    /**
     * 
     * @description 进入部门经理审批请假申请页面
     * 
     * @author zhoubang 
     * @date 2017年4月1日 上午9:56:32 
     * 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("/toDeptleaderAuditView")
    public String toDeptleaderAuditView(ModelMap map) {
        return "/activiti/oa/dept_leader_audit";
    }
    
    
    /**
     * 
     * @description 获取部门领导需要审批的请假流程列表
     * 
     * @author zhoubang 
     * @date 2017年4月1日 上午10:04:58 
     * 
     * @param pager
     * @param leaveApplyQo
     * @return
     * @throws Exception 
     */
    @RequestMapping("/deptleader/auditlist")
    @ResponseBody
    public JQDatatableResult<DeptLeaderAuditVo> getDeptLeaderAuditList(Pager<DeptLeaderAuditVo> pager, LeaveApplyQo leaveApplyQo) throws Exception {
        // 获取当前登录用户
        Subject currentUser = SecurityUtils.getSubject();// 登录对象
        SysUser user = userService.getUserByName(String.valueOf(currentUser.getPrincipal()));
        
        leaveApplyQo.setUserId(user.getId());
        logger.debug("获取部门领导需要审批的请假流程列表，leaveApplyQo:" + JsonUtil.toJson(leaveApplyQo));
        
        pager = leaveApplyService.getDeptLeaderAuditList(pager, leaveApplyQo);
        
        JQDatatableResult<DeptLeaderAuditVo> result = new JQDatatableResult<DeptLeaderAuditVo>();
        result.setData(pager.getRows());
        result.setRecordsTotal(pager.getTotal().intValue());
        result.setDraw(pager.getDraw());
        result.setRecordsFiltered(pager.getTotal().intValue());
        return result;
    }
    
    /**
     * 
     * @description 进入请假审批处理页面
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午1:06:05 
     * 
     * @param request
     * @param response
     * @param taskId
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/deptleader/audit/view")
    public String toDeptleaderAuditHandleView(String taskId, ModelMap map) throws Exception {
        DeptLeaderAuditVo vo = leaveApplyService.getDeptleaderAuditByTaskId(taskId);
        map.put("applyDetail", vo);//请假详情
        map.put("createTime", DateUtil.getDateFormat(vo.getCreateTime(), DateUtil.DATE_DEFAULT_FORMAT));//申请日期
        map.put("startDate", DateUtil.getDateFormat(vo.getStartDate(), DateUtil.DATE_DEFAULT_FORMAT));//开始日期
        map.put("endDate", DateUtil.getDateFormat(vo.getEndDate(), DateUtil.DATE_DEFAULT_FORMAT));//结束日期
        map.put("applyDetail", vo);//请假详情
        map.put("taskId", taskId);//任务ID
        map.put("approvalOpinion", ApprovalOpinion.values());//审批意见枚举
        return "/activiti/oa/dept_leader_audit_detail";
    }
    
    
    /**
     * 
     * @description 部门经理审批请假申请
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午1:12:12 
     * 
     * @param taskId
     * @param approval
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/deptleader/audit/handle")
    @ResponseBody
    public AjaxResult<String> deptleaderAuditComplete(@RequestParam(value="taskId",required=true) String taskId, ApprovalOpinion deptleaderApproveResult) throws Exception{
        String approveResult = "true";//默认审批通过
        if(deptleaderApproveResult == ApprovalOpinion.REJECT){
            approveResult = "false";
        }
        // 获取当前登录用户
        Subject currentUser = SecurityUtils.getSubject();// 登录对象
        SysUser user = userService.getUserByName(String.valueOf(currentUser.getPrincipal()));
        
        //审批流程执行
        AjaxResult<String> result = leaveApplyService.deptleaderAuditComplete(taskId, approveResult, user.getId());
        return result;
    }

    
    /**
     * 
     * @description 进入人事审批请假申请页面
     * 
     * @author zhoubang 
     * @date 2017年5月19日 下午2:58:12 
     * 
     * @param map
     * @return
     */
    @RequestMapping("/toHrAuditView")
    public String toHrAuditView(ModelMap map) {
        return "/activiti/oa/hr_audit";
    }
    
    
    /**
     * 
     * @description 获取人事需要审批的请假列表
     * 
     * @author zhoubang 
     * @date 2017年4月1日 上午10:04:58 
     * 
     * @param pager
     * @param leaveApplyQo
     * @return
     * @throws Exception 
     */
    @RequestMapping("/hr/auditlist")
    @ResponseBody
    public JQDatatableResult<HrAuditVo> getHrAuditList(Pager<HrAuditVo> pager, LeaveApplyQo leaveApplyQo) throws Exception {
        // 获取当前登录用户
        Subject currentUser = SecurityUtils.getSubject();// 登录对象
        SysUser user = userService.getUserByName(String.valueOf(currentUser.getPrincipal()));
        
        leaveApplyQo.setUserId(user.getId());
        logger.debug("获取人事需要审批的请假流程列表，leaveApplyQo:" + JsonUtil.toJson(leaveApplyQo));
        
        pager = leaveApplyService.getHrAuditList(pager, leaveApplyQo);
        
        JQDatatableResult<HrAuditVo> result = new JQDatatableResult<HrAuditVo>();
        result.setData(pager.getRows());
        result.setRecordsTotal(pager.getTotal().intValue());
        result.setDraw(pager.getDraw());
        result.setRecordsFiltered(pager.getTotal().intValue());
        return result;
    }
    
    
    /**
     * 
     * @description 进入人事请假审批处理页面
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午1:06:05 
     * 
     * @param request
     * @param response
     * @param taskId
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/hr/audit/view")
    public String toHrAuditHandleView(String taskId, ModelMap map) throws Exception {
        HrAuditVo vo = leaveApplyService.getHrAuditByTaskId(taskId);
        map.put("applyDetail", vo);//请假详情
        map.put("createTime", DateUtil.getDateFormat(vo.getCreateTime(), DateUtil.DATE_DEFAULT_FORMAT));//申请日期
        map.put("startDate", DateUtil.getDateFormat(vo.getStartDate(), DateUtil.DATE_DEFAULT_FORMAT));//开始日期
        map.put("endDate", DateUtil.getDateFormat(vo.getEndDate(), DateUtil.DATE_DEFAULT_FORMAT));//结束日期
        map.put("applyDetail", vo);//请假详情
        map.put("taskId", taskId);//任务ID
        map.put("approvalOpinion", ApprovalOpinion.values());//审批意见枚举
        return "/activiti/oa/hr_audit_detail";
    }
    
    
    /**
     * 
     * @description 人事审批请假申请
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午1:12:12 
     * 
     * @param taskId
     * @param approval
     * @return
     * @throws Exception 
     */
    @RequestMapping(value="/hr/audit/handle")
    @ResponseBody
    public AjaxResult<String> hrAuditComplete(@RequestParam(value="taskId",required=true) String taskId, ApprovalOpinion hrApproveResult) throws Exception{
        String approveResult = "true";//默认审批通过
        if(hrApproveResult == ApprovalOpinion.REJECT){
            approveResult = "false";
        }
        // 获取当前登录用户
        Subject currentUser = SecurityUtils.getSubject();// 登录对象
        SysUser user = userService.getUserByName(String.valueOf(currentUser.getPrincipal()));
        
        //审批流程执行
        AjaxResult<String> result = leaveApplyService.hrAuditComplete(taskId, approveResult, user.getId());
        return result;
    }
    
    
    /**
     * 
     * @description 进入被驳回的请假申请列表页面
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午2:10:36 
     * 
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/toMyLeaveApplyTurndownView")
    public String toMyLeaveApplyTurndownView(ModelMap map) throws Exception {
        return "/activiti/oa/my_leaveapply_turndown";
    }
    
    
    /**
     * 
     * @description 获取被驳回需要重新调整的请假申请列表
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午2:18:36 
     * 
     * @param pager
     * @param leaveApplyQo
     * @return
     * @throws Exception
     */
    @RequestMapping("/leaveapply/turndown/list")
    @ResponseBody
    public JQDatatableResult<DeptLeaderAuditVo> leaveApplyTurndownList(Pager<DeptLeaderAuditVo> pager, LeaveApplyQo leaveApplyQo) throws Exception {
        // 获取当前登录用户
        Subject currentUser = SecurityUtils.getSubject();// 登录对象
        SysUser user = userService.getUserByName(String.valueOf(currentUser.getPrincipal()));
        
        leaveApplyQo.setUserId(user.getId());
        logger.debug("获取被驳回需要重新调整的请假申请列表，leaveApplyQo:" + JsonUtil.toJson(leaveApplyQo));
        
        pager = leaveApplyService.getLeaveApplyTurndownList(pager, leaveApplyQo);
        
        JQDatatableResult<DeptLeaderAuditVo> result = new JQDatatableResult<DeptLeaderAuditVo>();
        result.setData(pager.getRows());
        result.setRecordsTotal(pager.getTotal().intValue());
        result.setDraw(pager.getDraw());
        result.setRecordsFiltered(pager.getTotal().intValue());
        return result;
    }
    
    
    /**
     * 
     * @description 进入请假申请调整页面
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午2:35:16 
     * 
     * @param taskId
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/leaveapply/modify/view")
    public String toLeaveApplyModifyView(String taskId, ModelMap map) throws Exception {
        DeptLeaderAuditVo vo = leaveApplyService.getDeptleaderAuditByTaskId(taskId);
        map.put("applyDetail", vo);//请假详情
        map.put("createTime", DateUtil.getDateFormat(vo.getCreateTime(), DateUtil.DATE_DEFAULT_FORMAT));//申请日期
        map.put("startDate", DateUtil.getDateFormat(vo.getStartDate(), DateUtil.DATE_DEFAULT_FORMAT));//开始日期
        map.put("endDate", DateUtil.getDateFormat(vo.getEndDate(), DateUtil.DATE_DEFAULT_FORMAT));//结束日期
        map.put("applyDetail", vo);//请假详情
        map.put("taskId", taskId);//任务ID
        return "/activiti/oa/my_leaveapply_turndown_modify";
    }
    
    
    /**
     * 
     * @description 调整请假申请
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午3:07:45 
     * 
     * @param taskId
     * @param reapply
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/leaveapply/turndown/modify/handle")
    @ResponseBody
    public AjaxResult<String> leaveapplyTurndownModify(@RequestParam(value="taskId",required=true) String taskId, @RequestParam(value="reapply",required=true) String reapply) throws Exception{
        //审批流程执行
        AjaxResult<String> result = leaveApplyService.leaveapplyTurndownModify(taskId, reapply);
        return result;
    }
    
    
    /**
     * 
     * @description 进入  我的请假申请历史  页面
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午3:34:33 
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/toMyLeaveApplyHistoryView")
    public String toMyLeaveApplyHistoryView(ModelMap map) throws Exception {
        return "/activiti/oa/my_leaveapply_history";
    }
    
    
    /**
     * 
     * @description 获取我的请假历史列表
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午3:40:11 
     * 
     * @param pager
     * @param leaveApplyQo
     * @return
     * @throws Exception
     */
    @RequestMapping("/leaveapply/history/list")
    @ResponseBody
    public JQDatatableResult<LeaveApplyHistoryVo> leaveApplyHistoryList(Pager<LeaveApplyHistoryVo> pager, LeaveApplyQo leaveApplyQo) throws Exception {
        // 获取当前登录用户
        Subject currentUser = SecurityUtils.getSubject();// 登录对象
        SysUser user = userService.getUserByName(String.valueOf(currentUser.getPrincipal()));
        
        leaveApplyQo.setUserId(user.getId());
        logger.debug("获取我的请假历史列表，leaveApplyQo:" + JsonUtil.toJson(leaveApplyQo));
        
        pager = leaveApplyService.getLeaveApplyHistoryList(pager, leaveApplyQo);
        
        JQDatatableResult<LeaveApplyHistoryVo> result = new JQDatatableResult<LeaveApplyHistoryVo>();
        result.setData(pager.getRows());
        result.setRecordsTotal(pager.getTotal().intValue());
        result.setDraw(pager.getDraw());
        result.setRecordsFiltered(pager.getTotal().intValue());
        return result;
    }
    
    /**
     * 
     * @description 获取请假申请处理记录
     * 
     * @author zhoubang 
     * @date 2017年4月1日 下午4:53:33 
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/leaveapply/handle/record")
    @ResponseBody
    public AjaxResult<List<HistoricActivityInstance>> leaveApplyHandleRecord(@RequestParam(value="processInstanceId",required = true) String processInstanceId) throws Exception {
        AjaxResult<List<HistoricActivityInstance>> result = leaveApplyService.leaveApplyHandleRecord(processInstanceId);
        return result;
    }
    
}
