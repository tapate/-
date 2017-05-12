package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf.Shelf;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

/**
 * 微信小店货架处理工具类
 */
public class ShopShelfUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();

    /**
     * 增加货架
     * 
     * @param shelf 货架对象
     * @param token 微信公众账号的Token对象
     * @return String 货架ID
     * @throws Exception
     */
    public static String addShelf(Shelf shelf, Token token) throws Exception {
    	String shelfId = "";
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_ADD_SHELF_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonShelf = JSONObject.fromObject(shelf).toString();
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonShelf);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			shelfId = jsonObject.getString("shelf_id");
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return shelfId;
    }
	
    /**
     * 删除货架
     * 
     * @param shelfId 货架ID
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean deleteShelf(String shelfId, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_DELETE_SHELF_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"shelf_id\": %s}";
    	jsonMsg = String.format(jsonMsg, shelfId);
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
     * 修改货架
     * 
     * @param shelf 货架对象
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean modShelf(Shelf shelf, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_MOD_SHELF_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonShelf = JSONObject.fromObject(shelf).toString();
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonShelf);
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
     * 获取所有货架
     * 
     * @param token 微信公众账号的Token对象
     * @return List<Shelf> 货架对象集合
     * @throws Exception
     */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static List<Shelf> findShelf(Token token) throws Exception {
    	List<Shelf> listShelf = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_FIND_SHELF_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, null);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			listShelf = JSONArray.toList(jsonObject.getJSONArray("shelves"), Shelf.class);
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return listShelf;
    }
	
    /**
     * 查询货架
     * 
     * @param shelfId 货架ID
     * @param token 微信公众账号的Token对象
     * @return Shelf 货架对象
     * @throws Exception
     */
    public static Shelf getShelf(String shelfId, Token token) throws Exception {
    	Shelf shelf = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_GET_SHELF_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"shelf_id\": %s}";
    	jsonMsg = String.format(jsonMsg, shelfId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			shelf = (Shelf)JSONObject.toBean(jsonObject.getJSONObject("shelf_info"), Shelf.class);
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return shelf;
    }
}
