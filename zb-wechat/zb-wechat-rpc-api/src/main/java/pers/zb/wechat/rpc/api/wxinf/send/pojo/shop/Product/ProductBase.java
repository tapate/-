package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product;

import java.io.Serializable;
import java.util.IdentityHashMap;

/**
 * 商品基础类
 */
public class ProductBase implements Serializable {

    private static final long serialVersionUID = -5768227329050279325L;

    // 商品分类id，商品分类列表请通过《获取指定分类的所有子分类》获取
    private String[] category_id;

    // 商品属性列表，属性列表请通过《获取指定分类的所有属性》获取
    private ProductBaseProperty[] property;

    // 商品名称
    private String name;

    // 商品sku定义，SKU列表请通过《获取指定子分类的所有SKU》获取
    private ProductBaseSku[] sku_info;

    // 商品主图(图片需调用图片上传接口获得图片Url填写至此，否则无法添加商品。图片分辨率推荐尺寸为640×600)
    private String main_img;

    // 商品图片列表(图片需调用图片上传接口获得图片Url填写至此，否则无法添加商品。图片分辨率推荐尺寸为640×600)
    private String[] img;

    // 商品详情列表，显示在客户端的商品详情页内（key=text，img）
    private IdentityHashMap<String, String> detail;

    // 用户商品限购数量
    private String buy_limit;

    public String[] getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String[] category_id) {
        this.category_id = category_id;
    }

    public ProductBaseProperty[] getProperty() {
        return property;
    }

    public void setProperty(ProductBaseProperty[] property) {
        this.property = property;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductBaseSku[] getSku_info() {
        return sku_info;
    }

    public void setSku_info(ProductBaseSku[] sku_info) {
        this.sku_info = sku_info;
    }

    public String getMain_img() {
        return main_img;
    }

    public void setMain_img(String main_img) {
        this.main_img = main_img;
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }

    public IdentityHashMap<String, String> getDetail() {
        return detail;
    }

    public void setDetail(IdentityHashMap<String, String> detail) {
        this.detail = detail;
    }

    public String getBuy_limit() {
        return buy_limit;
    }

    public void setBuy_limit(String buy_limit) {
        this.buy_limit = buy_limit;
    }
}
