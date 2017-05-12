package pers.zb.wechat.rpc.api.wxinf.rece.press.utils;

import java.io.Writer;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Article;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Image;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Kf;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Music;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Video;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Voice;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.ImageMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.KfMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.MusicMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.NewsMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.TextMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.VideoMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.VoiceMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;


/**
* 微信响应消息处理工具类
*/
public class ResponseMessageUtil {
    
    /**
     * 创建响应文本消息对象
     * 
     * @param toUserName 粉丝OpenID
     * @param fromUserName 微信公众号
     * @param respContent 回复信息
     * @return TextMessageResponse 响应文本消息对象
     */
    public static TextMessageResponse getTextMessageResponse(String toUserName, String fromUserName, String respContent) {
        // 响应文本消息对象
        TextMessageResponse textMessageResponse = new TextMessageResponse();
        // 发送方帐号（一个OpenID）
        textMessageResponse.setToUserName(fromUserName);
        // 开发者微信号
        textMessageResponse.setFromUserName(toUserName);
        // 消息创建时间 （整型）
        textMessageResponse.setCreateTime(System.currentTimeMillis());
        // 消息类型text
        textMessageResponse.setMsgType(WXConstants.RSP_MESSAGE_TYPE_TEXT);
        // 回复的消息内容,长度不超过2048字节
        textMessageResponse.setContent(respContent);
        // 转为xml格式
        // respMessage = ResponseMessageUtil.textMessageToXml(textMessageResponse);
        // 返回回复信息
        return textMessageResponse;
    }
    
    /**
     * 创建响应图片消息对象
     * 
     * @param toUserName 粉丝openid
     * @param fromUserName 微信公众号
     * @param mediaId 图片媒体ID
     * @return ImageMessageResponse 响应图片消息对象
     */
    public static ImageMessageResponse getImageMessageResponse(String toUserName, String fromUserName, String mediaId) {
        // 响应图片消息对象
        ImageMessageResponse imageMessageResponse = new ImageMessageResponse();
        // 发送方帐号（一个OpenID）
        imageMessageResponse.setToUserName(fromUserName);
        // 开发者微信号
        imageMessageResponse.setFromUserName(toUserName);
        // 消息创建时间 （整型）
        imageMessageResponse.setCreateTime(System.currentTimeMillis());
        // 消息类型text
        imageMessageResponse.setMsgType(WXConstants.RSP_MESSAGE_TYPE_IMAGE);
        Image image = new Image();
        image.setMediaId(mediaId);
        imageMessageResponse.setImage(image);
        // 转为xml格式
        // respMessage = ResponseMessageUtil.imageMessageToXml(imageMessageResponse);
        // 返回回复信息
        return imageMessageResponse;
    }
    
    /**
     * 创建响应语音消息对象
     * 
     * @param toUserName 粉丝openid
     * @param fromUserName 微信公众号
     * @param mediaId 语音媒体ID
     * @return VoiceMessageResponse 响应语音消息对象
     */
    public static VoiceMessageResponse getVoiceMessageResponse(String toUserName, String fromUserName, String mediaId) {
        // 响应语音消息对象
        VoiceMessageResponse voiceMessageResponse = new VoiceMessageResponse();
        // 发送方帐号（一个OpenID）
        voiceMessageResponse.setToUserName(fromUserName);
        // 开发者微信号
        voiceMessageResponse.setFromUserName(toUserName);
        // 消息创建时间 （整型）
        voiceMessageResponse.setCreateTime(System.currentTimeMillis());
        // 消息类型text
        voiceMessageResponse.setMsgType(WXConstants.RSP_MESSAGE_TYPE_VOICE);
        Voice voice = new Voice();
        voice.setMediaId(mediaId);
        voiceMessageResponse.setVoice(voice);
        // 转为xml格式
        // respMessage = ResponseMessageUtil.voiceMessageToXml(voiceMessageResponse);
        // 返回回复信息
        return voiceMessageResponse;
    }
    
    /**
     * 创建响应视频消息对象
     * 
     * @param toUserName 粉丝openid
     * @param fromUserName 微信公众号
     * @param mediaId 视频媒体ID
     * @param thumbMediaId 视频缩略图媒体ID
     * @return VideoMessageResponse 响应视频消息对象
     */
    public static VideoMessageResponse getVideoMessageResponse(String toUserName, String fromUserName, String mediaId, String thumbMediaId) {
        // 响应视频消息对象
        VideoMessageResponse videoMessageResponse = new VideoMessageResponse();
        // 发送方帐号（一个OpenID）
        videoMessageResponse.setToUserName(fromUserName);
        // 开发者微信号
        videoMessageResponse.setFromUserName(toUserName);
        // 消息创建时间 （整型）
        videoMessageResponse.setCreateTime(System.currentTimeMillis());
        // 消息类型text
        videoMessageResponse.setMsgType(WXConstants.RSP_MESSAGE_TYPE_VIDEO);
        Video video = new Video();
        video.setMediaId(mediaId);
        video.setThumbMediaId(thumbMediaId);
        videoMessageResponse.setVideo(video);
        // 转为xml格式
        // respMessage = ResponseMessageUtil.videoMessageToXml(videoMessageResponse);
        // 返回回复信息
        return videoMessageResponse;
    }
    
