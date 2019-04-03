package com.dt.learn.vertx;

import com.dt.learn.vertx.http.VertHttpClient;
import com.dt.learn.vertx.mongo.VertMongo;
import com.dt.learn.vertx.web.VertWeb;
import com.dt.learn.vertx.webClient.VertWebClient;
import io.vertx.core.Vertx;

/**
 * @author dt 2019/3/29 10:52
 * vert.x 启动类
 */
public class Main {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
//    vertx.deployVerticle(MainVerticle.class.getName());

//    vertx.deployVerticle(VertHttpClient.class.getName());

//    vertx.deployVerticle(VertWeb.class.getName());
//    vertx.deployVerticle(VertWebClient.class.getName());
    vertx.deployVerticle(VertMongo.class.getName());

  }

}
