package pers.zb.wechat.rpc.api.wxinf.utils;

public class Constants {
    
    public final static String CONFIG_FILE = "/properties/wxinf.properties";
    
    /**
     * WEIXIN_URL CONFIG
     */
    
    // 获取接口访问凭证URL
    public final static String WX_TOKEN_APPID = "TokenAppid";
    // 获取接口访问凭证URL
    public final static String WX_TOKEN_SECRET = "TokenSecret";
    // 获取接口访问凭证URL
    public final static String WX_GET_ACCESS_TOKEN_URL = "GetAccessTokenUrl";
    
    // 获取微信服务器IP地址URL
    public final static String WX_GET_CALL_BACK_IP_URL = "GetCallBackIpUrl";
    
    // 创建菜单URL
    public final static String WX_POST_CREATE_MENU_URL = "PostCreateMenuUrl";
    // 查询菜单URL
    public final static String WX_GET_FIND_MENU_URL = "GetFindMenuUrl";
    // 删除菜单URL
    public final static String WX_GET_DEL_MENU_URL = "GetDelMenuUrl";

    // 发送客服消息URL
    public final static String WX_POST_CUSTOM_MESSAGE_URL = "PostSendMessageUrl";
    
    // 获取网页授权凭证URL
    public final static String WX_GET_OAUTH2_ACCESS_TOKEN_URL  = "GetOauth2AccessTokenUrl";
    // 刷新网页授权凭证URL
    public final static String WX_GET_REFERSH_OAUTH2_ACCESS_TOKEN_URL  = "GetRefershOauth2AccessTokenUrl";
    // 通过网页授权凭证获取用户信息URL
    public final static String WX_GET_OAUTH2_USER_INFO_URL  = "GetOauth2UserInfoUrl";
    
    // 获取临时带参数二维码URL
    public final static String WX_POST_TEMP_QRCODE_URL  = "PostTempQRCodeUrl";
    // 获取永久带参数二维码URL
    public final static String WX_POST_QRCODE_URL  = "PostQRCodeUrl";
    // 通过ticket换取二维码URL
    public final static String WX_GET_QRCODE_FILE_URL  = "GetQRCodeFileUrl";
    // 长链接转短链接接口URL
    public final static String WX_POST_LONG_TO_SHORT_URL  = "PostLongToShortUrl";
    
    // 获取微信用户信息URL
    public final static String WX_GET_USER_INFO_URL  = "GetUserInfoUrl";
    // 获取微信关注用户列表URL
    public final static String WX_GET_USER_LIST_URL  = "GetUserListUrl";
    // 获取微信分组列表URL
    public final static String WX_GET_GROUP_LIST_URL  = "GetGroupListUrl";
    // 创建用户分组URL
    public final static String WX_POST_CREATE_GROUP_URL  = "PostCreateGroupUrl";
    // 修改用户分组名URL
    public final static String WX_POST_UPDATE_GROUP_NAME_URL  = "PostUpdateGroupNameUrl";
    // 查询用户所在分组URL
    public final static String WX_POST_FIND_USER_GROUP_URL  = "PostFindUserGroupUrl";
    // 移动用户分组URL
    public final static String WX_POST_MOVE_USER_GROUP_URL  = "PostMoveUserGroupUrl";
    // 获取用户增减数据URL
    public final static String WX_POST_USER_SUMMARY_URL  = "PostUserSummaryUrl";
    // 获取累计用户数据URL
    public final static String WX_POST_USER_CUMULATE_URL  = "PostUserCumulateUrl";
    // 设置备注名URL
    public final static String WX_POST_UPDATE_REMARK_URL  = "PostUpdateRemarkUrl";
    
    // 上传图文消息素材URL
    public final static String WX_POST_UPLOAD_NEWS_URL  = "PostUploadNewsUrl";
    // 根据分组群发图文消息URL
    public final static String WX_POST_SEND_GROUP_NEWS_URL  = "PostSendGroupNewsUrl";
    // 根据OpenID列表群发图文消息URL
    public final static String WX_POST_SEND_USERS_NEWS_URL  = "PostSendUsersNewsUrl";
    // 删除指定群发图文消息URL
    public final static String WX_POST_DELETE_NEWS_URL  = "PostDeleteNewsUrl";
    
    // 媒体文件上传请求地址URL
    public final static String WX_POST_UPLOAD_MEDIA_URL  = "PostUploadMediaUrl";
    // 媒体文件下载请求地址URL
    public final static String WX_GET_DOWNLOAD_MEDIA_URL  = "GetDownloadMediaUrl";
    
