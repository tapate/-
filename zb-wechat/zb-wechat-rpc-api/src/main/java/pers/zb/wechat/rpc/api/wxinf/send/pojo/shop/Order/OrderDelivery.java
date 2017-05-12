package pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Order;

import java.io.Serializable;

/**
 * 订单发货信息类
 */
public class OrderDelivery implements Serializable {
    
    private static final long serialVersionUID = 3981623408313571928L;
    
    // 订单ID
    private String order_id;
    
    // 物流公司ID(参考《物流公司ID》； 当need_delivery为0时，可不填本字段； 当need_delivery为1时，该字段不能为空； 当need_delivery为1且is_others为1时，本字段填写其它物流公司名称)
    /**
     * 邮政EMS	Fsearch_code
     * 申通快递	002shentong
     * 中通速递	066zhongtong
     * 圆通速递	056yuantong
     * 天天快递	042tiantian
     * 顺丰速运	003shunfeng
     * 韵达快运	059Yunda
     * 宅急送	064zhaijisong
     * 汇通快运	020huitong
     * 易迅快递	zj001yixun
     */
    private String delivery_company;
    
    // 运单ID(当need_delivery为0时，可不填本字段；当need_delivery为1时，该字段不能为空；)
    private String delivery_track_no;
    
    // 商品是否需要物流(0-不需要，1-需要，无该字段默认为需要物流)
    private String need_delivery;
    
    // 是否为6.4.5表之外的其它物流公司(0-否，1-是，无该字段默认为不是其它物流公司)
    private String is_others;
    
    public String getOrder_id() {
        return order_id;
    }
    
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
    
    public String getDelivery_company() {
        return delivery_company;
    }
    
    public void setDelivery_company(String delivery_company) {
        this.delivery_company = delivery_company;
    }
    
    public String getDelivery_track_no() {
        return delivery_track_no;
    }
    
    public void setDelivery_track_no(String delivery_track_no) {
        this.delivery_track_no = delivery_track_no;
    }
    
    public String getNeed_delivery() {
        return need_delivery;
    }
    
    public void setNeed_delivery(String need_delivery) {
        this.need_delivery = need_delivery;
    }
    
    public String getIs_others() {
        return is_others;
    }
    
    public void setIs_others(String is_others) {
        this.is_others = is_others;
    }
}
