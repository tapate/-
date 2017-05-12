package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.util.List;

import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message.Article;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message.ImageMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message.MusicMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message.NewsMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message.TextMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message.VideoMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message.VoiceMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

/**
* 客服消息处理工具类
*/
public class CustomMessageUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
    
    /**
     * 生成客服文本消息JOSN数据
     * 
     * @param textMessageRequest 发送文本请求客服消息对象
     * @return String 客服文本消息JOSN数据
     */
    public static String parseJosnTextMessageRequest(TextMessageRequest textMessageRequest, String kfAccount) {
    	String openId = textMessageRequest.getOpenId();
    	String content = textMessageRequest.getContent();
        content = content.replace("\"", "\\\"");
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}";
        if (kfAccount != null && kfAccount.length() > 0) {
        	jsonMsg += ",\"customservice\":{\"kf_account\": \"" + kfAccount + "\"}";
        }
        jsonMsg += "}";
        return String.format(jsonMsg, openId, content);
    }
    
    /**
     * 生成客服图片消息JOSN数据
     * 
     * @param imageMessageRequest 发送图片请求客服消息对象
     * @return String 客服图片消息JOSN数据
     */
    public static String parseJosnImageMessageRequest(ImageMessageRequest imageMessageRequest, String kfAccount) {
    	String openId = imageMessageRequest.getOpenId();
    	String mediaId = imageMessageRequest.getImage().getMediaId();
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}";
        if (kfAccount != null && kfAccount.length() > 0) {
        	jsonMsg += ",\"customservice\":{\"kf_account\": \"" + kfAccount + "\"}";
        }
        jsonMsg += "}";
        return String.format(jsonMsg, openId, mediaId);
    }
    
    /**
     * 生成客服语音消息JOSN数据
     * 
     * @param voiceMessageRequest 发送语音请求客服消息对象
     * @return String 客服语音消息JOSN数据
     */
    public static String parseJosnVoiceMessageRequest(VoiceMessageRequest voiceMessageRequest, String kfAccount) {
    	String openId = voiceMessageRequest.getOpenId();
    	String mediaId = voiceMessageRequest.getVoice().getMediaId();
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}";
        if (kfAccount != null && kfAccount.length() > 0) {
        	jsonMsg += ",\"customservice\":{\"kf_account\": \"" + kfAccount + "\"}";
        }
        jsonMsg += "}";
        return String.format(jsonMsg, openId, mediaId);
    }
    
    /**
     * 生成客服视频消息JOSN数据
     * 
     * @param videoMessageRequest 发送视频请求客服消息对象
     * @return String 客服视频消息JOSN数据
     */
    public static String parseJosnVideoMessageRequest(VideoMessageRequest videoMessageRequest, String kfAccount) {
    	String openId = videoMessageRequest.getOpenId();
    	String mediaId = videoMessageRequest.getMediaId();
    	String thumbMediaId = videoMessageRequest.getThumbMediaId();
    	String title = videoMessageRequest.getTitle();
    	String description = videoMessageRequest.getDescription();
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\",\"title\":\"%s\",\"description\":\"%s\"}";
        if (kfAccount != null && kfAccount.length() > 0) {
        	jsonMsg += ",\"customservice\":{\"kf_account\": \"" + kfAccount + "\"}";
        }
        jsonMsg += "}";
        return String.format(jsonMsg, openId, mediaId, thumbMediaId, title, description);
    }
    
    /**
     * 生成客服音乐消息JOSN数据
     * 
     * @param musicMessageRequest 发送音乐请求客服消息对象
     * @return String 客服音乐消息JOSN数据
     */
    public static String parseJosnMusicMessageRequest(MusicMessageRequest musicMessageRequest, String kfAccount) {
    	String openId = musicMessageRequest.getOpenId();
    	String title = musicMessageRequest.getTitle();
    	String description = musicMessageRequest.getDescription();
    	String musicUrl = musicMessageRequest.getMusicUrl();
    	String hqMusicUrl = musicMessageRequest.getHqMusicUrl();
    	String thumbMediaId = musicMessageRequest.getThumbMediaId();
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":{\"title\":\"%s\", \"description\":\"%s\", \"musicurl\":\"%s\", \"hqmusicurl\":\"%s\", \"thumb_media_id\":\"%s\"}";
        if (kfAccount != null && kfAccount.length() > 0) {
        	jsonMsg += ",\"customservice\":{\"kf_account\": \"" + kfAccount + "\"}";
        }
        jsonMsg += "}";
        jsonMsg = String.format(jsonMsg, openId, title, description, musicUrl, hqMusicUrl, thumbMediaId);
        return jsonMsg;
    }
    
    /**
     * 生成客服图文消息JOSN数据
     * 
     * @param newsMessageRequest 发送图文请求客服消息对象
     * @return String 客服图文消息JOSN数据
     */
    public static String parseJosnNewsMessageRequest(NewsMessageRequest newsMessageRequest, String kfAccount) {
    	String openId = newsMessageRequest.getOpenId();
    	List<Article> articleList = newsMessageRequest.getArticles();
    	String jsonMsg = "{\"touser\":\"%s\", \"msgtype\":\"news\", \"news\":{\"articles\": [";
    	String jsonTemp = "";
    	for (int i = 0; i < articleList.size(); i++) {
    		Article article = articleList.get(i);
    		jsonTemp += "{\"title\":\"%s\", \"description\":\"%s\", \"url\":\"%s\", \"picurl\":\"%s\"},";
    		jsonTemp = String.format(jsonMsg, openId, article.getTitle(), article.getDescription(), article.getUrl(), article.getPicUrl());
		}
    	if (jsonTemp.length() > 0) {
    		jsonTemp = jsonTemp.substring(0, jsonTemp.length()-1);
    	}
    	jsonMsg += jsonTemp + "]}";
        if (kfAccount != null && kfAccount.length() > 0) {
        	jsonMsg += ",\"customservice\":{\"kf_account\": \"" + kfAccount + "\"}";
        }
        jsonMsg += "}";
        return jsonMsg;
    }
    
    /**
     * 发送客服消息
     * 
     * @param jsonMsg 客服消息JOSN数据
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
    public static ResultResponse sendCustomMessage(String jsonMsg, Token token) throws Exception {
    	ResultResponse resultResponse = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_CUSTOM_MESSAGE_URL).replace("ACCESS_TOKEN", token.getAccessToken());
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