    /**
     * 创建响应音乐消息对象
     * 
     * @param toUserName 粉丝openid
     * @param fromUserName 微信公众号
     * @param title 音乐标题
     * @param description 音乐描述
     * @param thumbMediaId 音乐缩略图媒体ID
     * @param musicUrl 音乐播放URL
     * @param hQMusicUrl 高清音乐播放URL
     * @return MusicMessageResponse 响应音乐消息对象
     */
    public static MusicMessageResponse getMusicMessageResponse(String toUserName, String fromUserName, String title, String description, String thumbMediaId, String musicUrl, String hQMusicUrl) {
        // 响应音乐消息对象
        MusicMessageResponse musicMessageResponse = new MusicMessageResponse();
        // 发送方帐号（一个OpenID）
        musicMessageResponse.setToUserName(fromUserName);
        // 开发者微信号
        musicMessageResponse.setFromUserName(toUserName);
        // 消息创建时间 （整型）
        musicMessageResponse.setCreateTime(System.currentTimeMillis());
        // 消息类型text
        musicMessageResponse.setMsgType(WXConstants.RSP_MESSAGE_TYPE_MUSIC);
        Music music = new Music();
        music.setTitle(title);
        music.setDescription(description);
        music.setThumbMediaId(thumbMediaId);
        music.setMusicUrl(musicUrl);
        music.setHQMusicUrl(hQMusicUrl);
        musicMessageResponse.setMusic(music);
        // 转为xml格式
        // respMessage = ResponseMessageUtil.musicMessageToXml(musicMessageResponse);
        // 返回回复信息
        return musicMessageResponse;
    }
    
    /**
     * 创建图文对象
     * 
     * @param title 标题
     * @param description 描述
     * @param picUrl 图片URL
     * @param url 链接URL
     * @return Article 图文对象
     */
    public static Article getArticle(String title, String description, String picUrl, String url) {
        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setPicUrl(picUrl);
        article.setUrl(url);
        return article;
    }
    
    /**
     * 创建响应图文消息对象
     * 
     * @param toUserName 粉丝openid
     * @param fromUserName 微信公众号
     * @param articles 图文对象集合
     * @return NewsMessageResponse 响应图文消息对象
     */
    public static NewsMessageResponse getNewsMessageResponse(String toUserName, String fromUserName, List<Article> articles) {
        // 响应图文消息对象
        NewsMessageResponse newsMessageResponse = new NewsMessageResponse();
        // 发送方帐号（一个OpenID）
        newsMessageResponse.setToUserName(fromUserName);
        // 开发者微信号
        newsMessageResponse.setFromUserName(toUserName);
        // 消息创建时间 （整型）
        newsMessageResponse.setCreateTime(System.currentTimeMillis());
        // 消息类型text
        newsMessageResponse.setMsgType(WXConstants.RSP_MESSAGE_TYPE_NEWS);
        newsMessageResponse.setArticleCount(articles.size());
        newsMessageResponse.setArticles(articles);
        // 转为xml格式
        // respMessage = ResponseMessageUtil.newsMessageToXml(newsMessageResponse);
        // 返回回复信息
        return newsMessageResponse;
    }
    
    /**
     * 响应文本消息对象转换成XML
     * 
     * @param textMessageResponse 文本消息对象
     * @return String 文本消息xml
     */
    public static String parseTextMessageResponseToXml(TextMessageResponse textMessageResponse) {
        xstream.alias("xml", textMessageResponse.getClass());
        return xstream.toXML(textMessageResponse);
    }
    
    /**
     * 响应图片消息对象转换成XML
     * 
     * @param imageMessageResponse 图片消息对象
     * @return String 图片消息xml
     */
    public static String parseImageMessageResponseToXml(ImageMessageResponse imageMessageResponse) {
        xstream.alias("xml", imageMessageResponse.getClass());
        return xstream.toXML(imageMessageResponse);
    }
    
    /**
     * 响应语音消息对象转换成XML
     * 
     * @param voiceMessageResponse 语音消息对象
     * @return String 语音消息xml
     */
    public static String parseVoiceMessageResponseToXml(VoiceMessageResponse voiceMessageResponse) {
        xstream.alias("xml", voiceMessageResponse.getClass());
        return xstream.toXML(voiceMessageResponse);
    }
    
    /**
     * 响应视频消息对象转换成XML
     * 
     * @param videoMessageResponse 视频消息对象
     * @return String 视频消息xml
     */
    public static String parseVideoMessageResponseToXml(VideoMessageResponse videoMessageResponse) {
        xstream.alias("xml", videoMessageResponse.getClass());
        return xstream.toXML(videoMessageResponse);
    }
    
    /**
     * 响应音乐消息对象转换成XML
     * 
     * @param musicMessageResponse 音乐消息对象
     * @return String 音乐消息xml
     */
    public static String parseMusicMessageResponseToXml(MusicMessageResponse musicMessageResponse) {
        xstream.alias("xml", musicMessageResponse.getClass());
        return xstream.toXML(musicMessageResponse);
    }
    
    /**
     * 响应图文消息对象转换成XML
     * 
     * @param newsMessageResponse 图文消息对象
     * @return String 图文消息xml
     */
    public static String parseNewsMessageResponseToXml(NewsMessageResponse newsMessageResponse) {
        xstream.alias("xml", newsMessageResponse.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessageResponse);
    }
    
    /**
     * 响应转发客服消息对象转换成XML
     * 
     * @param kfMessageResponse 转发客服消息对象
     * @return String 客服消息xml
     */
    public static String parseKfMessageResponseToXml(KfMessageResponse kfMessageResponse) {
        xstream.alias("xml", kfMessageResponse.getClass());
        if (kfMessageResponse.getTransInfo() != null && kfMessageResponse.getTransInfo().size() > 0) {
        	xstream.alias("TransInfo", new Kf().getClass());
        }
        return xstream.toXML(kfMessageResponse);
    }
    
    /**
     * 扩展xstream，使其支持CDATA块
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记 
                boolean cdata = true;
                @SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
