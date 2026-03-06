# WebSocket 笔记

## WebSocket 是什么
`WebSocket` 是基于 TCP 的一种网络协议。它实现了浏览器与服务器的全双工通信：浏览器和服务器只需要完成一次握手，两者之间就可以创建**持久性连接**，并进行**双向数据传输**。

## HTTP 和 WebSocket 对比
- `HTTP` 是**短连接**
- `WebSocket` 是**长连接**
- `HTTP` 通信是**单向**的，基于请求-响应模式
- `WebSocket` 支持**双向**通信
- `HTTP` 和 `WebSocket` 底层都是 TCP 连接

## 应用场景
- 视频弹幕
- 网页聊天
- 体育实况更新
- 股票/基金报价实时更新

## 总结（修正版）
也就是说，`WebSocket` 建立连接后，**客户端和服务器都可以在需要时主动发送消息**；不必像 `HTTP` 那样必须由客户端发起请求后服务器才能响应。

------------------------

## 实现步骤
1. 直接使用 `websocket.html` 页面作为 WebSocket 客户端  
2. 导入 WebSocket 的 Maven 坐标  
3. 编写/导入 WebSocket 服务端组件 `WebSocketServer`，用于与客户端通信  
4. 编写/导入配置类 `WebSocketConfiguration`，注册 WebSocket 的服务端组件  
5. 编写/导入定时任务类 `WebSocketTask`，定时向客户端推送数据  
   - 说明：**第 5 步主要是为了测试才使用 Spring Task**（验证服务端推送是否正常）

## Maven 依赖
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```