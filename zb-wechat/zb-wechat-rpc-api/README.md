###wx-api为何诞生
现在微信越来越火，基于微信的公众号和服务号越来越丰富，虽然微信帮助文档已经提供了相关的接口，但是接口比较多，通过代码自己调用比较麻烦，所以为减轻开发者独自创造轮子，将微信API进行了统一封装！

###快速使用方法：
所有获取微信接口的方法都是静态方法，可以通过传递参数调用，API的包基本与微信开发者文档目录对应，通过英语单据，如参数有疑问，可以对照微信开发者帮助文档进行理解。

###扩展使用
由于微信开发的接口会不断的增多或者调整，开发者可以按照下面三步骤进行扩展：

A、添加请求参数对象，参考pers.zb.wxapi.core.req.model.menu.MenuCreate，定义自己的ReqType类型。

B、添加微信请求的配置信息zb-wxapi\src\main\resources\weixin-reqcongfig.xml，其中

Key：为定义的reqType类型

Method：为url请求方式，get/post

DataType：为参数形式，json或传统的url参数param

Url：为接口请求路径

MappingHandler：请求处理类，如果没有特殊处理，可以为空，如存在请求信息需要特殊处理，可以实现org.jeewx.api.core.handler.WeiXinReqHandler接口，开发者发送自己的请求获取微信数据。

C、类型和配置信息处理好后，通过
pers.zb.wxapi.core.req.WeiXinReqService.getInstance().doWeinxinReq(ReqType类型对象)进行调用。

###其他说明
由于此工具也是网络上搜索整理，如有问题请联系。
该工具主要是一些接口性质的请求解析，未有用户微信事件(比如关注、取消关注)、用户消息的操作处理。

该模块目前是独立的，未被其他项目引用。

代码出处：http://git.oschina.net/jeecg/jeewx-api
