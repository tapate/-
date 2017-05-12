package pers.zb.wechat.rpc.api.wxinf.rece.press.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
* 微信请求消息处理工具类
*/
public class RequestMessageUtil {
    
    /**
     * 解析微信发来的请求（XML）
     * 
     * @param request 微信服务器提交XML数据的Request对象
     * @return Map<String, String> XML解析后的数据
     * @throws Exception
     */
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        @SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }
    
    /**
     * 解析XML文件
     * 
     * @param configurationFile 本地XML文件访问路径
     * @return Map<String, String> XML解析后的数据
     * @throws Exception
     */
    public static Map<String, String> parseXml(String configurationFile) throws Exception {
    	FileInputStream fileInputStream = new FileInputStream(new File(configurationFile));
    	// 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(fileInputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        @SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }
        // 释放资源
        fileInputStream.close();
        fileInputStream = null;
        return map;
    }
}
