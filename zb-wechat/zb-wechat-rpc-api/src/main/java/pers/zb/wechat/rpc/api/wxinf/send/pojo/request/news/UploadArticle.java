package pers.zb.wechat.rpc.api.wxinf.send.pojo.request.news;

import java.io.Serializable;

/**
* 上传图文ARTICLE类
*/
public class UploadArticle implements Serializable {
    
    private static final long serialVersionUID = 8233985810375834488L;
    
    // 图文消息缩略图的media_id，可以在上传多媒体文件接口中获得
    private String thumbMediaId;
    
    // 图文消息的作者，可为空
    private String author;
    
    // 图文消息的标题
    private String title;
    
    // 在图文消息页面点击“阅读原文”后的页面，可为空
    private String contentSourceUrl;
    
    // 图文消息页面的内容，支持HTML标签
    private String content;
    
    // 图文消息的描述，可为空
    private String digest;
    
	public String getThumbMediaId() {
        return null == thumbMediaId ? "" : thumbMediaId;
	}
    
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
    
	public String getAuthor() {
        return null == author ? "" : author;
	}
    
	public void setAuthor(String author) {
		this.author = author;
	}
    
	public String getTitle() {
        return null == title ? "" : title;
	}
    
	public void setTitle(String title) {
		this.title = title;
	}
    
	public String getContentSourceUrl() {
        return null == contentSourceUrl ? "" : contentSourceUrl;
	}
    
	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
	}
    
	public String getContent() {
        return null == content ? "" : content;
	}
    
	public void setContent(String content) {
		this.content = content;
	}
    
	public String getDigest() {
        return null == digest ? "" : digest;
	}
    
	public void setDigest(String digest) {
		this.digest = digest;
	}
}
