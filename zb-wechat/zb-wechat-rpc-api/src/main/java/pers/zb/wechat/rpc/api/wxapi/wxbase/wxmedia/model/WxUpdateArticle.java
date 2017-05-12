package pers.zb.wechat.rpc.api.wxapi.wxbase.wxmedia.model;

import pers.zb.wechat.rpc.api.wxapi.wxsendmsg.model.WxArticle;

/**
 * 修改消息素材
 */
public class WxUpdateArticle {
    private String media_id;
    private int index;
    private WxArticle article = new WxArticle();

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public WxArticle getArticle() {
        return article;
    }

    public void setArticle(WxArticle article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "WxArticlesRequest [media_id=" + media_id + "index=" + index + "article=" + article + "]";
    }

}
