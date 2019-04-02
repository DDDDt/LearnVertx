package com.dt.learn.vertx.http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;

/**
 * @author dt 2019/4/2 14:13
 * httpClient
 */
public class VertHttpClient extends AbstractVerticle {

  @Override
  public void start() throws Exception {

    // 创建默认的 HttpClient
//    HttpClient httpClient = vertx.createHttpClient();

    // 创建默认的客户端选项
    HttpClientOptions httpClientOptions = new HttpClientOptions().setKeepAlive(false)
      .setLogActivity(true).setDefaultHost("localhost");
    HttpClient httpClient = vertx.createHttpClient(httpClientOptions);

    httpClient.getNow("8888","/some-uri",res -> {
      System.out.println("接口到的信息 Code" + res.statusCode());
    });


  }
}
