package pers.zb.wechat.rpc.api.wxapi.core.req.model.menu;

import java.util.List;

import pers.zb.wechat.rpc.api.wxapi.core.annotation.ReqType;
import pers.zb.wechat.rpc.api.wxapi.core.req.model.WeixinReqParam;

/**
 * 取多媒体文件
 */
@ReqType("menuCreate")
public class MenuCreate extends WeixinReqParam {

    /**
     * button 的json信息
     * 
     * {	
          "type":"click",
          "name":"今日歌曲",
          "key":"V1001_TODAY_MUSIC"
      },
      {
           "name":"菜单",
           "sub_button":[
           {	
               "type":"view",
               "name":"搜索",
               "url":"http://www.soso.com/"
            },
            {
               "type":"view",
               "name":"视频",
               "url":"http://v.qq.com/"
            }
            
     */
    private List<WeixinButton> button;

    public List<WeixinButton> getButton() {
        return button;
    }

    public void setButton(List<WeixinButton> button) {
        this.button = button;
    }

}
