package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.media.Media;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.utils.CommonUtil;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;


/**
 * 微信媒体上传下载工具类
 */
public class MediaUtil {

    private static ConfigManager configManager = ConfigManager.getInstance();
    
    /**
     * 上传媒体文件
     * @param token 微信接口访问凭证
     * @param type 媒体文件类型（image、voice、video、thumb）
     * @param mediaFilePath 需要上传媒体文件的Url
     * @return Media 微信媒体文件信息对象
     */
    public static Media uploadMedia(Token token, String type, String mediaFilePath) {
        Media media = null;
        // 拼装媒体文件上传请求地址
        String uploadMediaUrl = configManager.getConfigValue(Constants.WX_POST_UPLOAD_MEDIA_URL).replace("ACCESS_TOKEN", token.getAccessToken()).replace("TYPE", type);
        // 定义数据分隔符
        String boundary = "------------7da2e536604c8";
        try {
            URL uploadUrl = new URL(uploadMediaUrl);
            HttpURLConnection uploadConn = (HttpURLConnection)uploadUrl.openConnection();
            uploadConn.setDoOutput(true);
            uploadConn.setDoInput(true);
            uploadConn.setRequestMethod(WXConstants.REQ_METHOD_TYPE_POST);
            // 设置请求头Content-Type
            uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
            // 获取媒体文件上传的输出流（往微信服务器写数据）
            OutputStream outputStream = uploadConn.getOutputStream();
            
            URL mediaUrl = new URL(mediaFilePath);
            HttpURLConnection mediaConn = (HttpURLConnection)mediaUrl.openConnection();
            mediaConn.setDoOutput(true);
            mediaConn.setRequestMethod(WXConstants.REQ_METHOD_TYPE_GET);
            // 从请求头中获取内容类型
            String contentType = mediaConn.getHeaderField("Content-Type");
            // 根据内容类型判断文件扩展名
            String fileExt = CommonUtil.getFileExt(contentType);
            // 请求题开始
            outputStream.write(("--" + boundary + "\r\n").getBytes());
            outputStream.write(String.format("Content-Disposition: form-data; name=\"media\";filename=\"file1%s\"\r\n", fileExt).getBytes());
            outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());
            
            // 获取媒体文件的输入流（读取文件）
            BufferedInputStream bis = new BufferedInputStream(mediaConn.getInputStream());
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1) {
                outputStream.write(buf, 0, size);
            }
            // 请求体结束
            outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
            outputStream.close();
            bis.close();
            mediaConn.disconnect();
            
            // 获取媒体文件上传的输入流（从微信服务器读数据）
            InputStream inputStream = uploadConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            uploadConn.disconnect();
            
            // 解析返回解结果
            JSONObject jsonObject = JSONObject.fromObject(stringBuffer.toString());
            System.out.println(jsonObject);
            media = new Media();
            media.setType(jsonObject.getString("type"));
            // type等于thumb时的返回结果和其它类型不一样
            if ("thumb".equals(type)) {
                media.setMediaId(jsonObject.getString("thumb_media_id"));
            } else {
                media.setMediaId(jsonObject.getString("media_id"));
                media.setCreatedAt(jsonObject.getString("created_at"));
            }
        } catch (Exception e) {
            media = null;
            e.printStackTrace();
        }
        return media;
    }
    
    public static String getMedia(Token token, String mediaId, String savePath) {
        String filePath = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_FIND_MENU_URL).replace("ACCESS_TOKEN", token.getAccessToken().replace("MEDIA_ID", mediaId));
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod(WXConstants.REQ_METHOD_TYPE_GET);
            
            if (!savePath.endsWith("/")) {
                savePath += "/";
            }
            // 根据内容类型获取扩展名
            String fileExt = CommonUtil.getFileExt(conn.getHeaderField("Content-Type"));
            // 将mediaId作为文件名
            filePath = savePath + mediaId + fileExt;
            
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
        } catch (Exception e) {
            filePath = null;
            e.printStackTrace();
        }
        return filePath;
    }
}
