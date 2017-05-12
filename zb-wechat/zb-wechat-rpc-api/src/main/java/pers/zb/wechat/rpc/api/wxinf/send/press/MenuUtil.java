package pers.zb.wechat.rpc.api.wxinf.send.press;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.menu.Button;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.menu.ClickButton;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.menu.ComplexButton;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.menu.Menu;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.menu.ViewButton;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;
import pers.zb.wechat.rpc.api.wxinf.utils.ConfigManager;
import pers.zb.wechat.rpc.api.wxinf.utils.Constants;
import pers.zb.wechat.rpc.api.wxinf.utils.UrlUtil;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

/**
* 微信菜单处理工具类
*/
public class MenuUtil {
    
    private static ConfigManager configManager = ConfigManager.getInstance();
    
    /**
     * 创建菜单
     * 
     * @param menu 微信菜单对象
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
    public static ResultResponse createMenu(Menu menu, Token token) throws Exception {
    	ResultResponse resultResponse = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_POST_CREATE_MENU_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonMenu = JSONObject.fromObject(menu).toString();
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_POST, jsonMenu);
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
     * 查询菜单
     * 
     * @param token 微信公众账号的Token对象
     * @return Menu 微信菜单对象
     * @throws Exception
     */
    public static Menu getMenuInfo(Token token) throws Exception {
    	Menu menu = null;
    	Button[] buttons;
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_FIND_MENU_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        if (null != jsonObject) {
        	menu = new Menu();
        	JSONArray jsonArray = jsonObject.getJSONArray("button");
        	buttons = new Button[jsonArray.size()];
        	for(int i=0; i<jsonArray.size(); i++) {
        		JSONObject buttonObject = jsonArray.getJSONObject(i);
        		JSONArray subButtonArray = buttonObject.getJSONArray("sub_button");
        		if (subButtonArray.size() > 0) {
            		ComplexButton complexButton = new ComplexButton();
            		Button[] subButtons = new Button[subButtonArray.size()];
            		for(int j=0; j<subButtonArray.size(); j++) {
            			JSONObject subButtonObject = subButtonArray.getJSONObject(j);
            			String subType = subButtonObject.getString("type");
            			if (WXConstants.MENU_BUTTON_TYPE_VIEW.equals(subType)) {
                			ViewButton viewButton = (ViewButton)JSONObject.toBean(subButtonObject, ViewButton.class);
                			subButtons[j] = viewButton;
                    	} else if (WXConstants.MENU_BUTTON_TYPE_CLICK.equals(subType) 
                    			|| WXConstants.MENU_BUTTON_TYPE_SCANCODE_PUSH.equals(subType)
                    			|| WXConstants.MENU_BUTTON_TYPE_SCANCODE_WAITMSG.equals(subType)
                    			|| WXConstants.MENU_BUTTON_TYPE_PIC_SYSPHOTO.equals(subType)
                    			|| WXConstants.MENU_BUTTON_TYPE_PIC_PHOTO_OR_ALBUM.equals(subType)
                    			|| WXConstants.MENU_BUTTON_TYPE_PIC_WEIXIN.equals(subType)
                    			|| WXConstants.MENU_BUTTON_TYPE_LOCATION_SELECT.equals(subType)) {
                    		ClickButton clickButton = (ClickButton)JSONObject.toBean(subButtonObject, ClickButton.class);
                    		subButtons[j] = clickButton;
                    	}
            		}
            		complexButton.setSub_button(subButtons);
            		buttons[i] = complexButton;
            	} else {
            		String type = buttonObject.getString("type");
            		if (WXConstants.MENU_BUTTON_TYPE_VIEW.equals(type)) {
            			ViewButton viewButton = (ViewButton)JSONObject.toBean(buttonObject, ViewButton.class);
            			buttons[i] = viewButton;
                	} else if (WXConstants.MENU_BUTTON_TYPE_CLICK.equals(type)) {
                		ClickButton clickButton = (ClickButton)JSONObject.toBean(buttonObject, ClickButton.class);
                		buttons[i] = clickButton;
                	}
            	}
        	}
        	menu.setButton(buttons);
        }
        return menu;
    }
    
    /**
     * 删除自定义菜单
     * 
     * @param token 微信公众账号的Token对象
     * @return ResultResponse 微信服务器返回的错误提示对象
     * @throws Exception
     */
    public static ResultResponse clearMenu(Token token) throws Exception {
    	ResultResponse resultResponse = null;
        String requestUrl = configManager.getConfigValue(Constants.WX_GET_DEL_MENU_URL).replace("ACCESS_TOKEN", token.getAccessToken());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
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
     * 获取网页授权地址URL
     * 
     * @param url 需要转换的转发URL
     * @return String 微信服务器返回的网页授权地址URL
     * @throws Exception
     */
    public static String getOauth2TokenUrl(String url) throws Exception {
    	String appid = configManager.getConfigValue(Constants.WX_TOKEN_APPID);
    	String oauth2TokenUrl = configManager.getConfigValue(Constants.WX_OAUTH2TOKEN_URL).replace("APPID", appid).replace("REDIRECT_URI", UrlUtil.urlEncoder(url, "UTF-8"));
        return oauth2TokenUrl;
    }
}
