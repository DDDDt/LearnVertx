package com.dt.learn.vertx.client;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.client.WebClient;

/**
 * @author dt 2019/3/29 13:26
 * Client
 */
public class VerticleClient extends AbstractVerticle {

  HttpClient httpClient;

  WebClient webClient;

  JDBCClient jdbcClient;

  @Override
  public void start() throws Exception {

    httpClient = vertx.createHttpClient();

    webClient = WebClient.wrap(httpClient);

    JsonObject config = new JsonObject()
      .put("jdbcUrl","")
      .put("maximumPoolSize", 30)
      .put("username", "db user name")
      .put("password", "***")
      .put("provider_class", "...");

    jdbcClient = JDBCClient.createShared(vertx, config);

  }
}
