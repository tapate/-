package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.utils.CommonUtil;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

/**
 * 微信小店功能处理工具类
 */
public class ShopFunctionUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
	
    /**
     * 上传图片
     * 
     * @param imgFilePath 上传图片文件路径
     * @param token 微信公众账号的Token对象
     * @return String 图片Url
     * @throws Exception
     */
    public static String uploadImg(String imgFilePath, Token token) throws Exception {
    	File tempFile =new File(imgFilePath.trim());
    	String imgFileName = tempFile.getName();
    	String imgUrl = "";
    	String uploadImgUrl = configManager.getConfigValue(Constants.WX_POST_UPLOAD_IMG_URL).replace("ACCESS_TOKEN", token.getAccessToken()).replace("FILE_NAME", imgFileName);
    	String boundary = "------------7da2e536604c8";
    	try {
            URL uploadUrl = new URL(uploadImgUrl);
            HttpURLConnection uploadConn = (HttpURLConnection)uploadUrl.openConnection();
            uploadConn.setDoOutput(true);
            uploadConn.setDoInput(true);
            uploadConn.setRequestMethod(WXConstants.REQ_METHOD_TYPE_POST);
            // 设置请求头Content-Type
            uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
            // 获取媒体文件上传的输出流（往微信服务器写数据）
            OutputStream outputStream = uploadConn.getOutputStream();
            
            URL mediaUrl = new URL(imgFilePath);
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
            if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			imgUrl = jsonObject.getString("image_url");
        		}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrl;
    }
}
