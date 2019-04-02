package com.dt.learn.vertx.tcp;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * @author dt 2019/4/2 13:49
 * 创建 TCP 服务端和客户端
 */
public class VertTcp extends AbstractVerticle {

  @Override
  public void start() throws Exception {

    vertx.createNetServer();

  }
}
