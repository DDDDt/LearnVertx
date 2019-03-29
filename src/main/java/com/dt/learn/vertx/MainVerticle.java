package com.dt.learn.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MainVerticle extends AbstractVerticle {

  // 属性变量
  int i = 0;

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    // handler 处理器 1
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startFuture.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startFuture.fail(http.cause());
      }
    });

    // handler 处理器 2
    vertx.createHttpServer().requestHandler(req -> {
      System.out.println("进来了开始----");
      i++;
      System.out.println(i);
      System.out.println("进来了结束-----");
      req.response().end(); // 要关闭请求, 否则连接很快会被占满
    }).listen(8080);

    // handler 处理器 3
    vertx.createHttpServer().requestHandler(req -> {
      System.out.println(i);
      req.response().end(""+i);
    }).listen(8081);

  }

}
