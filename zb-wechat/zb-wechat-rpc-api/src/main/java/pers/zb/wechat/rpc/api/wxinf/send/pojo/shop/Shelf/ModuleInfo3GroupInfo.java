package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件3分组信息
 */
public class ModuleInfo3GroupInfo implements Serializable {
    
    private static final long serialVersionUID = -2785581622037191676L;
    
    // 货架控件3分组ID
    private String group_id;
    
    // 货架控件3分组照片(图片需调用图片上传接口获得图片Url填写至此，否则添加货架失败，建议分辨率600*208)
    private String img;
    
    public String getGroup_id() {
        return group_id;
    }
    
    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
    
    public String getImg() {
        return img;
    }
    
    public void setImg(String img) {
        this.img = img;
    }
}
