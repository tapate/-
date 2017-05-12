package pers.zb.wechat.rpc.api.wxinf.send.press;

import java.util.List;

import org.springframework.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product.Cate;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product.CateList;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product.Product;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product.Property;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product.PropertyList;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product.PropertyValue;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product.Sku;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product.SkuList;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.shop.Product.SkuValue;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

/**
 * 微信小店商品处理工具类
 */
public class ShopProductUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
	
    /**
     * 创建商品
     * 
     * @param product 商品对象
     * @param token 微信公众账号的Token对象
     * @return CreateProductResponse 微信服务器返回的创建商品请求返回对象
     * @throws Exception
     */
    public static String createProduct(Product product, Token token) throws Exception {
    	String productId = "";
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_CREATE_PRODUCT_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonProduct = JSONObject.fromObject(product).toString();
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonProduct);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			productId = jsonObject.getString("product_id");
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return productId;
    }
	
    /**
     * 删除商品
     * 
     * @param productId 商品ID
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
    public static ResultResponse deleteProduct(String productId, Token token) throws Exception {
    	ResultResponse resultResponse = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_DELETE_PRODUCT_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"product_id\": %s}";
    	jsonMsg = String.format(jsonMsg, productId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
        		resultResponse = new ResultResponse();
        		resultResponse.setErrCode(jsonObject.getString("errcode"));
        		resultResponse.setErrMsg(jsonObject.getString("errmsg"));
            }
        } catch(Exception e) {
        	resultResponse = null;
        	e.printStackTrace();
        }
        return resultResponse;
    }
	
    /**
     * 修改商品
     * 
     * @param product 商品对象
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
    public static ResultResponse updateProduct(Product product, Token token) throws Exception {
    	ResultResponse resultResponse = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_UPDATE_PRODUCT_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonProduct = JSONObject.fromObject(product).toString();
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonProduct);
        try {
        	if (null != jsonObject) {
        		resultResponse = new ResultResponse();
        		resultResponse.setErrCode(jsonObject.getString("errcode"));
        		resultResponse.setErrMsg(jsonObject.getString("errmsg"));
            }
        } catch(Exception e) {
        	resultResponse = null;
        	e.printStackTrace();
        }
        return resultResponse;
    }
	
    /**
     * 查询商品
     * 
     * @param productId 商品ID
     * @param token 微信公众账号的Token对象
     * @return Product 商品对象
     * @throws Exception
     */
    public static Product getProduct(String productId, Token token) throws Exception {
    	Product product = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_GET_PRODUCT_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"product_id\": %s}";
    	jsonMsg = String.format(jsonMsg, productId);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			product = (Product)JSONObject.toBean(jsonObject.getJSONObject("product_info"), Product.class);
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return product;
    }
	
    /**
     * 获取指定状态的所有商品
     * 
     * @param status 商品状态
     * @param token 微信公众账号的Token对象
     * @return List<Product> 商品对象集合
     * @throws Exception
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
	public static List<Product> findProductByStatus(String status, Token token) throws Exception {
    	List<Product> listProduct = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_FIND_PRODUCT_BY_STATUS_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"status\": %s}";
    	jsonMsg = String.format(jsonMsg, status);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			listProduct = JSONArray.toList(jsonObject.getJSONArray("products_info"), Product.class);
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return listProduct;
    }
	
    /**
     * 商品上下架
     * 
     * @param productId 商品ID
     * @param status 商品上下架标识(0-下架, 1-上架)
     * @param token 微信公众账号的Token对象
     * @return Boolean 操作是否成功标志
     * @throws Exception
     */
    public static Boolean modProductStatus(String productId, String status, Token token) throws Exception {
    	Boolean ifSuccess = false;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_MOD_PRODUCT_STATUS_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMsg = "{\"product_id\": %s, \"status\": %s}";
    	jsonMsg = String.format(jsonMsg, productId, status);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
        try {
        	if (null != jsonObject) {
        		String errcode = jsonObject.getString("errcode");
        		String errmsg = jsonObject.getString("errmsg");
        		if ("0".equals(errcode) && "success".equals(errmsg)) {
        			ifSuccess = true;
        		}
            }
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return ifSuccess;
    }
	
    /**
     * 获取指定商品分类的所有子分类
     * 
     * @param id 上级商品分类的ID
     * @param token 微信公众账号的Token对象
     * @return CateList 商品类别列表对象
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
	public static CateList getCateInfoList(String id, Token token) throws Exception {
    	if (StringUtils.isEmpty(id)) {
    		id = "1";
    	}
    	CateList cateList = null;
    	String requestUrl = configManager.getConfigValue(Constants.WX_POST_CATE_LIST_URL).replace("ACCESS_TOKEN", token.getAccessToken());
    	String jsonMsg = "{\"cate_id\": %s}";
    	jsonMsg = String.format(jsonMsg, id);
    	JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
    	if (null != jsonObject) {
    		cateList = new CateList();
    		cateList.setCateList(JSONArray.toList(jsonObject.getJSONArray("cate_list"), Cate.class));
    	}
        return cateList;
    }
    
    /**
     * 获取指定商品分类的所有SKU列表
     * 
     * @param id 商品分类的ID
     * @param token 微信公众账号的Token对象
     * @return SkuList 商品类别的所有SKU列表对象
     * @throws Exception
     */
	public static SkuList getCateSkuListUrl(String id, Token token) throws Exception {
    	if (StringUtils.isEmpty(id)) {
    		id = "1";
    	}
    	SkuList skuList = null;
    	Sku[] skus;
    	String requestUrl = configManager.getConfigValue(Constants.WX_POST_CATE_SKU_LIST_URL).replace("ACCESS_TOKEN", token.getAccessToken());
    	String jsonMsg = "{\"cate_id\": %s}";
    	jsonMsg = String.format(jsonMsg, id);
    	JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
    	if (null != jsonObject) {
    		skuList = new SkuList();
    		JSONArray jsonArray = jsonObject.getJSONArray("sku_table");
    		skus = new Sku[jsonArray.size()];
    		for(int i=0; i<jsonArray.size(); i++) {
    			JSONObject skuObject = jsonArray.getJSONObject(i);
    			skus[i].setId(skuObject.getString("id"));
    			skus[i].setName(skuObject.getString("name"));
    			JSONArray skuValueArray = skuObject.getJSONArray("value_list");
    			SkuValue[] skuValues = new SkuValue[skuValueArray.size()];
    			for(int j=0; j<skuValueArray.size(); j++) {
    				JSONObject skuValueObject = skuValueArray.getJSONObject(j);
    				skuValues[j].setId(skuValueObject.getString("id"));
    				skuValues[j].setName(skuValueObject.getString("name"));
				}
    			skus[i].setSkuValues(skuValues);
    		}
    		skuList.setSkus(skus);
    	}
        return skuList;
    }
    
    /**
     * 获取指定商品分类的所有属性列表
     * 
     * @param id 商品分类的ID
     * @param token 微信公众账号的Token对象
     * @return PropertyList 商品类别的所有属性列表对象
     * @throws Exception
     */
	public static PropertyList getCatePropertyListUrl(String id, Token token) throws Exception {
    	if (StringUtils.isEmpty(id)) {
    		id = "1";
    	}
    	PropertyList propertyList = null;
    	Property[] propertys;
    	String requestUrl = configManager.getConfigValue(Constants.WX_POST_CATE_PROPERTY_LIST_URL).replace("ACCESS_TOKEN", token.getAccessToken());
    	String jsonMsg = "{\"cate_id\": %s}";
    	jsonMsg = String.format(jsonMsg, id);
    	JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMsg);
    	if (null != jsonObject) {
    		propertyList = new PropertyList();
    		JSONArray jsonArray = jsonObject.getJSONArray("properties");
    		propertys = new Property[jsonArray.size()];
    		for(int i=0; i<jsonArray.size(); i++) {
    			JSONObject propertyObject = jsonArray.getJSONObject(i);
    			propertys[i].setId(propertyObject.getString("id"));
    			propertys[i].setName(propertyObject.getString("name"));
    			JSONArray propertyValueArray = propertyObject.getJSONArray("property_value");
    			PropertyValue[] propertyValues = new PropertyValue[propertyValueArray.size()];
    			for(int j=0; j<propertyValueArray.size(); j++) {
    				JSONObject propertyValueObject = propertyValueArray.getJSONObject(j);
    				propertyValues[j].setId(propertyValueObject.getString("id"));
    				propertyValues[j].setName(propertyValueObject.getString("name"));
				}
    			propertys[i].setPropertyValues(propertyValues);
    		}
    		propertyList.setPropertys(propertys);
    	}
        return propertyList;
    }
}
