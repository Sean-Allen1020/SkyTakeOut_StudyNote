### 介绍
- HttpClient 是 Apache Jakarta Common 下的子项目，可以用来提供高效的、最新的、功能丰富的支持 HTTP 协议的客户端编程工具包，并且它支持 HTTP 协议最新的版本和建议。

------------------------------------------------------------
### Maven 依赖
```xml
    <dependency>
        <groupId>org.apache.httpcomponents.client5</groupId>
        <artifactId>httpclient5</artifactId>
        <version>5.2.3</version>
    </dependency>
```
------------------------------------------------------------
### 核心 API
- HttpClient
- HttpClients
- CloseableHttpClient
- HttpGet
- HttpPost

  - `HttpClients`           ：工厂类，用于创建 HttpClient
  - `CloseableHttpClient`   ：常用实现，支持资源关闭
  - `HttpGet / HttpPost`    ：具体请求类型

------------------------------------------------------------

### 发送 HTTP 请求的基本流程

1. 创建 HttpClient 对象
2. 创建 Http 请求对象（HttpGet / HttpPost）
3. 调用 execute() 方法发送请求并获取响应

------------------------------------------------------------

### 典型使用场景
- 调第三方 API	    登录、支付、短信、地图
- 服务间调用	    微服务系统
- 模拟浏览器	    数据同步、报表
- HTTP 通信	        一切 Web 系统的基础