package pers.zb.wechat.rpc.api.wxinf.send.pojo.request.message;

import java.io.Serializable;
import java.util.List;

/**
* 发送图文请求客服消息类
*/
public class NewsMessageRequest implements Serializable {
    
    private static final long serialVersionUID = 336521102428566280L;
    
    // 接收用户的OpenId
    private String openId;
    
    // articleList 图文对象列表
    private List<Article> articles;
    
    public String getOpenId() {
        return openId;
    }
    
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public List<Article> getArticles() {
        return articles;
    }
    
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
