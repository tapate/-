package pers.zb.ucenter.rpc.api.order;

import pers.zb.common.util.AjaxResult;
import pers.zb.entity.order.OrderInfo;
import pers.zb.ucenter.rpc.api.BaseService;

public interface OrderInfoService extends BaseService<OrderInfo> {

    /**
     * 订单同步
     * 
     * 操作人: zhoubang 日期：2016年10月19日 下午12:52:53
     *
     * @param order
     * @throws Exception
     */
    void orderSync(OrderInfo order,AjaxResult<String> result) throws Exception;
}
