package com.dt.learn.vertx.mongo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;

/**
 * @author dt 2019/4/3 17:30
 */
public class VertMongo extends AbstractVerticle {

  @Override
  public void start() throws Exception {

    JsonObject config = new JsonObject()
      .put("connection_string","mongodb://userName:pwd3967@ip:port/admin");

    MongoClient mongo = MongoClient.createShared(vertx, config);

    JsonObject document = new JsonObject().put("title", "the hobbit");
    mongo.save("test_vertx",document,req -> {
      if(req.succeeded()){
        String id = req.result();
        System.out.println("id = "+id);
      }else {
        req.cause().printStackTrace();
      }
    });

  }
}
