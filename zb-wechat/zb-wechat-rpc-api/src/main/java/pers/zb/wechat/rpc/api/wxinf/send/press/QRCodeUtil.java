package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.qrcode.QRCode;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.UrlUtil;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

public class QRCodeUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
    
    /**
     * 创建临时带参数二维码信息类
     * 
     * @param qrCode 临时带参数二维码信息类
     * @param token 微信公众账号的Token对象
     * @return QRCode 临时带参数二维码信息类
     * @throws Exception
     */
    public static QRCode createTempQRCode(QRCode qrCode, Token token) throws Exception {
    	int expireSeconds = qrCode.getExpireSeconds();
    	if (expireSeconds <= 0) {
    		expireSeconds = 1800;
    	}
    	int sceneId = (int) qrCode.getSceneId();
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_TEMP_QRCODE_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, String.format(jsonMsg, expireSeconds, sceneId));
        if (null != jsonObject) {
            try {
                qrCode.setTicket(jsonObject.getString("ticket"));
                qrCode.setExpireSeconds(jsonObject.getInt("expire_seconds"));
            } catch(Exception e) {
            	qrCode.setErrCode(jsonObject.getString("errcode"));
            	qrCode.setErrMsg(jsonObject.getString("errmsg"));
            }
        }
        return qrCode;
    }
    
    /**
     * 创建永久带参数二维码
     * 
     * @param qrCode 带参数二维码信息类
     * @param token 微信公众账号的Token对象
     * @return QRCode 带参数二维码信息类
     * @throws Exception
     */
    public static QRCode createQRCode(QRCode qrCode, Token token) throws Exception {
    	String sceneId = String.valueOf(qrCode.getSceneId());
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_QRCODE_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"%s\"}}}";
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, String.format(jsonMsg, sceneId));
        if (null != jsonObject) {
            try {
                qrCode.setTicket(jsonObject.getString("ticket"));
            } catch(Exception e) {
            	qrCode.setErrCode(jsonObject.getString("errcode"));
            	qrCode.setErrMsg(jsonObject.getString("errmsg"));
            }
        }
        return qrCode;
    }
    
    /**
     * 通过ticket换取二维码图片文件，并保存到本地
     * 
     * @param ticket 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码
     * @param savePath 二维码图片本地保存路径
     * @return String 获取的二维码图片文件保存路径
     * @throws Exception
     */
    public static String getQRCodeFile(String ticket, String savePath) throws Exception {
        String filePath = "";
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_QRCODE_FILE_URL).replace("TICKET", UrlUtil.urlEncoder(ticket, "UTF-8"));
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            if (!savePath.endsWith("/")) {
                savePath += "/";
            }
            // 将ticket作为文件名
            filePath = savePath + ticket + ".jpg";
            
            // 将微信服务器返回的输入流写入文件
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            fos.close();
            bis.close();
            
            conn.disconnect();
        } catch(Exception e) {
            filePath = null;
        }
        return filePath;
    }
    
    /**
     * 长链接转短链接接口
     * 
     * @param longUrl 长链接
     * @param token 微信公众账号的Token对象
     * @return String 短链接
     * @throws Exception
     */
    public static String longToShort(String longUrl, Token token) throws Exception {
    	String shortUrl = "";
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_LONG_TO_SHORT_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"action\":\"long2short\",\"long_url\":\"%s\"}";
        jsonMsg = String.format(jsonMsg, longUrl);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        if (null != jsonObject) {
    		String errcode = jsonObject.getString("errcode");
    		String errmsg = jsonObject.getString("errmsg");
    		if ("0".equals(errcode) && "ok".equals(errmsg)) {
    			shortUrl = jsonObject.getString("short_url");
    		}
        }
        return shortUrl;
    }
}
