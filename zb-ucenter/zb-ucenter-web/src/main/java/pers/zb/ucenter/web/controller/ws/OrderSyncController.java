package pers.zb.ucenter.web.controller.ws;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.zb.common.util.AjaxResult;
import pers.zb.entity.order.OrderInfo;
import pers.zb.ucenter.rpc.api.order.OrderInfoService;

/**
 * 订单同步接口----模拟业务
 * 
 * 操作人: zhoubang 日期：2016年10月19日 下午12:50:35
 *
 */
@Controller
@RequestMapping("/orderSync")
public class OrderSyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private OrderInfoService orderService;
    
    @ResponseBody
    @RequestMapping(value="/create",method = RequestMethod.POST)
    public AjaxResult<String> create(HttpServletRequest request,HttpServletResponse response,OrderInfo order) {
        AjaxResult<String> result = new AjaxResult<String>();
        try {
            orderService.orderSync(order, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
