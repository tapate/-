package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.group.ModGroupProduct;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.user.Group;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

/**
 * 微信小店分组处理工具类
 */
public class ShopGroupUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
	
    /**
     * 增加分组
     * 
     * @param Group 分组对象
     * @param token 微信公众账号的Token对象
     * @return String 分组ID
     * @throws Exception
     */
    public static String addGroup(Group group, Token token) throws Exception {
    	String groupId = "";
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_ADD_GROUP_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonGroup = JSONObject.fromObject(group).toString();
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonGroup);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			groupId = jsonObject.getString("group_id");
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return groupId;
    }
	
    /**
     * 删除分组
     * 
     * @param groupId 分组ID
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean deleteGroup(String groupId, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_DELETE_GROUP_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"group_id\": %s}";
    	jsonMsg = String.format(jsonMsg, groupId);
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
     * 修改分组属性
     * 
     * @param groupId 分组对象
     * @param groupName 分组名称
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean modGroupProperty(String groupId, String groupName, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_MOD_GROUP_PROPERTY_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"group_id\": %s, \"group_name\": %s}";
    	jsonMsg = String.format(jsonMsg, groupId, groupName);
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
     * 修改分组商品
     * 
     * @param modGroupProduct 待修改分组的商品集合对象
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean modGroupProduct(ModGroupProduct modGroupProduct, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_MOD_GROUP_PRODUCT_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonModGroupProduct = JSONObject.fromObject(modGroupProduct).toString();
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonModGroupProduct);
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
     * 获取所有分组
     * 
     * @param token 微信公众账号的Token对象
     * @return List<Group> 分组对象集合
     * @throws Exception
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
	public static List<Group> findGroup(Token token) throws Exception {
    	List<Group> listGroup = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_FIND_GROUP_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, null);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			listGroup = JSONArray.toList(jsonObject.getJSONArray("groups_detail"), Group.class);
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return listGroup;
    }
	
    /**
     * 获取制定ID的分组对象
     * 
     * @param groupId 分组对象ID
     * @param token 微信公众账号的Token对象
     * @return Group 分组对象
     * @throws Exception
     */
    public static Group getGroup(String groupId, Token token) throws Exception {
    	Group group = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_GET_GROUP_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"group_id\": %s}";
    	jsonMsg = String.format(jsonMsg, groupId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			group = (Group)JSONObject.toBean(jsonObject.getJSONObject("group_detail"), Group.class);
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return group;
    }
}
