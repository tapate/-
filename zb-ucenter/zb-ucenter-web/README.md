###这是一个java web后台项目.可部署到tomcat服务器访问.

###项目中使用到的服务，比如：mysql、redis、dubbo、zookeeper、activemq、activiti等，都是连接我的服务器，如果有兴趣，建议自己搭建环境，提升自己技术能力。

###【其他说明】：
项目中的[实时监控]功能，如果控制台报错No suitable default RequestUpgradeStrategy found，原因是tomcat版本低，请检查tomcat\lib目录下是否有tomcat7-websocket.jar。

项目之前是单应用的登录页面，之后加入了CAS单点登录认证功能（与shiro进行集成），所以以前的登录页面已经不再使用了，需要登录的时候，自动会跳转到CAS认证中心，由CAS进行认证授权。

登陆的会话是存放在redis缓存中的，不再是session，好处我想各位都想的到。




###【分布式配置管理中心】
当前项目集成了百度开源的分布式配置管理框架disconf，将各模块项目中的配置文件（不限于properties，也可以是xml等）统一管理，所有子模块项目无须一一修改配置，在disconf的控制中心修改配置文件后，服务器上项目的配置文件会自动reload，实现实时更新，无需重新打包或重启，即可实时生效。

【特别说明】
实际操作过程中，我本身有这样的感触，对于像jdbc、redis、zookeeper这样的与spring集成的服务，是不适应的，因为无法动态刷新spring上下午中的相关连接信息。官方不建议对这些重连接进行管理修改，只让disconf托管即可(就是在控制中心查看的意思，当然修改也是可以的，只不过系统中不生效)。

disconf与spring集成的配置请查看 /zb-web/src/main/resources/application/spring.xml中最下方配置。
disconf所需基础配置，请查看 /zb-conf/src/main/resources/disconf.properties

disconf肯定需要一个web控制中心在线管理配置文件，环境需要搭建，我自己已经在服务器上搭建好了(如何搭建，这就只能自行看官方文档了)，可以访问 http://www.2b2b92b.com:8081/ 查看下效果，登陆账户和密码默认都是admin

disconf源码地址  https://github.com/knightliao/disconf
disconf官方文档  http://disconf.readthedocs.io



###[问题咨询]
如有问题，可以联系我，为你解答。QQ842324724   QQ技术群470414533   邮箱842324724@qq.com