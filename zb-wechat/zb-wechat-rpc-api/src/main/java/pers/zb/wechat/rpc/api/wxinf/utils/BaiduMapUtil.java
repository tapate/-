package pers.zb.wechat.rpc.api.wxinf.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pers.zb.wechat.rpc.api.wxinf.pojo.BaiduPlace;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.Article;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.UserLocation;
import pers.zb.wechat.rpc.api.wxinf.send.press.core.HttpClientConnectionManager;


/**
 * 百度地图操作工具类
 */
public class BaiduMapUtil {

    private static ConfigManager configManager = ConfigManager.getInstance();

    /**
     * 百度地图圆形区域搜索
     * 
     * @param query
     *            搜索关键字
     * @param lng
     *            中心点经度
     * @param lat
     *            中心点纬度
     * @param radius
     *            搜索半径（单位：米）
     * @return List<BaiduPlace> 百度地址信息对象列表
     * @throws Exception
     */
    public static List<BaiduPlace> searchCircularPlace(String query,
            String lng, String lat, String radius) throws Exception {
        String requestUrl = configManager
                .getConfigValue(Constants.DB_GET_SEARCH_CIRCULAR_PLACE_URL)
                .replace("QUERY", query).replace("LAT", lat)
                .replace("LNG", lng).replace("REDIUS", radius);
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(
                requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        List<BaiduPlace> list = new ArrayList<BaiduPlace>();
        try {
            if (null != jsonObject) {
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                BaiduPlace baiduPlace = null;
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject resultObject = jsonArray.getJSONObject(i);
                    baiduPlace = new BaiduPlace();
                    baiduPlace.setName(resultObject.getString("name"));
                    baiduPlace.setAddress(resultObject.getString("address"));
                    baiduPlace.setLng(resultObject.getString("lng"));
                    baiduPlace.setLat(resultObject.getString("lat"));
                    baiduPlace
                            .setTelephone(resultObject.getString("telephone"));
                    baiduPlace.setDistance(resultObject.getInt("distance"));
                    list.add(baiduPlace);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据Place组装图文消息列表
     * 
     * @param placeList
     *            百度地址信息对象列表
     * @param bd09Lng
     *            中心点经度
     * @param bd09Lat
     *            中心点纬度
     * @return List<Article> 微信图文消息列表对象
     */
    public static List<Article> makeArticleList(List<BaiduPlace> placeList,
            String bd09Lng, String bd09Lat) {
        // 项目的跟路径
        String basePath = "";
        List<Article> list = new ArrayList<Article>();
        BaiduPlace baiduPlace = null;
        for (int i = 0; i < placeList.size(); i++) {
            baiduPlace = placeList.get(i);
            Article article = new Article();
            article.setTitle(baiduPlace.getName() + "\n距离约"
                    + baiduPlace.getDistance() + "米");
            article.setUrl(String.format(basePath
                    + "route.jsp?p1=%s,%s&p2=%s, %s", bd09Lng, bd09Lat,
                    baiduPlace.getLng(), baiduPlace.getLat()));
            if (i == 0) {
                article.setPicUrl(basePath + "/images/poisearch.png");
            } else {
                article.setPicUrl(basePath + "/images/navi.png");
            }
            list.add(article);
        }
        return list;
    }

    /**
     * 将微信定位的坐标转换成百度坐标
     * 
     * @param userLocation
     *            用户地理位置对象
     * @return UserLocation 转换后的用户地理位置对象
     * @throws Exception
     */
    public static UserLocation convertCoord(UserLocation userLocation)
            throws Exception {
        String requestUrl = configManager
                .getConfigValue(Constants.DB_GET_CONVERT_COORD_URL)
                .replace("LAT", userLocation.getLat())
                .replace("LNG", userLocation.getLng());
        JSONObject jsonObject = HttpClientConnectionManager.httpsRequest(
                requestUrl, WXConstants.REQ_METHOD_TYPE_GET, null);
        try {
            if (null != jsonObject) {
                userLocation.setBd09Lng(jsonObject.getString("x"));
                userLocation.setBd09Lat(jsonObject.getString("y"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userLocation;
    }
}
