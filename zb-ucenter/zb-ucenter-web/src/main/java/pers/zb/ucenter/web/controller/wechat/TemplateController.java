package pers.zb.ucenter.web.controller.wechat;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.zb.common.util.AjaxResult;
import pers.zb.common.util.enums.order.OrderSource;
import pers.zb.entity.order.OrderInfo;
import pers.zb.ucenter.rpc.api.wechat.TemplateMessageService;

/**
 * 微信接口相关功能开发
 * 
 * 操作人: zhoubang 日期：2016年10月22日 下午5:14:46
 *
 */
@Controller
@RequestMapping("/wechat/template")
public class TemplateController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private TemplateMessageService templateMessageService;
    
    /**
     * 进入微信模板消息发送页面
     * 
     * 操作人: zhoubang 日期：2016年10月22日 下午5:15:52
     *
     * @param map
     * @return
     */
    @RequestMapping("toTemplateMsgView")
    public String toTemplateMsgView(ModelMap map) {
        map.put("orderSource", OrderSource.values());
        return "/wechat/template/templateMsgSend";
    }

    /**
     * 发送微信模板消息
     * @param openId
     * @return
     */
    @RequestMapping("send")
    @ResponseBody
    public AjaxResult<String> send(String openId,OrderInfo orderInfo) {
        AjaxResult<String> result = new AjaxResult<String>();
        logger.debug(String.format("开始发送微信模板消息，openId：%s", openId));
        try {
            //模拟一条订单数据
            /*OrderInfo orderInfo = new OrderInfo();
            orderInfo.setCreateTime(new Date());
            orderInfo.setGoodName("一箱苹果");
            orderInfo.setOrderNo("TEST2016102411250000");
            orderInfo.setOrderSource(OrderSource.BACK_SYSTEM);
            orderInfo.setTotalFee(5000);
            orderInfo.setUpdateTime(new Date());*/
            orderInfo.setCreateTime(new Date());
            
            //发送模板消息
            templateMessageService.sendMessage(openId,orderInfo,result);
        } catch (Exception e) {
            logger.debug(String.format("发送微信模板消息失败，错误信息：%s", e.getMessage()));
            result.setCode(500);
            result.setMsg("消息发送失败，请稍后重试。");
            return result;
        }
        return result;
    }
}
