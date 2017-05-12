package pers.zb.ucenter.web.wechat.menu;

import pers.zb.wechat.rpc.api.wxinf.send.pojo.menu.Button;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.menu.ClickButton;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.menu.Menu;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.menu.ViewButton;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.response.ResultResponse;
import pers.zb.wechat.rpc.api.wxinf.send.pojo.token.Token;
import pers.zb.wechat.rpc.api.wxinf.send.press.MenuUtil;
import pers.zb.wechat.rpc.api.wxinf.send.press.TokenUtil;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;

public class CreateMenu {

    // private static String servletRootPath =
    // "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx70109145d6974c9c&redirect_uri="
    // + URLEncoder.encode("http://zhoubang85.com/index") +
    // "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
    
    private static final String DO_MAIN_URL = "http://weixin.ecarpool.cn/pinche-front-wx";
    //路线招募
    private static final String ROAD = DO_MAIN_URL + "/roadZhaomu";
    
    //关于我们
    private static final String ABOUT_US = "http://mp.weixin.qq.com/s?__biz=MzAwNzcwMjA0OA==&mid=216821633&idx=1&sn=11b1c56f5fd952ed9b8616238b63bc8e#rd";
    
    public static Menu getMenu() {
        Menu menu = new Menu();
        try {
            ViewButton viewButton1_0 = new ViewButton();
            viewButton1_0.setName("路线招募");
            viewButton1_0.setType(WXConstants.MENU_BUTTON_TYPE_VIEW);
            viewButton1_0.setUrl(ROAD);
            
            ClickButton clickButton4 = new ClickButton();
            clickButton4.setName("关于我们");
            clickButton4.setType(WXConstants.MENU_BUTTON_TYPE_CLICK);
            clickButton4.setKey("about_us");
            
            ViewButton viewButton1_1 = new ViewButton();
            viewButton1_1.setName("关于我们");
            viewButton1_1.setType(WXConstants.MENU_BUTTON_TYPE_VIEW);
            viewButton1_1.setUrl(ABOUT_US);
             
            menu.setButton(new Button[] { viewButton1_0, clickButton4});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menu;
    }
    public static void main(String[] args) {
        try {
            Token at = TokenUtil.getAccessToken();
            System.out.println(at.getAccessToken());
            if (null != at) {
                // 调用接口创建菜单
                Menu menu = getMenu();
                ResultResponse res = MenuUtil.createMenu(menu, at);
                System.out.println(res.getErrCode() + "\t" + res.getErrMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String s = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx70109145d6974c9c&redirect_uri=" + URLEncoder.encode("http://zhoubang85.com/index") + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
        //System.out.println(s);
    }
}
