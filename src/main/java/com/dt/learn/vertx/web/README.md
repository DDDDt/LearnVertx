## Vert.x Web 组件
Vert.x Web 是一系列用于基于 Vert.x 构建 Web 应用的构建模块  
Vert.x Core 提供了一系列底层的功能用于操作 HTTP, 对于一部分应用来是足够的.  
Vert.x Web 基于 Vert.x Core , 提供了一系列更丰富的功能以便更容易地开发实际的 Web 应用.  
Vert.x Web 来构建经典的服务端 Web 应用, RESTful 应用, 实时的 (服务端推送) Web 应用, 或任何
类型的您所能想到的 Web 应用. 应用类型的选择取决于您, 而不是 Vert.x Web  
Vert.x Web 非常适合编写 RESTful HTTP 微服务, 但我们不强制您必须把应用实现成这样. 

### Vert.x Web 的基本概念
#### 1. Router 
`Router` 是 Vert.x Web 的核心概念之一. 它是一个维护了零或多个 `Route` 的对象.
Router 接受 HTTP 请求, 并查找首个匹配该请求的 `Route`, 然后将请求传递给这个 `Route`   
`Route` 可以持有一个与之关联的处理器用于接收请求. 可以通过这个处理器对请求做一些事情, 
然后结束响应或者把请求传递给下一个匹配的处理器.  
做了和上文使用 Vert.x Core 实现的 HTTP 服务器基本相同的事情, 只是这一次换成了 Vert.x Web  
创建一个 HTTP 服务器, 然后创建了一个 `Router` . 在这之后, 我们创建了一个没有匹配条件的 `Route`, 
这个 route 会匹配所有到达这个服务器的请求.  
之后, 我们为这个 `route` 指定了一个处理器, 所有的请求都会调用这个处理器处理.  
调用处理器的参数是一个 `RoutingContext` 对象. 它不仅包含了 Vert.x 中标准的 `HttpServerRequest` 和
`HttpServerResponse`, 还包含了各种用于 Vert.x Web 使用的 东西.  
每一个被路由的请求对应一个唯一的 `RoutingContext`, 这个实例会被传递到所有处理这个请求的处理器上.  
当我们创建了处理器之后, 我们设置了 HTTP 服务器的请求处理器, 使所有的请求都通过 `accept` 处理.


