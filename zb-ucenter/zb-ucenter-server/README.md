### 该模块主要暴露接口服务，是一个独立的web应用，可以发布到tomcat进行暴露服务。
### 通过dubbo实现服务治理，dubbo使用zookeeper作为注册中心，暴露接口服务。


### zb-service-impl 是接口的实现，这里将实现类单独抽出成模块，是为了方便项目的结构化管理，当然，这里不作硬性要求，你们也可以按照自己的需求进行修改模块结构。个人习惯罢了。