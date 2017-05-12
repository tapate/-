package pers.zb.ucenter.rpc.api.wechat;

import pers.zb.common.util.AjaxResult;
import pers.zb.entity.order.OrderInfo;

public interface TemplateMessageService{

    /**
     * 发送微信模板消息
     * @param openId
     * @param result 
     * @throws Exception
     */
    void sendMessage(String openId, OrderInfo orderInfo, AjaxResult<String> result) throws Exception;
}
