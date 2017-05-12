package pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message;

import java.io.Serializable;
import java.util.List;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Article;


/**
 * 图文响应消息类
 */
public class NewsMessageResponse extends BaseMessageResponse implements
        Serializable {

    private static final long serialVersionUID = -3233389404086851797L;

    // 图文消息个数，限制为10条以内
    private int ArticleCount;

    // 多条图文消息信息，默认第一个item为大图
    private List<Article> Articles;

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<Article> getArticles() {
        return Articles;
    }

    public void setArticles(List<Article> articles) {
        Articles = articles;
    }
}
