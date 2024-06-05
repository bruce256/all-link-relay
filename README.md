
### 工程说明

全链路透传merchantId，link_id信息，透传的链路包括rocketmq, dubbo, http


日常开发分支：main

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

向spring iocr容器注册两个切面对象
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