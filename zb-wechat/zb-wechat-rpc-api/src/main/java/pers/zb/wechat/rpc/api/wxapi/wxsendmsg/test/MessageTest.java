package pers.zb.wechat.rpc.api.wxapi.wxsendmsg.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import pers.zb.wechat.rpc.api.wxapi.core.common.AccessToken;
import pers.zb.wechat.rpc.api.wxapi.core.exception.WexinReqException;
import pers.zb.wechat.rpc.api.wxapi.wxsendmsg.JwSendMessageAPI;
import pers.zb.wechat.rpc.api.wxapi.wxsendmsg.model.WxArticle;
import pers.zb.wechat.rpc.api.wxapi.wxsendmsg.model.WxArticlesResponse;
import pers.zb.wechat.rpc.api.wxapi.wxsendmsg.model.WxMediaResponse;

/**
 * 请使用认证的服务号测试
 */
public class MessageTest {
    private static String newAccessToken = null;
    private static String touser = null;

    /**
     * 测试获取token
     */
    // @Ignore
    @Before
    public void getToken() throws WexinReqException {
        touser = "oGZ33t1rC5_HF5tIFe7o16EuU4NE";
        String appId = "wxf06b5852ed923210";
        String appSecret = "c98c3487158d8432916b09495888b440";
        AccessToken token = new AccessToken(appId, appSecret);

        String strtoken = token.getNewAccessToken();
        newAccessToken = strtoken;
        System.out.println(strtoken);

    }

    // 文本预览
    @Test
    public void testmessagePrivateUsedText() {
        JwSendMessageAPI service = new JwSendMessageAPI();
        try {
            String r = service.messagePrivate(newAccessToken, touser, "我要预览啊");
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 上传资源
    // @Ignore
    @Test
    public void testuploadmedia() {

        JwSendMessageAPI service = new JwSendMessageAPI();
        try {
            WxMediaResponse r = service.uploadMediaFile(newAccessToken, "F:\\img\\", "showqrcode.jpg", "image");
            System.out.println(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 上传素材
    @Ignore
    @Test
    public void testuploadnews() {

        JwSendMessageAPI service = new JwSendMessageAPI();
        try {
            List<WxArticle> wxArticles = new ArrayList<WxArticle>();
            for (int i = 0; i < 4; i++) {
                WxArticle article = new WxArticle();
                article.setAuthor("author" + i);
                article.setContent("Content" + i);
                article.setDigest("Digest" + i);
                article.setShow_cover_pic("1");
                article.setTitle("title" + i);
                article.setFileName("test.jpg");
                article.setFilePath("F:\\");
                wxArticles.add(article);
            }

            WxArticlesResponse result = service.uploadArticles(newAccessToken, wxArticles);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 图文消息预览
    @Test
    public void testmessagePrivateUsedNews() {

        JwSendMessageAPI service = new JwSendMessageAPI();
        try {
            List<WxArticle> wxArticles = new ArrayList<WxArticle>();
            for (int i = 0; i < 4; i++) {
                WxArticle article = new WxArticle();
                article.setAuthor("author" + i);
                article.setContent("Content" + i);
                article.setDigest("Digest" + i);
                article.setShow_cover_pic("1");
                article.setTitle("title" + i);
                article.setFileName("showqrcode.jpg");
                article.setFilePath("F:\\img\\");
                wxArticles.add(article);
            }
            service.messagePrivate(newAccessToken, touser, wxArticles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
