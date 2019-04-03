package com.dt.learn.vertx.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

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

    router.route().handler(CookieHandler.create());

    // 创建一个 Session 
    LocalSessionStore session = LocalSessionStore.create(vertx);
    SessionHandler sessionHandler = SessionHandler.create(session);
    // 确保所有的请求都会经过 session 处理器
    router.route().handler(sessionHandler);

    router.route("/session").handler(routingContext -> {
      Session sessionContext = routingContext.session();
      sessionContext.put("foo","bar");
      routingContext.response().end(sessionContext.get("foo").toString());
    });

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
      routingContext.response().write("阻塞任务\n","UTF-8");
      routingContext.next();
    });

    // 基于路径前缀的路由
    router.route("/hello/*").handler(routingContext -> {
      System.out.println("基于路径前缀的路由!!!!!");
      routingContext.response().write("基于路径前缀的路由信息","UTF-8");
      routingContext.next();
    });

    // 捕捉路径参数 -> : 占位符
    router.route(HttpMethod.GET,"/getUser/:id").handler(routingContext -> {
      String id = routingContext.request().getParam("id");
      System.out.println("接收到的 id 为 = "+id);
      routingContext.response().write("接收到信息","UTF-8");
      routingContext.next();
    });

    // 基于正则表达式的路由
    router.route().pathRegex(".*foo").handler(routingContext -> {
      System.out.println(routingContext.request().path());
      System.out.println("正则进来了");
      routingContext.response().write("正则跳转路由","UTF-8");
      routingContext.next();
    });

    // 使用上下文获取数据
    router.get("/dt/path").handler(routingContext -> {
      routingContext.put("foo","bar");
      routingContext.next();
    });
    router.get("/dt/path").handler(routingContext -> {
      String bar = routingContext.get("foo");
      routingContext.response().end(bar);
    });

    // 错误处理
    router.get("/error/*").failureHandler(frc -> {
      System.out.println("错误....");
      frc.response().end();
    });


    // Cookie
    router.route("/cookie").handler(routingContext -> {
      System.out.println("进来了....");
      Cookie dtCookie = routingContext.getCookie("dt");
      String value = dtCookie.getValue();
      System.out.println(value);
      routingContext.addCookie(Cookie.cookie("dtt","dt"));
      routingContext.response().end("end...");
    }).failureHandler(frc -> {
//      frc.fail(400);
      frc.response().setStatusCode(400).end("error.....");
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
