package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Shelf;

import java.io.Serializable;

/**
 * 货架类
 */
public class Shelf implements Serializable {
    
    private static final long serialVersionUID = -7329483629479477791L;
    
    // 货架ID
    private String shelf_id;
    
    // 货架名称
    private String shelf_name;
    
    // 货架招牌图片Url(图片需调用图片上传接口获得图片Url填写至此，否则添加货架失败，建议尺寸为640*120，仅控件1-4有banner，控件5没有banner)
    private String shelf_banner;
    
    // 货架信息对象
    private ShelfData shelf_data;
    
    public String getShelf_id() {
        return shelf_id;
    }
    
    public void setShelf_id(String shelf_id) {
        this.shelf_id = shelf_id;
    }
    
    public String getShelf_name() {
        return shelf_name;
    }
    
    public void setShelf_name(String shelf_name) {
        this.shelf_name = shelf_name;
    }
    
    public String getShelf_banner() {
        return shelf_banner;
    }
    
    public void setShelf_banner(String shelf_banner) {
        this.shelf_banner = shelf_banner;
    }
    
    public ShelfData getShelf_data() {
        return shelf_data;
    }
    
    public void setShelf_data(ShelfData shelf_data) {
        this.shelf_data = shelf_data;
    }
}
