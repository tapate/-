package pers.zb.mq.rpc.service;

import java.net.SocketTimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;

import pers.zb.common.util.util.HttpClientUtil;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws ConnectTimeoutException,
            SocketTimeoutException, Exception {
        String json = "{\"serviceCode\":\"userquery\",\"sign\":\"123456789\",\"timestamp\":\"123456789\",\"mobile\":\"15901718151\"}";
        String url = "http://server.zhoubang85.com:6080/zb-ucenter-web/webservice/user/invoke";

        final String CONTENT_TYPE_TEXT_JSON = "text/json";
        DefaultHttpClient client = new DefaultHttpClient(
                new PoolingClientConnectionManager());


        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

        StringEntity se = new StringEntity(json);
        se.setContentType(CONTENT_TYPE_TEXT_JSON);

        httpPost.setEntity(se);

        CloseableHttpResponse response2 = null;

        response2 = client.execute(httpPost);
        HttpEntity entity2 = null;
        entity2 = response2.getEntity();
        String s2 = EntityUtils.toString(entity2, "UTF-8");
        System.out.println(s2);
    }
}
