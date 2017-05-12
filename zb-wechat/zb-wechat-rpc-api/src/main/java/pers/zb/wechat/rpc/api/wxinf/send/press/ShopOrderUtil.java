package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Order.Order;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Order.OrderDelivery;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

/**
 * 微信小店订单处理工具类
 */
public class ShopOrderUtil {

    private static ConfigManager configManager = ConfigManager.getInstance();

    /**
     * 根据订单ID获取订单详情
     * 
     * @param orderId
     *            订单ID
     * @param token
     *            微信公众账号的Token对象
     * @return Order 订单对象
     * @throws Exception
     */
    public static Order getOrder(String orderId, Token token) throws Exception {
        Order order = null;
        String requestUrl = configManager.getConfigValue(
                Constants.WX_POST_GET_ORDER_URL).replace("ACCESS_TOKEN",
                token.getAccessToken());
        String jsonMsg = "{\"order_id\": %s}";
        jsonMsg = String.format(jsonMsg, orderId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(
                requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
            if (null != jsonObject) {
                String errcode = jsonObject.getString("errcode");
                String errmsg = jsonObject.getString("errmsg");
                if ("0".equals(errcode) && "success".equals(errmsg)) {
                    order = (Order) JSONObject.toBean(
                            jsonObject.getJSONObject("order"), Order.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    /**
     * 根据订单状态/创建时间获取订单详情
     * 
     * @param status
     *            订单状态(不带该字段-全部状态, 2-待发货, 3-已发货, 5-已完成, 8-维权中, )
     * @param begintime
     *            订单创建时间起始时间(不带该字段则不按照时间做筛选)
     * @param endtime
     *            订单创建时间终止时间(不带该字段则不按照时间做筛选)
     * @param token
     *            微信公众账号的Token对象
     * @return List<Order> 订单对象集合
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
    public static List<Order> findOrder(String status, String begintime,
            String endtime, Token token) throws Exception {
        List<Order> listOrder = null;
        String requestUrl = configManager.getConfigValue(
                Constants.WX_POST_FIND_ORDER_URL).replace("ACCESS_TOKEN",
                token.getAccessToken());
        String jsonMsg = "{\"status\": %s, \"begintime\": %s, \"endtime\": %s}";
        jsonMsg = String.format(jsonMsg, status, begintime, endtime);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(
                requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
            if (null != jsonObject) {
                String errcode = jsonObject.getString("errcode");
                String errmsg = jsonObject.getString("errmsg");
                if ("0".equals(errcode) && "success".equals(errmsg)) {
                    listOrder = JSONArray.toList(
                            jsonObject.getJSONArray("order_list"), Order.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOrder;
    }

    /**
     * 设置订单发货信息
     * 
     * @param orderDelivery
     *            订单发货信息对象
     * @param token
     *            微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean setDelivery(OrderDelivery orderDelivery, Token token)
            throws Exception {
        Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(
                Constants.WX_POST_SET_DELIVERY_URL).replace("ACCESS_TOKEN",
                token.getAccessToken());
        String jsonOrderDelivery = JSONObject.fromObject(orderDelivery)
                .toString();
        JSONObject jsonObject = HttpClientConnectionManager
                .httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST,
                        jsonOrderDelivery);
        try {
            if (null != jsonObject) {
                String errcode = jsonObject.getString("errcode");
                String errmsg = jsonObject.getString("errmsg");
                if ("0".equals(errcode) && "success".equals(errmsg)) {
                    ifSuccess = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ifSuccess;
    }

    /**
     * 关闭订单
     * 
     * @param orderId
     *            订单ID
     * @param token
     *            微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean closeOrder(String orderId, Token token)
            throws Exception {
        Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(
                Constants.WX_POST_CLOSE_ORDER_URL).replace("ACCESS_TOKEN",
                token.getAccessToken());
        String jsonMsg = "{\"order_id\": %s}";
        jsonMsg = String.format(jsonMsg, orderId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(
                requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
            if (null != jsonObject) {
                String errcode = jsonObject.getString("errcode");
                String errmsg = jsonObject.getString("errmsg");
                if ("0".equals(errcode) && "success".equals(errmsg)) {
                    ifSuccess = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ifSuccess;
    }
}
