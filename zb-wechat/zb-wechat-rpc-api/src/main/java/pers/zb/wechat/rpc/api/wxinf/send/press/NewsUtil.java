package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.request.news.SendGroupNewsRequest;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.request.news.SendUsersNewsRequest;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.request.news.UploadArticle;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.request.news.UploadArticleRequest;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.UploadArticleResponse;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

/**
* 微信图文消息工具类
*/
public class NewsUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
    
    /**
     * 上传图文消息素材
     * 
     * @param uploadArticleRequest 上传图文消息请求对象
     * @param token 微信公众账号的Token对象
     * @return UploadArticleResponse 上传图文消息响应对象
     * @throws Exception
     */
	public static UploadArticleResponse uploadNews(UploadArticleRequest uploadArticleRequest, Token token) throws Exception {
		List<UploadArticle> articles = uploadArticleRequest.getArticles();
		UploadArticleResponse uploadArticleResponse = null;
		int size = articles.size();
		if (size == 0 || size > 10) {
			return uploadArticleResponse;
		}
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_UPLOAD_NEWS_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"articles\": [";
        for (Iterator<UploadArticle> iterator = articles.iterator(); iterator.hasNext();) {
			UploadArticle uploadArticle = (UploadArticle) iterator.next();
			String thumbMediaId = uploadArticle.getThumbMediaId();
			String author = uploadArticle.getAuthor();
			String title = uploadArticle.getTitle();
			String contentSourceUrl = uploadArticle.getContentSourceUrl();
			String content = uploadArticle.getContent();
			String digest = uploadArticle.getDigest();
			
			if (thumbMediaId.trim().length() == 0 || title.trim().length() == 0 || content.trim().length() == 0) {
				return uploadArticleResponse;
			}
			
			jsonMsg += "{\"thumb_media_id\":\"%s\",\"author\":\"%s\",\"title\":\"%s\",\"content_source_url\":\"%s\",\"content\":\"%s\",\"digest\":\"%s\"},";
			jsonMsg = String.format(jsonMsg, thumbMediaId, author, title, MenuUtil.getOauth2TokenUrl(contentSourceUrl), content, digest);
		}
        jsonMsg = jsonMsg.substring(0, jsonMsg.length()-1);
        jsonMsg += "]}";
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
		uploadArticleResponse = new UploadArticleResponse();
        try {
        	if (null != jsonObject) {
            	uploadArticleResponse.setType(jsonObject.getString("type"));
            	uploadArticleResponse.setMediaId(jsonObject.getString("media_id"));
            	uploadArticleResponse.setCreateTime(jsonObject.getString("created_at"));
            }
        } catch(Exception e) {
        	uploadArticleResponse.setErrCode(jsonObject.getString("errcode"));
        	uploadArticleResponse.setErrMsg(jsonObject.getString("errmsg"));
        }
        return uploadArticleResponse;
    }
    
    /**
     * 根据分组群发图文消息
     * 
     * @param sendGroupNewsRequest 根据用户分组群发图文消息请求对象
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
	public static ResultResponse sendGroupNews(SendGroupNewsRequest sendGroupNewsRequest, Token token) throws Exception {
		int groupId = sendGroupNewsRequest.getGroupId();
		String mediaId = sendGroupNewsRequest.getMediaId();
		ResultResponse resultResponse = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_SEND_GROUP_NEWS_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"filter\":{\"group_id\":\"%s\"},\"mpnews\":{\"media_id\":\"%s\"},\"msgtype\":\"mpnews\"}";
        jsonMsg = String.format(jsonMsg, groupId, mediaId);
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
     * 根据OpenID列表群发图文消息
     * 
     * @param sendUsersNewsRequest 根据用户列表群发图文消息请求对象
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
	public static ResultResponse sendUsersNews(SendUsersNewsRequest sendUsersNewsRequest, Token token) throws Exception {
		String mediaId = sendUsersNewsRequest.getMediaId();
		ResultResponse resultResponse = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_SEND_USERS_NEWS_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"touser\":[";
        String[] openIds = sendUsersNewsRequest.getOpenIds();
        for (int i = 0; i < openIds.length; i++) {
        	String openId = openIds[i];
			if (openId.trim().length() > 0) {
				jsonMsg += "\"%s\",";
				jsonMsg = String.format(jsonMsg, openId.trim());
			}
		}
        jsonMsg = jsonMsg.substring(0, jsonMsg.length()-1);
        jsonMsg += "],\"mpnews\":{\"media_id\":\"%s\"},\"msgtype\":\"mpnews\"}";
        jsonMsg = String.format(jsonMsg, mediaId);
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
     * 删除指定群发图文消息
     * 
     * @param msgId 群发图文消息ID
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
	public static ResultResponse deleteNews(int msgId, Token token) throws Exception {
		ResultResponse resultResponse = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_DELETE_NEWS_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"msgid\":%s}";
        jsonMsg = String.format(jsonMsg, msgId);
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
