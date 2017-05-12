package pers.zb.wechat.rpc.api.wxinf.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlUtil {

    public static String urlEncoder(String url, String charset)
            throws Exception {
        return URLEncoder.encode(url, charset);
    }

    public static String urlDecoder(String url, String charset)
            throws Exception {
        return URLDecoder.decode(url, charset);
    }

    // 测试主函数
    public static void main(String args[]) {
        String s = new String("原始MD5后加密的解密的");
        String charset = "UTF-8";
        System.out.println("原始：" + s);
        try {
            System.out.println("编码后：" + urlEncoder(s, charset));
            System.out.println("解码的："
                    + urlDecoder(urlEncoder(s, charset), charset));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
