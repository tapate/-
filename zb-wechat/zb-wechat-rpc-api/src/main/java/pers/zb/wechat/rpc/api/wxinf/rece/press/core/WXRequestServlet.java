package pers.zb.wechat.rpc.api.wxinf.rece.press.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.zb.wechat.rpc.api.wxinf.rece.press.core.service.WXRequestService;
import pers.zb.wechat.rpc.api.wxinf.rece.press.utils.RequestMessageUtil;
import pers.zb.wechat.rpc.api.wxinf.rece.press.utils.SignUtil;

/**
* 请求处理的核心类
*/
public class WXRequestServlet extends HttpServlet {
    
    private static final long serialVersionUID = -4970205360971185911L;
    
    private static ServletConfig classPathConfig;
    
    private static Map<String, String> map;
    
    public void init(ServletConfig config) throws ServletException {
        WXRequestServlet.classPathConfig = config;
        try {
            map = RequestMessageUtil.parseXml(classPathConfig.getServletContext().getRealPath(classPathConfig.getInitParameter("classPathConfig")));
            WXRequestService.setMap(map);
            // WXRequestAuthService.setMap(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        init();
    }
    
    /**
     * 请求校验（确认请求来自微信服务器）
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");
        
        PrintWriter out = response.getWriter();
        // 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }
    
    /**
     * 请求校验与处理
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
        
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        
        PrintWriter out = response.getWriter();
        // 请求校验
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            // 调用核心服务类接收处理请求
            String respXml = WXRequestService.processRequest(request);
            // String respXml = WXRequestAuthService.processRequest(request);
            out.print(respXml);
        }
        out.close();
        out = null;
    }
}
