package com.dt.learn.vertx.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * @author dt 2019/4/2 18:19
 * Vert web 服务
 */
public class VertWeb extends AbstractVerticle {

  @Override
  public void start() throws Exception {

    HttpServerOptions httpServerOptions = new HttpServerOptions().setLogActivity(true);
    //  路由
    Router router = Router.router(vertx);

    // 没有现成阻塞
    router.route("/hello").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      // 由于我们会在不同的处理器写入响应, 因此需要弃用分块传输
      // 仅当需要通过多个处理器输出响应式才需要
      response.setChunked(true);
      response.write("hello \n");
      // 5s 后调用下一个处理器
      routingContext.vertx().setTimer(5000, tid -> routingContext.next());
    });

    // 没有现成阻塞
    router.route("/hello").handler(routingContext -> {
      HttpServerResponse response = routingContext.response();
      // 由于我们会在不同的处理器写入响应, 因此需要弃用分块传输
      // 仅当需要通过多个处理器输出响应式才需要
      response.setChunked(true);
      response.write("hello world \n");
      // 5s 后调用下一个处理器
      routingContext.vertx().setTimer(5000, tid -> routingContext.next());
    });

    // 阻塞式处理器 -> 默认情况下在一个 Context 上执行所有的阻塞式处理器的执行时顺序的。
    // 并且不介意阻塞式处理器以并行的方式执行，您可以在调用 blockingHandler 方法时将 ordered 设置为 false。
    router.route("/hello").blockingHandler(routingContext -> {

      // 某些同步的耗时操作
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      routingContext.response().write("阻塞任务\n","utf-8");
      routingContext.next();
    });

    router.route().handler(routingContext -> {
      //  所有的请求都会调用这个处理器处理
      HttpServerResponse response = routingContext.response();
      response.putHeader("content-type","text/plain");
      // 写入响应并结束处理
      response.end("Hello World from Vert.x-Web!");
    });
    vertx.createHttpServer(httpServerOptions).requestHandler(router::accept).listen(8090);

  }
}
