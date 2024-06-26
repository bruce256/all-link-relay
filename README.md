
### 工程说明

全链路透传merchantId，link_id信息，透传的链路包括rocketmq, dubbo, http

技术方案：https://blog.csdn.net/bruce128/article/details/123788596

该工程的一个重要依赖组件：[https://github.com/bruce256/saas-sql](https://github.com/bruce256/saas-sql),将租户id自动填充入sql中，原理参考pagehelper

### 部署
- 正式环境发布，版本号不带snapshot
- 正式环境和非正式环境deploy，都要升级版本号

部署命令
```shell
mvn deploy "-Dmaven.test.skip=true" -N
```

### 接入手册

1. 依赖引入
```xml
 <dependency>
     <groupId>com.bruce</groupId>
     <artifactId>all-link-relay</artifactId>
     <version>1.0.0-SNAPSHOT</version>
 </dependency>
```

2. mq 配置

向spring ioc容器注册两个切面对象
```java
@Bean
public MqSenderAspect mqSenderAspect(){
    return new MqSenderAspect();
}

@Bean
public MqReceiverAspect mqReceiverAspect(){
    return new MqReceiverAspect();
}
```

3. dubbo无需配置
4. http 待续