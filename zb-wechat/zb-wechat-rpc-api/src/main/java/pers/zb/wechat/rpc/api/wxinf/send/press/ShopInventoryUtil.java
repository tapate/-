package pers.zb.wechat.rpc.api.wxinf.send.press;

import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

/**
* 微信小店库存处理工具类
*/
public class ShopInventoryUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
	
    /**
     * 增加库存
     * 
     * @param productId 商品ID
     * @param skuInfo sku信息, 格式"id1:vid1;id2:vid2"
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean addInventory(String productId, String skuInfo, String quantity, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_ADD_INVENTORY_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"product_id\": %s, \"sku_info\": %s, \"quantity\": %s}";
    	jsonMsg = String.format(jsonMsg, productId, skuInfo, quantity);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			ifSuccess = true;
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return ifSuccess;
    }
	
    /**
     * 减少库存
     * 
     * @param productId 商品ID
     * @param skuInfo sku信息, 格式"id1:vid1;id2:vid2"
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean reduceInventory(String productId, String skuInfo, String quantity, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_REDUCE_INVENTORY_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"product_id\": %s, \"sku_info\": %s, \"quantity\": %s}";
    	jsonMsg = String.format(jsonMsg, productId, skuInfo, quantity);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			ifSuccess = true;
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return ifSuccess;
    }
}
