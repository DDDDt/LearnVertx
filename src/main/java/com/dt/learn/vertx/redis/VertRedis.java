package com.dt.learn.vertx.redis;

import io.vertx.core.AbstractVerticle;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;

/**
 * @author dt 2019/4/3 18:49
 */
public class VertRedis extends AbstractVerticle {

  @Override
  public void start() throws Exception{

    RedisOptions config = new RedisOptions()
      .setHost("")
      .setPort(6379)
      .setAuth("");

    RedisClient redisClient = RedisClient.create(vertx, config);



    redisClient.close(req -> {
      if(req.succeeded()){
        System.out.println("关闭成功...");
      }else{
        System.out.println("关闭失败.."+req.cause().toString());
      }
    });
  }

}
