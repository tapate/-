package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件4分组信息类
 */
public class ModuleInfo4GroupInfosGroups implements Serializable {
    
    private static final long serialVersionUID = -2772470855048594643L;
    
    // 货架控件4分组ID
    private String group_id;
    
    // 货架控件4分组照片(图片需调用图片上传接口获得图片Url填写至此，否则添加货架失败，3个分组建议分辨率分别为: 350*350, 244*172, 244*172)
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
