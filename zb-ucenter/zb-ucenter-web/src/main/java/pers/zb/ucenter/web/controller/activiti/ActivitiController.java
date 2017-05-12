package pers.zb.ucenter.web.controller.activiti;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import pers.zb.common.util.AjaxResult;
import pers.zb.common.util.Pager;
import pers.zb.common.util.util.JsonUtil;
import pers.zb.entity.activiti.qo.ProcessQo;
import pers.zb.entity.activiti.vo.ProcessVo;
import pers.zb.ucenter.rpc.api.activiti.ProcessListService;

/**
 * 
 * @description activiti流程定义相关
 * 
 * @author zhoubang 
 * @date 2017年3月29日 下午5:03:49 
 *
 */
@Controller
@RequestMapping("/activiti")
public class ActivitiController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessListService processListService;

    @Autowired
    private RepositoryService repositoryService;
    
    /**
     * 
     * @description 进入查看已部署的工作流页面
     * 
     * @author zhoubang
     * @date 2017年3月21日 下午3:21:49
     * 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping("/toProcessListView")
    public String toUserListView(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        return "/activiti/processlist";
    }

    /**
     * 
     * @description 获取已经部署的工作流列表
     * 
     * @author zhoubang
     * @date 2017年3月21日 下午5:04:05
     * 
     * @param pager
     * @param processQo
     * @return
     */
    @RequestMapping("/processList")
    @ResponseBody
    public Object list(Pager<ProcessVo> pager, ProcessQo processQo) {
        logger.debug("获取已经部署的工作流列表，processQo:" + JsonUtil.toJson(processQo));
        pager = processListService.getList(pager, processQo);
        logger.debug("已经部署的工作流列表:" + JsonUtil.toJson(pager.getRows()));
        return pager;
    }

    /**
     * 
     * @description 删除已经部署的流程实例
     * 
     * @author zhoubang
     * @date 2017年3月21日 下午5:05:48
     * 
     * @param request
     * @param response
     * @param qo
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteProcess")
    public AjaxResult<String> deleteProcess(HttpServletRequest request, HttpServletResponse response, ProcessQo qo) {
        AjaxResult<String> result = new AjaxResult<String>();

        try {
            processListService.deleteProcess(qo, result);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("流程删除失败");
        }
        return result;
    }

    /**
     * 
     * @description 上传流程文件
     * 
     * @author zhoubang
     * @date 2017年3月29日 上午11:02:07
     * 
     * @param uploadFile
     * @param request
     * @return
     */
    @RequestMapping("/uploadWorkFlow")
    public String processFileUpload(@RequestParam MultipartFile uploadFile, HttpServletRequest request) {
        try {
            processListService.processFileUpload(uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/activiti/toProcessListView";
    }

    /**
     * 
     * @description 查看流程文件内容
     * 
     * @author zhoubang
     * @date 2017年3月29日 下午1:55:02
     * 
     * @param processDeploymentId
     * @param resource
     * @param response
     * @throws Exception
     */
    @RequestMapping("/showProcessResource")
    public void showProcessResource(@RequestParam("processDeploymentId") String processDeploymentId,
            @RequestParam("resource") String resource, HttpServletResponse response) throws Exception {

        // 获取流程定义
        ProcessDefinition def = processListService.getProcessDefinition(processDeploymentId, resource);

        // 在服务调用方实现InputStream流的解析操作，因为InputStream没有实现序列化，而dubbo是要求必须实现序列化，所以只能在服务调用方进行流的操作
        InputStream is = repositoryService.getResourceAsStream(def.getDeploymentId(), resource);
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(is, output);
    }
}
