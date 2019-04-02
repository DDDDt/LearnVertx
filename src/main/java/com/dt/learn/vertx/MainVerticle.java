package com.dt.learn.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

  // 属性变量
  int i = 0;

  @Override
  public void start(Future<Void> startFuture) throws Exception {

    HttpServerOptions httpServerOptions = new HttpServerOptions().setLogActivity(true);

    // handler 处理器 1
    vertx.createHttpServer(httpServerOptions).requestHandler(req -> {
      System.out.println(req.path());
      System.out.println(req.headers().get("user-agent"));
      req.handler(buffer -> {
        System.out.println("接收到的值"+buffer.length());
      });
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
      req.endHandler(v -> {
        System.out.println("结束");
      });
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

    // 一次性计时器
    vertx.setTimer(100,id -> {
      System.out.println("一次性数据,id = "+id);
    });

    // 周期性计时器
    long timerId = vertx.setPeriodic(1000,id -> {
      System.out.println("周期性计时器，id = "+id);
    });

    System.out.println(timerId);

    // Event Bus -> 对于每一个 Vert.x 实例来说它是单例的
    EventBus eventBus = vertx.eventBus();
    // 注册处理器
    MessageConsumer<Object> consumer = eventBus.consumer("new.uk.sport", message -> {
      System.out.println("接收到一个消息: message = " + message.body());
    });

    // 在集群模式下的 Event Bus
    consumer.completionHandler(res -> {
      if(res.succeeded()){
        System.out.println("成功......");
      }else{
        System.out.println("失败......");
      }
    });

    // 发布消息 -> `publish` 方法指定一个地址去发布
    eventBus.publish("new.uk.sport","发步消息");

    // 发送消息
    eventBus.send("new.uk.sport","发送信息");

    // 设置消息头 -> 通过 `DeliveryOptions` 来指定
    DeliveryOptions options = new DeliveryOptions();
    options.addHeader("some-header","some-value");
    eventBus.send("new.uk.sport","带有请求头发送消息的",options);

    // 应答消息和发送回复
    MessageConsumer<Object> consumer1 = eventBus.consumer("news.uk.sport");
    consumer1.handler(message -> {
      System.out.println("收到一个消息并回复,message = "+message.body());
      message.reply("回复");
    });

    // 发送
    eventBus.send("news.uk.sport","发送信息并看有无回复",ar -> {
      if(ar.succeeded()){
        System.out.println("接受到回复信息: "+ar.result().body());
      }
    });

  }

}
