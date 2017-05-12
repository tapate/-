package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架控件5分组列表类
 */
public class ModuleInfo5GroupInfos implements Serializable {
    
    private static final long serialVersionUID = -4039602341870120909L;
    
    // 货架控件5分组列表数组
    private ModuleInfo5GroupInfosGroup[] groups;
    
    // 货架控件5分组照片(图片需调用图片上传接口获得图片Url填写至此，否则添加货架失败，建议分辨率640*1008)
    private String img_background;
    
    public ModuleInfo5GroupInfosGroup[] getGroups() {
        return groups;
    }
    
    public void setGroups(ModuleInfo5GroupInfosGroup[] groups) {
        this.groups = groups;
    }
    
    public String getImg_background() {
        return img_background;
    }
    
    public void setImg_background(String img_background) {
        this.img_background = img_background;
    }
}
