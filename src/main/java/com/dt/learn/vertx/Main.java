package com.dt.learn.vertx;

import com.dt.learn.vertx.http.VertHttpClient;
import io.vertx.core.Vertx;

/**
 * @author dt 2019/3/29 10:52
 * vert.x 启动类
 */
public class Main {

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(MainVerticle.class.getName());

//    vertx.deployVerticle(VertHttpClient.class.getName());

  }

}
