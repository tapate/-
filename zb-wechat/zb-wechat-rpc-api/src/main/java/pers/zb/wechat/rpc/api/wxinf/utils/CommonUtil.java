package pers.zb.wechat.rpc.api.wxinf.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * 普通工具类
 * 
 */
public class CommonUtil {

    /**
     * 根据请求头中获取的内容类型判断文件扩展名
     * 
     * @param contentType
     * @return
     */
    public static String getFileExt(String contentType) {
        String fileExt = "";
        if ("image/jpeg".equals(contentType)) {
            fileExt = ".jpg";
        } else if ("audio/mpeg".equals(contentType)) {
            fileExt = ".mp3";
        } else if ("audio/amr".equals(contentType)) {
            fileExt = ".amr";
        } else if ("audio/mp4".equals(contentType)) {
            fileExt = ".mp4";
        } else if ("audio/mpeg4".equals(contentType)) {
            fileExt = ".mp4";
        }
        return fileExt;
    }

    /**
     * 将long类型的时间转换成标准时间格式
     * 
     * @param longTime
     *            long类型的时间
     * @param dateFormat
     *            日期格式（例如：yyyy-MM-dd HH:mm:ss）
     * @return String
     */
    public static String formatTime(long longTime, String dateFormat) {
        DateFormat formater = new SimpleDateFormat(dateFormat);
        return formater.format(new Date(longTime));
    }

    /**
     * 判断客户端是否微信浏览器
     * 
     * @param request
     *            HttpServletRequest对象
     * @return boolean true：是，false：否
     */
    public static boolean isMicroMessenger(HttpServletRequest request) {
        boolean result = false;
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("MicroMessenger")) {
            result = true;
        }
        return result;
    }
}
