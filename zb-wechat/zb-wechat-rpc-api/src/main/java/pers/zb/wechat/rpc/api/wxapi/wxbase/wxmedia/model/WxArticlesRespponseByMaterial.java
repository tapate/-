package pers.zb.wechat.rpc.api.wxapi.wxbase.wxmedia.model;

import java.util.ArrayList;
import java.util.List;

import pers.zb.wechat.rpc.api.wxapi.wxsendmsg.model.WxArticle;

/**
 * 获得媒体信息
 *
 */
public class WxArticlesRespponseByMaterial {
    List<WxArticle> news_item = new ArrayList<WxArticle>();

    public List<WxArticle> getNews_item() {
        return news_item;
    }

    public void setNews_item(List<WxArticle> news_item) {
        this.news_item = news_item;
    }

    @Override
    public String toString() {
        return "WxArticlesRequest [news_item=" + news_item + "]";
    }

}
