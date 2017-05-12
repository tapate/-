package pers.zb.wechat.rpc.api.wxinf.send.pojo.request.news;

import java.io.Serializable;
import java.util.List;

/**
* 上传图文消息请求类
*/
public class UploadArticleRequest implements Serializable {
    
    private static final long serialVersionUID = -6592323168620532808L;
    
    private List<UploadArticle> articles;
    
    public List<UploadArticle> getArticles() {
        return articles;
    }
    
    public void setArticles(List<UploadArticle> articles) {
        this.articles = articles;
    }
}
