package pers.zb.wechat.rpc.api.wxinf.send.press;


import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;


/**
 * 微信小店货架处理工具类
 */
public class ShopShelfUtilBak {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
	
    /**
     * 增加货架
     * 
     * @param jsonShelfData 货架数据JSON字符串
     * @param token 微信公众账号的Token对象
     * @return String 货架ID
     * @throws Exception
     */
    public static String addShelf(String jsonShelfData, Token token) throws Exception {
    	String shelfId = "";
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_ADD_SHELF_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonShelfData);
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
     * @param jsonShelfData 货架数据JSON字符串
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean modShelf(String jsonShelfData, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_MOD_SHELF_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonShelfData);
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
     * 组装货架控件1的JSON数据字符串
     * 
     * @param groupId 分组ID
     * @param count 该控件展示商品个数
     * @return String 货架控件1的JSON数据字符串
     * @throws Exception
     */
    private static String assemblyModule1(String groupId, String count) {
    	String jsonMsg = "{\"group_info\": {\"filter\": {\"count\": %s}, \"group_id\": %s}, \"eid\": 1}}";
    	jsonMsg = String.format(jsonMsg, count, groupId);
    	return jsonMsg;
    }
    
    /**
     * 组装货架控件2的JSON数据字符串
     * 
     * @param groupIds 分组ID数组
     * @return String 货架控件2的JSON数据字符串
     * @throws Exception
     */
    private static String assemblyModule2(String[] groupIds) {
    	if (groupIds.length < 1 || groupIds.length > 4) {
    		return "";
    	}
    	StringBuffer jsonMsg = new StringBuffer();
    	jsonMsg.append("{\"group_infos\": {\"groups\": [");
    	StringBuffer tempStr = new StringBuffer();
    	for (int i = 0; i < groupIds.length; i++) {
    		tempStr.append("{\"group_id\": " + groupIds[i] + "},");
		}
    	jsonMsg.append(tempStr.substring(0, tempStr.length()-1));
    	jsonMsg.append("]}, \"eid\": 2}");
    	return jsonMsg.toString();
    }
    
    /**
     * 组装货架控件3的JSON数据字符串
     * 
     * @param groupId 分组ID
     * @param img 分组照片(图片需调用图片上传接口获得图片Url填写至此，否则添加货架失败，建议分辨率600*208)
     * @return String 货架控件3的JSON数据字符串
     * @throws Exception
     */
    private static String assemblyModule3(String groupId, String img) {
    	String jsonMsg = "{\"group_info\": {\"group_id\": %s, \"img\": \"%s\"}, \"eid\": 3}";
    	jsonMsg = String.format(jsonMsg, groupId, img);
    	return jsonMsg;
    }
    
    /**
     * 组装货架控件4的JSON数据字符串
     * 
     * @param groupIds 分组ID数组
     * @param imgs 分组照片数组(图片需调用图片上传接口获得图片Url填写至此，否则添加货架失败，3个分组建议分辨率分别为: 350*350, 244*172, 244*172)
     * @return String 货架控件4的JSON数据字符串
     * @throws Exception
     */
    private static String assemblyModule4(String[] groupIds, String[] imgs) {
    	if (groupIds.length < 1 || groupIds.length > 3) {
    		return "";
    	}
    	StringBuffer jsonMsg = new StringBuffer();
    	jsonMsg.append("{\"group_infos\": {\"groups\": [");
    	StringBuffer tempStr = new StringBuffer();
    	for (int i = 0; i < groupIds.length; i++) {
    		tempStr.append("{\"group_id\": " + groupIds[i] + ", \"img\": \"" + imgs[i] + "\"},");
		}
    	jsonMsg.append(tempStr.substring(0, tempStr.length()-1));
    	jsonMsg.append("]}, \"eid\": 4}");
    	return jsonMsg.toString();
    }
    
    /**
     * 组装货架控件5的JSON数据字符串
     * 
     * @param groupIds 分组ID数组
     * @param img 分组照片(图片需调用图片上传接口获得图片Url填写至此，否则添加货架失败，建议分辨率640*1008)
     * @return String 货架控件5的JSON数据字符串
     * @throws Exception
     */
    private static String assemblyModule5(String[] groupIds, String img) {
    	StringBuffer jsonMsg = new StringBuffer();
    	jsonMsg.append("{\"group_infos\": {\"groups\": [");
    	StringBuffer tempStr = new StringBuffer();
    	for (int i = 0; i < groupIds.length; i++) {
    		tempStr.append("{\"group_id\": " + groupIds[i] + "},");
		}
    	jsonMsg.append(tempStr.substring(0, tempStr.length()-1));
    	jsonMsg.append("], \"img_background\": \"" + img + "\"}, \"eid\": 5}");
    	return jsonMsg.toString();
    }
    
    /**
     * 组装货架控件集合的JSON数据字符串
     * 
     * @param assemblyModules 货架控件的JSON数据字符串数组
     * @param shelfBanner 货架招牌图片Url(图片需调用图片上传接口获得图片Url填写至此，否则添加货架失败，建议尺寸为640*120，仅控件1-4有banner，控件5没有banner)
     * @param shelfName 货架名称
     * @return String 货架控件集合的JSON数据字符串
     * @throws Exception
     */
    private static String assemblyShelfData(String[] assemblyModules, String shelfId, String shelfBanner, String shelfName) {
    	StringBuffer jsonMsg = new StringBuffer();
    	if (shelfId != null && shelfId.length() > 0) {
    		jsonMsg.append("{\"shelf_id\": %s, ");
    	}
    	jsonMsg.append("\"shelf_data\": {\"module_infos\": [");
    	StringBuffer tempStr = new StringBuffer();
    	for (int i = 0; i < assemblyModules.length; i++) {
    		tempStr.append(assemblyModules[i] + ",");
    	}
    	jsonMsg.append(tempStr.substring(0, tempStr.length()-1));
    	jsonMsg.append("]}, \"shelf_banner\": \"" + shelfBanner + "\", \"shelf_name\": \"" + shelfName + "\"}");
    	return jsonMsg.toString();
    }
}
