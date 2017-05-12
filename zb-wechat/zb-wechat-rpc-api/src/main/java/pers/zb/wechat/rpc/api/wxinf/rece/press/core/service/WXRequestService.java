package pers.zb.wechat.rpc.api.wxinf.rece.press.core.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.LocationEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.MenuEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.NewsFinishEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.QRCodeEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.SubscribeEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.event.UnSubscribeEventRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message.ImageMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message.LinkMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message.LocationMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message.TextMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message.VideoMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.request.message.VoiceMessageRequest;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.BaseMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.ImageMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.KfMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.MusicMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.NewsMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.TextMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.VideoMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.pojo.response.message.VoiceMessageResponse;
import pers.zb.wechat.rpc.api.wxinf.rece.press.IWXEventExecutor;
import pers.zb.wechat.rpc.api.wxinf.rece.press.IWXMessageExecutor;
import pers.zb.wechat.rpc.api.wxinf.rece.press.utils.RequestMessageUtil;
import pers.zb.wechat.rpc.api.wxinf.rece.press.utils.ResponseMessageUtil;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;


/**
 * 核心服务类
 */
public class WXRequestService {

    private static Map<String, String> map;

    public static Map<String, String> getMap() {
        return map;
    }

    public static void setMap(Map<String, String> map) {
        WXRequestService.map = map;
    }