    // 网页授权地址URL
    public final static String WX_OAUTH2TOKEN_URL  = "Oauth2TokenUrl";
    
    
    // 百度地图圆形区域搜索URL
    public final static String DB_GET_SEARCH_CIRCULAR_PLACE_URL  = "GetSearchCircularPlaceUrl";
    // 百度坐标转换URL
    public final static String DB_GET_CONVERT_COORD_URL  = "GetConvertCoordUrl";
    
    
    /**
     * 微信小店接口
     */
    // 获取微信商品分类列表URL
    public final static String WX_POST_CATE_LIST_URL = "PostCateListUrl";
    // 获取指定商品分类的所有SKU列表URL
    public final static String WX_POST_CATE_SKU_LIST_URL = "PostCateSkuListUrl";
    // 获取指定商品分类的所有属性列表URL
    public final static String WX_POST_CATE_PROPERTY_LIST_URL = "PostCatePropertyListUrl";
    // 增加商品URL
    public final static String WX_POST_CREATE_PRODUCT_URL = "PostCreateProductUrl";
    // 删除商品URL
    public final static String WX_POST_DELETE_PRODUCT_URL = "PostDeleteProductUrl";
    // 修改商品URL
    public final static String WX_POST_UPDATE_PRODUCT_URL = "PostUpdateProductUrl";
    // 查询商品URL
    public final static String WX_POST_GET_PRODUCT_URL = "PostGetProductUrl";
    // 获取指定状态的所有商品URL
    public final static String WX_POST_FIND_PRODUCT_BY_STATUS_URL = "PostFindProductByStatusUrl";
    // 商品上下架URL
    public final static String WX_POST_MOD_PRODUCT_STATUS_URL = "PostModProductStatusUrl";
    
    // 增加库存URL
    public final static String WX_POST_ADD_INVENTORY_URL = "PostAddInventoryUrl";
    // 减少库存URL
    public final static String WX_POST_REDUCE_INVENTORY_URL = "PostReduceInventoryUrl";
    
    // 增加邮费模版URL
    public final static String WX_POST_ADD_DELIVERY_TEMPLATE_URL = "PostAddDeliveryTemplateUrl";
    // 删除邮费模版URL
    public final static String WX_POST_DELETE_DELIVERY_TEMPLATE_URL = "PostDeleteDeliveryTemplateUrl";
    // 修改邮费模版URL
    public final static String WX_POST_UPDATE_DELIVERY_TEMPLATE_URL = "PostUpdateDeliveryTemplateUrl";
    // 获取制定ID的邮费模版URL
    public final static String WX_POST_GET_DELIVERY_TEMPLATE_URL = "PostGetDeliveryTemplateUrl";
    // 获取所有邮费模版URL
    public final static String WX_POST_FIND_DELIVERY_TEMPLATE_URL = "PostFindDeliveryTemplateUrl";
    
    // 增加分组URL
    public final static String WX_POST_ADD_GROUP_URL = "PostAddGroupUrl";
    // 删除分组URL
    public final static String WX_POST_DELETE_GROUP_URL = "PostDeleteGroupUrl";
    // 修改分组属性URL
    public final static String WX_POST_MOD_GROUP_PROPERTY_URL = "PostModGroupPropertyUrl";
    // 修改分组商品URL
    public final static String WX_POST_MOD_GROUP_PRODUCT_URL = "PostModGroupProductUrl";
    // 获取所有分组URL
    public final static String WX_POST_FIND_GROUP_URL = "PostFindGroupUrl";
    // 根据分组ID获取分组信息URL
    public final static String WX_POST_GET_GROUP_URL = "PostGetGroupUrl";

    // 增加货架URL
    public final static String WX_POST_ADD_SHELF_URL = "PostAddShelfUrl";
    // 删除货架URL
    public final static String WX_POST_DELETE_SHELF_URL = "PostDeleteShelfUrl";
    // 修改货架URL
    public final static String WX_POST_MOD_SHELF_URL = "PostModShelfUrl";
    // 获取所有货架URL
    public final static String WX_POST_FIND_SHELF_URL = "PostFindShelfUrl";
    // 根据货架ID获取货架信息URL
    public final static String WX_POST_GET_SHELF_URL = "PostGetShelfUrl";
    
    // 根据订单ID获取订单详情URL
    public final static String WX_POST_GET_ORDER_URL = "PostGetOrderUrl";
    // 根据订单状态/创建时间获取订单详情URL
    public final static String WX_POST_FIND_ORDER_URL = "PostFindOrderUrl";
    // 设置订单发货信息URL
    public final static String WX_POST_SET_DELIVERY_URL = "PostSetDeliveryUrl";
    // 关闭订单URL
    public final static String WX_POST_CLOSE_ORDER_URL = "PostCloseOrderUrl";
    
    // 上传图片URL
    public final static String WX_POST_UPLOAD_IMG_URL = "PostUploadImgUrl";
    
    // 添加客服账号URL
    public final static String WX_POST_ADD_KF_ACCOUNT_URL = "PostAddKfAccountUrl";
    // 设置客服信息URL
    public final static String WX_POST_UPDATE_KF_ACCOUNT_URL = "PostUpdateKfAccountUrl";
    // 删除客服账号URL
    public final static String WX_GET_DELETE_KF_ACCOUNT_URL = "GetDeleteKfAccountUrl";
    // 上传客服头像URL
    public final static String WX_POST_UPLOAD_HEAD_IMG_URL = "PostUploadHeadImgUrl";
    // 获取客服基本信息URL
    public final static String WX_GET_FIND_KF_ACCOUNT_URL = "GetFindKfAccountUrl";
    // 获取在线客服接待信息URL
    public final static String WX_GET_FIND_KF_ACCOUNT_STATUS_URL = "GetFindKfAccountStatusUrl";
    // 获取客服聊天记录接口URL
    public final static String WX_POST_FIND_KF_RECORD_URL = "PostFindKfRecordUrl";
    
    
    
}
