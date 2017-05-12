package pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message;

import java.io.Serializable;

/**
 * 链接请求消息类
 */
public class LinkMessageRequest extends BaseMessageRequest implements
        Serializable {

    private static final long serialVersionUID = 8818820928752975539L;

    // 消息标题
    private String Title;

    // 消息描述
    private String Description;

    // 消息链接
    private String Url;

    public String getTitle() {
        return null == Title ? "" : Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return null == Description ? "" : Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return null == Url ? "" : Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
