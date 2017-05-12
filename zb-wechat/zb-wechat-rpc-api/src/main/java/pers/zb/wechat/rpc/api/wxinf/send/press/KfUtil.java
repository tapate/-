package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.kf.KfAccount;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.kf.KfRecord;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

/**
 * 微信客服处理工具类
 */
public class KfUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
	
    /**
     * 增加客服帐号
     * 
     * @param kfAccount 客服帐号对象
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean addKfAccount(KfAccount kfAccount, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_ADD_KF_ACCOUNT_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonKfAccount = JSONObject.fromObject(kfAccount).toString();
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonKfAccount);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "ok".equals(errmsg)) {
        			ifSuccess = true;
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return ifSuccess;
    }
	
    /**
     * 设置客服信息
     * 
     * @param kfAccount 客服帐号对象
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean updateKfAccount(KfAccount kfAccount, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_UPDATE_KF_ACCOUNT_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonKfAccount = JSONObject.fromObject(kfAccount).toString();
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonKfAccount);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "ok".equals(errmsg)) {
        			ifSuccess = true;
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return ifSuccess;
    }
	
    /**
     * 删除客服帐号
     * 
     * @param kfAccount 客服账号
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean deleteKfAccount(String kfAccount, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_DELETE_KF_ACCOUNT_URL).replace("ACCESS_TOKEN", token.getAccessToken().replace("KFACCOUNT", kfAccount));
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "ok".equals(errmsg)) {
        			ifSuccess = true;
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return ifSuccess;
    }
	
    /**
     * 上传客服头像
     * 
     * @param headImgFilePath 上传客服头像图片文件路径
     * @param token 微信公众账号的Token对象
     * @return String 客服头像图片Url
     * @throws Exception
     */
    public static String uploadImg(String headImgFilePath, Token token) throws Exception {
    	String imgUrl = "";
        return imgUrl;
    }
	
    /**
     * 获取客服基本信息
     * 
     * @param token 微信公众账号的Token对象
     * @return List<KfAccount> 客服基本信息对象集合
     * @throws Exception
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
	public static List<KfAccount> findKfAccount(Token token) throws Exception {
    	List<KfAccount> listKfAccount = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_FIND_KF_ACCOUNT_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        try {
        	if (null != jsonObject) {
        		listKfAccount = JSONArray.toList(jsonObject.getJSONArray("kf_list"), KfAccount.class);
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return listKfAccount;
    }
	
    /**
     * 获取在线客服接待信息
     * 
     * @param token 微信公众账号的Token对象
     * @return List<KfAccount> 在线客服接待信息对象集合
     * @throws Exception
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
	public static List<KfAccount> findKfAccountStatus(Token token) throws Exception {
    	List<KfAccount> listKfAccount = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_FIND_KF_ACCOUNT_STATUS_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        try {
        	if (null != jsonObject) {
        		listKfAccount = JSONArray.toList(jsonObject.getJSONArray("kf_online_list"), KfAccount.class);
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return listKfAccount;
    }
	
    /**
     * 获取客服聊天记录
     * 
     * @param token 微信公众账号的Token对象
     * @return List<KfRecord> 客服聊天记录对象集合
     * @throws Exception
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
	public static List<KfRecord> findKfRecord(String startTime, String endTime, String openId, String pageSize, String pageIndex, Token token) throws Exception {
    	List<KfRecord> listKfRecord = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_FIND_KF_RECORD_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"starttime\" : %s, \"endtime\" : %s, \"openid\" : \"%s\", \"pagesize\" : %s, \"pageindex\" : %s}";
    	jsonMsg = String.format(jsonMsg, startTime, endTime, openId, pageSize, pageIndex);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
        		listKfRecord = JSONArray.toList(jsonObject.getJSONArray("kf_list"), KfRecord.class);
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return listKfRecord;
    }
}