    /**
     * 处理微信发来的请求
     * 
     * @param request
     * @return XML 响应消息XML数据
     */
    @SuppressWarnings("unchecked")
    public static String processRequest(HttpServletRequest request) {

        // XML格式的消息数据
        String respXml = null;
        try {
            // 调用parseXml方法解析请求信息
            Map<String, String> requestMap = RequestMessageUtil.parseXml(request);
            // 发送方账号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息创建时间 （整型）
            String createTime = requestMap.get("CreateTime");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            
            String executor = "";
            BaseMessageResponse baseMessageResponse = null;
            if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_TEXT)) {
                // 消息id，64位整型
                String msgId = requestMap.get("MsgId");
                // 文本消息处理
                String content = requestMap.get("Content");

                TextMessageRequest textMessageRequest = new TextMessageRequest();
                textMessageRequest.setFromUserName(fromUserName);
                textMessageRequest.setToUserName(toUserName);
                textMessageRequest.setCreateTime(Long.parseLong(createTime));
                textMessageRequest.setMsgType(msgType);
                textMessageRequest.setMsgId(Long.parseLong(msgId));

                textMessageRequest.setContent(content);

                executor = map.get("text-message");
                Class<IWXMessageExecutor> iwxExecutor = (Class<IWXMessageExecutor>) Class.forName(executor);
                baseMessageResponse = iwxExecutor.newInstance().executor(textMessageRequest);
            } else if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_IMAGE)) {
                // 消息id，64位整型
                String msgId = requestMap.get("MsgId");
                // 图片消息处理
                String picUrl = requestMap.get("PicUrl");

                ImageMessageRequest imageMessageRequest = new ImageMessageRequest();
                imageMessageRequest.setFromUserName(fromUserName);
                imageMessageRequest.setToUserName(toUserName);
                imageMessageRequest.setCreateTime(Long.parseLong(createTime));
                imageMessageRequest.setMsgType(msgType);
                imageMessageRequest.setMsgId(Long.parseLong(msgId));

                imageMessageRequest.setPicUrl(picUrl);

                executor = map.get("image-message");
                Class<IWXMessageExecutor> iwxExecutor = (Class<IWXMessageExecutor>) Class.forName(executor);
                baseMessageResponse = iwxExecutor.newInstance().executor(imageMessageRequest);
            } else if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_VOICE)) {
                // 消息id，64位整型
                String msgId = requestMap.get("MsgId");
                // 语音消息处理
                String mediaId = requestMap.get("MediaId");
                String format = requestMap.get("Format");
                String recognition = requestMap.get("Recognition");

                VoiceMessageRequest voiceMessageRequest = new VoiceMessageRequest();
                voiceMessageRequest.setFromUserName(fromUserName);
                voiceMessageRequest.setToUserName(toUserName);
                voiceMessageRequest.setCreateTime(Long.parseLong(createTime));
                voiceMessageRequest.setMsgType(msgType);
                voiceMessageRequest.setMsgId(Long.parseLong(msgId));

                voiceMessageRequest.setMediaId(mediaId);
                voiceMessageRequest.setFormat(format);
                voiceMessageRequest.setRecognition(recognition);

                executor = map.get("voice-message");
                Class<IWXMessageExecutor> iwxExecutor = (Class<IWXMessageExecutor>) Class.forName(executor);
                baseMessageResponse = iwxExecutor.newInstance().executor(voiceMessageRequest);
            } else if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_VIDEO)) {
                // 消息id，64位整型
                String msgId = requestMap.get("MsgId");
                // 视频消息处理
                String mediaId = requestMap.get("MediaId");
                String thumbMediaId = requestMap.get("ThumbMediaId");

                VideoMessageRequest videoMessageRequest = new VideoMessageRequest();
                videoMessageRequest.setFromUserName(fromUserName);
                videoMessageRequest.setToUserName(toUserName);
                videoMessageRequest.setCreateTime(Long.parseLong(createTime));
                videoMessageRequest.setMsgType(msgType);
                videoMessageRequest.setMsgId(Long.parseLong(msgId));

                videoMessageRequest.setMediaId(mediaId);
                videoMessageRequest.setThumbMediaId(thumbMediaId);

                executor = map.get("video-message");
                Class<IWXMessageExecutor> iwxExecutor = (Class<IWXMessageExecutor>) Class.forName(executor);
                baseMessageResponse = iwxExecutor.newInstance().executor(videoMessageRequest);
            } else if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_LOCATION)) {
                // 消息id，64位整型
                String msgId = requestMap.get("MsgId");
                // 地理位置消息处理
                String locationX = requestMap.get("Location_X");
                String locationY = requestMap.get("Location_Y");
                String scale = requestMap.get("Scale");
                String label = requestMap.get("Label");

                LocationMessageRequest locationMessageRequest = new LocationMessageRequest();
                locationMessageRequest.setFromUserName(fromUserName);
                locationMessageRequest.setToUserName(toUserName);
                locationMessageRequest
                        .setCreateTime(Long.parseLong(createTime));
                locationMessageRequest.setMsgType(msgType);
                locationMessageRequest.setMsgId(Long.parseLong(msgId));

                locationMessageRequest.setLocation_X(locationX);
                locationMessageRequest.setLocation_Y(locationY);
                locationMessageRequest.setScale(scale);
                locationMessageRequest.setLabel(label);

                executor = map.get("location-message");
                Class<IWXMessageExecutor> iwxExecutor = (Class<IWXMessageExecutor>) Class
                        .forName(executor);
                baseMessageResponse = iwxExecutor.newInstance().executor(
                        locationMessageRequest);
            } else if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_LINK)) {
                // 消息id，64位整型
                String msgId = requestMap.get("MsgId");
                // 链接消息处理
                String title = requestMap.get("Title");
                String description = requestMap.get("Description");
                String url = requestMap.get("Url");

                LinkMessageRequest linkMessageRequest = new LinkMessageRequest();
                linkMessageRequest.setFromUserName(fromUserName);
                linkMessageRequest.setToUserName(toUserName);
                linkMessageRequest.setCreateTime(Long.parseLong(createTime));
                linkMessageRequest.setMsgType(msgType);
                linkMessageRequest.setMsgId(Long.parseLong(msgId));

                linkMessageRequest.setTitle(title);
                linkMessageRequest.setDescription(description);
                linkMessageRequest.setUrl(url);

                executor = map.get("link-message");
                Class<IWXMessageExecutor> iwxExecutor = (Class<IWXMessageExecutor>) Class
                        .forName(executor);
                baseMessageResponse = iwxExecutor.newInstance().executor(
                        linkMessageRequest);
            } else if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件消息处理

                // 事件类型
                String eventType = requestMap.get("Event");
                if (eventType.equals(WXConstants.EVENT_TYPE_SUBSCRIBE)) {

                    String eventKey = requestMap.get("EventKey");
                    String ticket = requestMap.get("Ticket");
//                    if (eventKey == null || eventKey.length() == 0) {
                        // 处理订阅事件
                        SubscribeEventRequest subscribeEventRequest = new SubscribeEventRequest();
                        subscribeEventRequest.setFromUserName(fromUserName);
                        subscribeEventRequest.setToUserName(toUserName);
                        subscribeEventRequest.setCreateTime(Long
                                .parseLong(createTime));
                        subscribeEventRequest.setMsgType(msgType);
                        subscribeEventRequest.setEvent(eventType);

                        executor = map.get("subscribe-event");
                        Class<IWXEventExecutor> iwxExecutor = (Class<IWXEventExecutor>) Class
                                .forName(executor);
                        baseMessageResponse = iwxExecutor.newInstance()
                                .executor(subscribeEventRequest);
//                    } else {
//                        // 处理未关注用户扫描带参数二维码事件
//                        QRCodeEventRequest qrCodeEventRequest = new QRCodeEventRequest();
//                        qrCodeEventRequest.setFromUserName(fromUserName);
//                        qrCodeEventRequest.setToUserName(toUserName);
//                        qrCodeEventRequest.setCreateTime(Long
//                                .parseLong(createTime));
//                        qrCodeEventRequest.setMsgType(msgType);
//                        qrCodeEventRequest.setEvent(eventType);
//
//                        qrCodeEventRequest.setEventKey(eventKey);
//                        qrCodeEventRequest.setTicket(ticket);
//
//                        executor = map.get("scan-event");
//                        Class<IWXEventExecutor> iwxExecutor = (Class<IWXEventExecutor>) Class
//                                .forName(executor);
//                        baseMessageResponse = iwxExecutor.newInstance()
//                                .executor(qrCodeEventRequest);
//                    }

                } else if (eventType.equals(WXConstants.EVENT_TYPE_UNSUBSCRIBE)) {
                    // 处理取消订阅事件：取消订阅后，用户不会再收到公众账号发送的消息，因此不需要回复
                    UnSubscribeEventRequest unSubscribeEventRequest = new UnSubscribeEventRequest();
                    unSubscribeEventRequest.setFromUserName(fromUserName);
                    unSubscribeEventRequest.setToUserName(toUserName);
                    unSubscribeEventRequest.setCreateTime(Long
                            .parseLong(createTime));
                    unSubscribeEventRequest.setMsgType(msgType);
                    unSubscribeEventRequest.setEvent(eventType);

                    executor = map.get("unsubscribe-event");
                    Class<IWXEventExecutor> iwxExecutor = (Class<IWXEventExecutor>) Class
                            .forName(executor);
                    baseMessageResponse = iwxExecutor.newInstance().executor(
                            unSubscribeEventRequest);
                } else if (eventType.equalsIgnoreCase(WXConstants.EVENT_TYPE_SCAN)) {
                    // 处理已关注用户扫描带参数二维码事件
                    String eventKey = requestMap.get("EventKey");
                    String ticket = requestMap.get("Ticket");

                    QRCodeEventRequest qrCodeEventRequest = new QRCodeEventRequest();
                    qrCodeEventRequest.setFromUserName(fromUserName);
                    qrCodeEventRequest.setToUserName(toUserName);
                    qrCodeEventRequest
                            .setCreateTime(Long.parseLong(createTime));
                    qrCodeEventRequest.setMsgType(msgType);
                    qrCodeEventRequest.setEvent(eventType);

                    qrCodeEventRequest.setEventKey(eventKey);
                    qrCodeEventRequest.setTicket(ticket);

                    executor = map.get("scan-event");
                    Class<IWXEventExecutor> iwxExecutor = (Class<IWXEventExecutor>) Class
                            .forName(executor);
                    baseMessageResponse = iwxExecutor.newInstance().executor(
                            qrCodeEventRequest);
                } else if (eventType.equals(WXConstants.EVENT_TYPE_LOCATION)) {
                    // 处理上报地理位置事件
                    String latitude = requestMap.get("Latitude");
                    String longitude = requestMap.get("Longitude");
                    String precision = requestMap.get("Precision");

                    LocationEventRequest locationEventRequest = new LocationEventRequest();
                    locationEventRequest.setFromUserName(fromUserName);
                    locationEventRequest.setToUserName(toUserName);
                    locationEventRequest.setCreateTime(Long
                            .parseLong(createTime));
                    locationEventRequest.setMsgType(msgType);
                    locationEventRequest.setEvent(eventType);

                    locationEventRequest.setLatitude(latitude);
                    locationEventRequest.setLongitude(longitude);
                    locationEventRequest.setPrecision(precision);

                    executor = map.get("location-event");
                    Class<IWXEventExecutor> iwxExecutor = (Class<IWXEventExecutor>) Class.forName(executor);
                    //baseMessageResponse = iwxExecutor.newInstance().executor(locationEventRequest);//取消地理位置上报
                } else if (eventType.equals(WXConstants.EVENT_TYPE_CLICK)) {
                    // 处理点击菜单拉取消息
                    String eventKey = requestMap.get("EventKey");

                    MenuEventRequest menuEventRequest = new MenuEventRequest();
                    menuEventRequest.setFromUserName(fromUserName);
                    menuEventRequest.setToUserName(toUserName);
                    menuEventRequest.setCreateTime(Long.parseLong(createTime));
                    menuEventRequest.setMsgType(msgType);
                    menuEventRequest.setEvent(eventType);

                    menuEventRequest.setEventKey(eventKey);

                    executor = map.get("click-event");
                    Class<IWXEventExecutor> iwxExecutor = (Class<IWXEventExecutor>) Class
                            .forName(executor);
                    baseMessageResponse = iwxExecutor.newInstance().executor(
                            menuEventRequest);
                } else if (eventType.equals(WXConstants.EVENT_TYPE_VIEW)) {
                    // 处理点击菜单跳转链接
                    String eventKey = requestMap.get("EventKey");

                    MenuEventRequest menuEventRequest = new MenuEventRequest();
                    menuEventRequest.setFromUserName(fromUserName);
                    menuEventRequest.setToUserName(toUserName);
                    menuEventRequest.setCreateTime(Long.parseLong(createTime));
                    menuEventRequest.setMsgType(msgType);
                    menuEventRequest.setEvent(eventType);

                    menuEventRequest.setEventKey(eventKey);

                    executor = map.get("view-event");
                    Class<IWXEventExecutor> iwxExecutor = (Class<IWXEventExecutor>) Class
                            .forName(executor);
                    baseMessageResponse = iwxExecutor.newInstance().executor(
                            menuEventRequest);
                } else if (eventType.equals(WXConstants.EVENT_TYPE_NEWS)) {
                    // 群发图文消息回调事件
                    String msgID = requestMap.get("MsgID");
                    String status = requestMap.get("Status");
                    String totalCount = requestMap.get("TotalCount");
                    String filterCount = requestMap.get("FilterCount");
                    String sentCount = requestMap.get("SentCount");
                    String errorCount = requestMap.get("ErrorCount");

                    NewsFinishEventRequest newsFinishEventRequest = new NewsFinishEventRequest();
                    newsFinishEventRequest.setFromUserName(fromUserName);
                    newsFinishEventRequest.setToUserName(toUserName);
                    newsFinishEventRequest.setCreateTime(Long
                            .parseLong(createTime));
                    newsFinishEventRequest.setMsgType(msgType);
                    newsFinishEventRequest.setEvent(eventType);

                    newsFinishEventRequest.setMsgID(Long.parseLong(msgID));
                    newsFinishEventRequest.setStatus(status);
                    newsFinishEventRequest.setTotalCount(Integer
                            .parseInt(totalCount));
                    newsFinishEventRequest.setFilterCount(Integer
                            .parseInt(filterCount));
                    newsFinishEventRequest.setSentCount(Integer
                            .parseInt(sentCount));
                    newsFinishEventRequest.setErrorCount(Integer
                            .parseInt(errorCount));

                    executor = map.get("news-send-finish-event");
                    Class<IWXEventExecutor> iwxExecutor = (Class<IWXEventExecutor>) Class
                            .forName(executor);
                    baseMessageResponse = iwxExecutor.newInstance().executor(
                            newsFinishEventRequest);
                }
            }
            // 处理响应信息
            if (baseMessageResponse != null) {
                String rspMsgType = baseMessageResponse.getMsgType();
                if (WXConstants.RSP_MESSAGE_TYPE_TEXT.equals(rspMsgType)) {
                    respXml = ResponseMessageUtil
                            .parseTextMessageResponseToXml((TextMessageResponse) baseMessageResponse);
                } else if (WXConstants.RSP_MESSAGE_TYPE_IMAGE
                        .equals(rspMsgType)) {
                    respXml = ResponseMessageUtil
                            .parseImageMessageResponseToXml((ImageMessageResponse) baseMessageResponse);
                } else if (WXConstants.RSP_MESSAGE_TYPE_VOICE
                        .equals(rspMsgType)) {
                    respXml = ResponseMessageUtil
                            .parseVoiceMessageResponseToXml((VoiceMessageResponse) baseMessageResponse);
                } else if (WXConstants.RSP_MESSAGE_TYPE_VIDEO
                        .equals(rspMsgType)) {
                    respXml = ResponseMessageUtil
                            .parseVideoMessageResponseToXml((VideoMessageResponse) baseMessageResponse);
                } else if (WXConstants.RSP_MESSAGE_TYPE_MUSIC
                        .equals(rspMsgType)) {
                    respXml = ResponseMessageUtil
                            .parseMusicMessageResponseToXml((MusicMessageResponse) baseMessageResponse);
                } else if (WXConstants.RSP_MESSAGE_TYPE_NEWS.equals(rspMsgType)) {
                    respXml = ResponseMessageUtil
                            .parseNewsMessageResponseToXml((NewsMessageResponse) baseMessageResponse);
                } else if (WXConstants.RSP_MESSAGE_TYPE_KF.equals(rspMsgType)) {
                	respXml = ResponseMessageUtil
                            .parseKfMessageResponseToXml((KfMessageResponse) baseMessageResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();           
        }
//        return respXml;
        return "success";//不发消息给用户，默认回复"success"
    }
}
