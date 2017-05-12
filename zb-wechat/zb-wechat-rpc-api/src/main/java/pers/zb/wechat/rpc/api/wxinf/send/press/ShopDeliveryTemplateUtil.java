package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.DeliveryTemplate.DeliveryTemplate;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;


/**
 * 微信小店邮费模版处理工具类
 */
public class ShopDeliveryTemplateUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
	
    /**
     * 增加邮费模版
     * 
     * @param DeliveryTemplate 邮费模版对象
     * @param token 微信公众账号的Token对象
     * @return String 邮费模板ID
     * @throws Exception
     */
    public static String addDeliveryTemplate(DeliveryTemplate deliveryTemplate, Token token) throws Exception {
    	String templateId = "";
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_ADD_DELIVERY_TEMPLATE_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonDeliveryTemplate = JSONObject.fromObject(deliveryTemplate).toString();
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonDeliveryTemplate);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			templateId = jsonObject.getString("template_id");
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return templateId;
    }
	
    /**
     * 删除邮费模版
     * 
     * @param templateId 邮费模版ID
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean deleteDeliveryTemplate(String templateId, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_DELETE_DELIVERY_TEMPLATE_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"template_id\": %s}";
    	jsonMsg = String.format(jsonMsg, templateId);
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
     * 修改邮费模版
     * 
     * @param DeliveryTemplate 邮费模版对象
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean updateDeliveryTemplate(DeliveryTemplate deliveryTemplate, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_ADD_DELIVERY_TEMPLATE_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonDeliveryTemplate = JSONObject.fromObject(deliveryTemplate).toString();
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonDeliveryTemplate);
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
     * 获取制定ID的邮费模版对象
     * 
     * @param templateId 邮费模版ID
     * @param token 微信公众账号的Token对象
     * @return DeliveryTemplate 邮费模版对象
     * @throws Exception
     */
    public static DeliveryTemplate getDeliveryTemplate(String templateId, Token token) throws Exception {
    	DeliveryTemplate deliveryTemplate = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_GET_DELIVERY_TEMPLATE_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"template_id\": %s}";
    	jsonMsg = String.format(jsonMsg, templateId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			deliveryTemplate = (DeliveryTemplate)JSONObject.toBean(jsonObject.getJSONObject("template_info"), DeliveryTemplate.class);
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return deliveryTemplate;
    }
	
    /**
     * 获取所有邮费模版
     * 
     * @param token 微信公众账号的Token对象
     * @return List<DeliveryTemplate> 邮费模版对象集合
     * @throws Exception
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
	public static List<DeliveryTemplate> findDeliveryTemplate(Token token) throws Exception {
    	List<DeliveryTemplate> listDeliveryTemplate = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_FIND_DELIVERY_TEMPLATE_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, null);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			listDeliveryTemplate = JSONArray.toList(jsonObject.getJSONArray("templates_info"), DeliveryTemplate.class);
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return listDeliveryTemplate;
    }
}
