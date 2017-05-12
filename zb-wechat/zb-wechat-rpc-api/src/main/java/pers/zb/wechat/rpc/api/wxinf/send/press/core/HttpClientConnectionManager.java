package pers.zb.wechat.rpc.api.wxinf.send.press.core;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;


/**
* Http客户端请求管理类
*/
@SuppressWarnings("deprecation")
public class HttpClientConnectionManager {
    
    public static HttpClient httpClient;
    
    /**
     * 获取SSL验证的HttpClient
     * 
     * @return HttpClient Http客户端请求对象
     */
    public static HttpClient getSSLInstance() {
        if (httpClient == null) {
            httpClient = new DefaultHttpClient();
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", MySSLSocketFactory.getInstance(), 443));
            httpClient =  new DefaultHttpClient(ccm, httpClient.getParams());
        }
        return httpClient;
    }
    
    /**
     * 模拟浏览器post提交 
     * @param url
     * @return HttpPost Http的Post请求对象
     */
    private static HttpPost getPostMethod(String url) {
        HttpPost pmethod = new HttpPost(url);
        // 设置响应头信息
        pmethod.addHeader("Connection", "keep-alive");
        pmethod.addHeader("Accept", "*/*");
        pmethod.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        pmethod.addHeader("Host", "mp.weixin.qq.com");
        pmethod.addHeader("X-Requested-With", "XMLHttpRequest");
        pmethod.addHeader("Cache-Control", "max-age=0");
        pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        return pmethod;
    }
    
    /**
     * 模拟浏览器GET提交
     * @param url
     * @return HttpGet Http的Get请求对象
     */
    private static HttpGet getGetMethod(String url) {
        HttpGet pmethod = new HttpGet(url);
        // 设置响应头信息
        pmethod.addHeader("Connection", "keep-alive");
        pmethod.addHeader("Cache-Control", "max-age=0");
        pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
        pmethod.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/;q=0.8");
        return pmethod;
    }
    
    /**
     * 发送Http请求
     * 
     * @param requestUrl Http请求的URL地址
     * @param requestMethod Http请求的方法类型（POST, GET）
     * @param josnData Http请求提交的数据
     * @return JSONObject 请求返回的Json数据对象
     * @throws Exception
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String josnData) throws Exception {
    	if (httpClient == null) {
    		getSSLInstance();
    	}
        String jsonStr = "";
        if (WXConstants.REQ_METHOD_TYPE_GET.equals(requestMethod.trim().toUpperCase())) {
            HttpGet httpGet = getGetMethod(requestUrl);
            HttpResponse response = httpClient.execute(httpGet);
            jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
        } else if (WXConstants.REQ_METHOD_TYPE_POST.equals(requestMethod.trim().toUpperCase())) {
            HttpPost httpPost = getPostMethod(requestUrl);
            httpPost.setEntity(new StringEntity(josnData, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return JSONObject.fromObject(jsonStr);
    }
}
