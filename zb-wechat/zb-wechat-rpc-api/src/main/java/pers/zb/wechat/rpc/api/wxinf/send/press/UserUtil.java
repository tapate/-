package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.user.Group;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.user.GroupList;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.user.UserCumulate;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.user.UserDataList;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.user.UserInfo;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.user.UserList;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.user.UserSummary;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;


/**
* 微信用户处理工具类
*/
public class UserUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
    
    /**
     * 获取用户信息
     * 
     * @param openId 需要获取微信用户信息的OpenId
     * @param token 微信公众账号的Token对象
     * @return UserInfo 微信用户基本信息
     * @throws Exception
     */
    public static UserInfo getUserInfo(String openId, Token token) throws Exception {
        UserInfo userInfo = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_USER_INFO_URL).replace("ACCESS_TOKEN", token.getAccessToken()).replace("OPENID", openId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        if (null != jsonObject) {
            try {
                userInfo = new UserInfo();
                userInfo.setOpenId(jsonObject.getString("openid"));
                userInfo.setSubscribe(jsonObject.getString("subscribe"));
                userInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
                userInfo.setNickName(jsonObject.getString("nickname"));
                userInfo.setSex(jsonObject.getString("sex"));
                userInfo.setCountry(jsonObject.getString("country"));
                userInfo.setProvince(jsonObject.getString("province"));
                userInfo.setCity(jsonObject.getString("city"));
                userInfo.setLanguage(jsonObject.getString("language"));
                userInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch(Exception e) {
            	e.printStackTrace();
            	userInfo.setErrCode(jsonObject.getString("errcode"));
            	userInfo.setErrMsg(jsonObject.getString("errmsg"));
            }
        }
        return userInfo;
    }
    
    /**
     * 获取关注用户列表
     * 
     * @param nextOpenId 前次获取用户列表中最后一个用户的OpenId，第一次则传""
     * @param token 微信公众账号的Token对象
     * @return UserList 微信关注用户列表对象
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
	public static UserList getUserList(String nextOpenId, Token token) throws Exception {
        UserList userList = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_USER_LIST_URL).replace("ACCESS_TOKEN", token.getAccessToken()).replace("NEXT_OPENID", nextOpenId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        if (null != jsonObject) {
            try {
                userList = new UserList();
                userList.setTotal(jsonObject.getInt("total"));
                userList.setCount(jsonObject.getInt("count"));
                userList.setNextOpenId(jsonObject.getString("next_openid"));
                JSONObject dataObject = (JSONObject)jsonObject.get("data");
                userList.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"), List.class));
            } catch(Exception e) {
            	userList.setErrCode(jsonObject.getString("errcode"));
            	userList.setErrMsg(jsonObject.getString("errmsg"));
            }
        }
        return userList;
    }
    
    /**
     * 获取用户分组列表
     * 
     * @param token 微信公众账号的Token对象
     * @return GroupList 用户分组列表对象
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
	public static GroupList getGroupList(Token token) throws Exception {
    	GroupList groupList = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_GROUP_LIST_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        if (null != jsonObject) {
            try {
            	groupList = new GroupList();
            	groupList.setGroups(JSONArray.toList(jsonObject.getJSONArray("groups"), Group.class));
            } catch(Exception e) {
            	groupList.setErrCode(jsonObject.getString("errcode"));
            	groupList.setErrMsg(jsonObject.getString("errmsg"));
            }
        }
        return groupList;
    }
    
    /**
     * 创建用户分组
     * 
     * @param group 用户分组对象
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
	public static ResultResponse createGroup(Group group, Token token) throws Exception {
		ResultResponse resultResponse = null;
		int groupId = group.getId();
		String groupName = group.getName();
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_CREATE_GROUP_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"group\": {\"id\": %s,\"name\": \"%s\"}}";
        jsonMsg = String.format(jsonMsg, groupId, groupName);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
            	resultResponse = new ResultResponse();
            	resultResponse.setErrCode(jsonObject.getString("errcode"));
            	resultResponse.setErrMsg(jsonObject.getString("errmsg"));
            }
        } catch(Exception e) {
        	resultResponse = null;
        	e.printStackTrace();
        }
        return resultResponse;
    }
    
    /**
     * 修改用户分组名称
     * 
     * @param group 用户分组对象
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
	public static ResultResponse updateGroup(Group group, Token token) throws Exception {
		ResultResponse resultResponse = null;
		int groupId = group.getId();
		String groupName = group.getName();
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_UPDATE_GROUP_NAME_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"group\":{\"id\":%s,\"name\":\"%s\"}}";
        jsonMsg = String.format(jsonMsg, groupId, groupName);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
            	resultResponse = new ResultResponse();
            	resultResponse.setErrCode(jsonObject.getString("errcode"));
            	resultResponse.setErrMsg(jsonObject.getString("errmsg"));
            }
        } catch(Exception e) {
        	resultResponse = null;
        	e.printStackTrace();
        }
        return resultResponse;
    }
    
    /**
     * 查询用户所在分组
     * 
     * @param openId 查询用户的OpenId
     * @param token 微信公众账号的Token对象
     * @return int 当前用户所属的分组ID
     * @throws Exception
     */
	public static int findUserGroup(String openId, Token token) throws Exception {
		int groupId = 0;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_FIND_USER_GROUP_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"openid\":\"%s\"}";
        jsonMsg = String.format(jsonMsg, openId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        if (null != jsonObject) {
        	groupId = jsonObject.getInt("groupid");
        }
        return groupId;
    }
    
    /**
     * 移动用户分组
     * 
     * @param openId 需要移动分组的用户的OpenId
     * @param toGroupId 移动目标的分组ID
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
	public static ResultResponse moveUserGroup(String openId, int toGroupId, Token token) throws Exception {
		ResultResponse resultResponse = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_MOVE_USER_GROUP_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"openid\":\"%s\",\"to_groupid\":%s}";
        jsonMsg = String.format(jsonMsg, openId, toGroupId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
            	resultResponse = new ResultResponse();
            	resultResponse.setErrCode(jsonObject.getString("errcode"));
            	resultResponse.setErrMsg(jsonObject.getString("errmsg"));
            }
        } catch(Exception e) {
        	resultResponse = null;
        	e.printStackTrace();
        }
        return resultResponse;
    }
    
    /**
     * 获取用户增减数据列表
     * 
     * @param token 微信公众账号的Token对象
     * @return GroupList 用户分组列表对象
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
	public static UserDataList<UserSummary> getUserSummaryList(String beginDate, String endDate, Token token) throws Exception {
    	UserDataList<UserSummary> userSummaryList = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_USER_SUMMARY_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"begin_date\": \"%s\", \"end_date\": \"%s\"}";
        jsonMsg = String.format(jsonMsg, beginDate, endDate);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        if (null != jsonObject) {
            try {
            	userSummaryList = new UserDataList<UserSummary>();
            	userSummaryList.setList(JSONArray.toList(jsonObject.getJSONArray("list"), UserSummary.class));
            } catch(Exception e) {
            	e.printStackTrace();
            	userSummaryList.setErrCode(jsonObject.getString("errcode"));
            	userSummaryList.setErrMsg(jsonObject.getString("errmsg"));
            }
        }
        return userSummaryList;
    }
    
    /**
     * 获取累计用户数据列表
     * 
     * @param token 微信公众账号的Token对象
     * @return GroupList 用户分组列表对象
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
	public static UserDataList<UserCumulate> getUserCumulateList(String beginDate, String endDate, Token token) throws Exception {
    	UserDataList<UserCumulate> userCumulateList = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_USER_CUMULATE_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"begin_date\": \"%s\", \"end_date\": \"%s\"}";
        jsonMsg = String.format(jsonMsg, beginDate, endDate);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        if (null != jsonObject) {
            try {
            	userCumulateList = new UserDataList<UserCumulate>();
            	userCumulateList.setList(JSONArray.toList(jsonObject.getJSONArray("list"), UserCumulate.class));
            } catch(Exception e) {
            	e.printStackTrace();
            	userCumulateList.setErrCode(jsonObject.getString("errcode"));
            	userCumulateList.setErrMsg(jsonObject.getString("errmsg"));
            }
        }
        return userCumulateList;
    }
	
    /**
     * 设置备注名
     * 
     * @param openId 用户标识
     * @param remark 用户备注
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
    public static ResultResponse addKfAccount(String openId, String remark, Token token) throws Exception {
    	ResultResponse resultResponse = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_UPDATE_REMARK_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"openid\":\"%s\", \"remark\":\"%s\"}";
        jsonMsg = String.format(jsonMsg, openId, remark);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
            	resultResponse = new ResultResponse();
            	resultResponse.setErrCode(jsonObject.getString("errcode"));
            	resultResponse.setErrMsg(jsonObject.getString("errmsg"));
            }
        } catch(Exception e) {
        	resultResponse = null;
        	e.printStackTrace();
        }
        return resultResponse;
    }
}
