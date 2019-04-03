## Web Client
- Pump : 泵(平滑流式数据读入内存的机制, 防止一次性将大量数据读入内存导致内存溢出
- Response Codec : 响应编解码器(编码及解码工具)
- Body Codec : 响应提编解码器

#### 组件介绍
Vert.x Web Client (Web 客户端) 是一个异步的 HTTP 和 HTTP/2 客户端.  
Web Client 使得发送 HTTP 请求以及从 Web 服务器接收 HTTP 响应变得更加便捷, 同时提供了额外的高级功能.
- JSON 体的编码和解码
- 请求和响应泵
- 请求参数的处理
- 统一的错误处理
- 提交表单
