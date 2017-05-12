package pers.zb.wechat.rpc.api.wxinf.rece.press.core.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

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
import pers.zb.wechat.rpc.api.wxinf.rece.press.utils.ReceiveXmlEntity;
import pers.zb.wechat.rpc.api.wxinf.rece.press.utils.ReceiveXmlUtil;
import pers.zb.wechat.rpc.api.wxinf.rece.press.utils.ResponseMessageUtil;
import pers.zb.wechat.rpc.api.wxinf.utils.AuthProcess;
import pers.zb.wechat.rpc.api.wxinf.utils.WXConstants;


/**
 * 核心服务类
 */
public class WXRequestAuthService {

    private static Map<String, String> map;

    public static Map<String, String> getMap() {
        return map;
    }

    public static void setMap(Map<String, String> map) {
        WXRequestAuthService.map = map;
    }

    /**
     * 处理微信发来的请求
     * 
     * @param request
     * @return XML 响应消息XML数据
     */
    @SuppressWarnings("unchecked")
    public static String processRequest(HttpServletRequest request) {
        String xml = "";
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            xml = sb.toString();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String encrypt_type = request.getParameter("encrypt_type");
        if (StringUtils.isEmpty(encrypt_type) || encrypt_type.equals("raw")) {
            xml = AuthProcess.decryptMsg(request, xml);
        }
        // XML格式的消息数据
        String respXml = null;
        try {
            // 调用ReceiveXmlProcess方法解析请求信息
            ReceiveXmlEntity receiveXmlEntity = ReceiveXmlUtil
                    .getMsgEntity(xml);
            // 发送方账号
            String fromUserName = receiveXmlEntity.getFromUserName();
            // 开发者微信号
            String toUserName = receiveXmlEntity.getToUserName();
            // 消息创建时间 （整型）
            String createTime = receiveXmlEntity.getCreateTime();
            // 消息类型
            String msgType = receiveXmlEntity.getMsgType();

            String executor = "";
            BaseMessageResponse baseMessageResponse = null;
            if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_TEXT)) {
                // 消息id，64位整型
                String msgId = receiveXmlEntity.getMsgId();
                // 文本消息处理
                String content = receiveXmlEntity.getContent();

                TextMessageRequest textMessageRequest = new TextMessageRequest();
                textMessageRequest.setFromUserName(fromUserName);
                textMessageRequest.setToUserName(toUserName);
                textMessageRequest.setCreateTime(Long.parseLong(createTime));
                textMessageRequest.setMsgType(msgType);
                textMessageRequest.setMsgId(Long.parseLong(msgId));

                textMessageRequest.setContent(content);

                executor = map.get("text-message");
                Class<IWXMessageExecutor> iwxExecutor = (Class<IWXMessageExecutor>) Class
                        .forName(executor);
                baseMessageResponse = iwxExecutor.newInstance().executor(
                        textMessageRequest);
            } else if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_IMAGE)) {
                // 消息id，64位整型
                String msgId = receiveXmlEntity.getMsgId();
                // 图片消息处理
                String picUrl = receiveXmlEntity.getPicUrl();

                ImageMessageRequest imageMessageRequest = new ImageMessageRequest();
                imageMessageRequest.setFromUserName(fromUserName);
                imageMessageRequest.setToUserName(toUserName);
                imageMessageRequest.setCreateTime(Long.parseLong(createTime));
                imageMessageRequest.setMsgType(msgType);
                imageMessageRequest.setMsgId(Long.parseLong(msgId));

                imageMessageRequest.setPicUrl(picUrl);

                executor = map.get("image-message");
                Class<IWXMessageExecutor> iwxExecutor = (Class<IWXMessageExecutor>) Class
                        .forName(executor);
                baseMessageResponse = iwxExecutor.newInstance().executor(
                        imageMessageRequest);
            } else if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_VOICE)) {
                // 消息id，64位整型
                String msgId = receiveXmlEntity.getMsgId();
                // 语音消息处理
                String mediaId = receiveXmlEntity.getMediaId();
                String format = receiveXmlEntity.getFormat();
                String recognition = receiveXmlEntity.getRecognition();

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
                Class<IWXMessageExecutor> iwxExecutor = (Class<IWXMessageExecutor>) Class
                        .forName(executor);
                baseMessageResponse = iwxExecutor.newInstance().executor(
                        voiceMessageRequest);
            } else if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_VIDEO)) {
                // 消息id，64位整型
                String msgId = receiveXmlEntity.getMsgId();
                // 视频消息处理
                String mediaId = receiveXmlEntity.getMediaId();
                String thumbMediaId = receiveXmlEntity.getThumbMediaId();

                VideoMessageRequest videoMessageRequest = new VideoMessageRequest();
                videoMessageRequest.setFromUserName(fromUserName);
                videoMessageRequest.setToUserName(toUserName);
                videoMessageRequest.setCreateTime(Long.parseLong(createTime));
                videoMessageRequest.setMsgType(msgType);
                videoMessageRequest.setMsgId(Long.parseLong(msgId));

                videoMessageRequest.setMediaId(mediaId);
                videoMessageRequest.setThumbMediaId(thumbMediaId);

                executor = map.get("video-message");
                Class<IWXMessageExecutor> iwxExecutor = (Class<IWXMessageExecutor>) Class
                        .forName(executor);
                baseMessageResponse = iwxExecutor.newInstance().executor(
                        videoMessageRequest);
            } else if (msgType.equals(WXConstants.REQ_MESSAGE_TYPE_LOCATION)) {
                // 消息id，64位整型
                String msgId = receiveXmlEntity.getMsgId();
                // 地理位置消息处理
                String locationX = receiveXmlEntity.getLocation_X();
                String locationY = receiveXmlEntity.getLocation_Y();
                String scale = receiveXmlEntity.getScale();
                String label = receiveXmlEntity.getLabel();

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
                String msgId = receiveXmlEntity.getMsgId();
                // 链接消息处理
                String title = receiveXmlEntity.getTitle();
                String description = receiveXmlEntity.getDescription();
                String url = receiveXmlEntity.getUrl();

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
                String eventType = receiveXmlEntity.getEvent();
                if (eventType.equals(WXConstants.EVENT_TYPE_SUBSCRIBE)) {

                    String eventKey = receiveXmlEntity.getEventKey();
                    String ticket = receiveXmlEntity.getTicket();
                    if (eventKey == null || eventKey.length() == 0) {
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
                    } else {
                        // 处理未关注用户扫描带参数二维码事件
                        QRCodeEventRequest qrCodeEventRequest = new QRCodeEventRequest();
                        qrCodeEventRequest.setFromUserName(fromUserName);
                        qrCodeEventRequest.setToUserName(toUserName);
                        qrCodeEventRequest.setCreateTime(Long
                                .parseLong(createTime));
                        qrCodeEventRequest.setMsgType(msgType);
                        qrCodeEventRequest.setEvent(eventType);

                        qrCodeEventRequest.setEventKey(eventKey);
                        qrCodeEventRequest.setTicket(ticket);

                        executor = map.get("scan-event");
                        Class<IWXEventExecutor> iwxExecutor = (Class<IWXEventExecutor>) Class
                                .forName(executor);
                        baseMessageResponse = iwxExecutor.newInstance()
                                .executor(qrCodeEventRequest);
                    }

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
                } else if (eventType.equals(WXConstants.EVENT_TYPE_SCAN)) {
                    // 处理已关注用户扫描带参数二维码事件
                    String eventKey = receiveXmlEntity.getEventKey();
                    String ticket = receiveXmlEntity.getTicket();

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
                    String latitude = receiveXmlEntity.getLatitude();
                    String longitude = receiveXmlEntity.getLongitude();
                    String precision = receiveXmlEntity.getPrecision();

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
                    Class<IWXEventExecutor> iwxExecutor = (Class<IWXEventExecutor>) Class
                            .forName(executor);
                    baseMessageResponse = iwxExecutor.newInstance().executor(
                            locationEventRequest);
                } else if (eventType.equals(WXConstants.EVENT_TYPE_CLICK)) {
                    // 处理点击菜单拉取消息
                    String eventKey = receiveXmlEntity.getEventKey();

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
                    String eventKey = receiveXmlEntity.getEventKey();

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
                    String msgID = receiveXmlEntity.getMsgId();
                    String status = receiveXmlEntity.getStatus();
                    String totalCount = receiveXmlEntity.getTotalCount();
                    String filterCount = receiveXmlEntity.getFilterCount();
                    String sentCount = receiveXmlEntity.getSentCount();
                    String errorCount = receiveXmlEntity.getErrorCount();

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
                if (StringUtils.isEmpty(encrypt_type)
                        || encrypt_type.equals("raw")) {
                    respXml = AuthProcess.encryptMsg(request, respXml);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }
}
