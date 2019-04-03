package com.dt.learn.vertx.webClient;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;

/**
 * @author dt 2019/4/3 16:23
 */
public class VertWebClient extends AbstractVerticle {

  @Override
  public void start() throws Exception {

    WebClient webClient = WebClient.create(vertx);

    // 发送请求
    webClient.post(30619,"","/bigdata-marketing-service/labelTaskCondition/getPayWay")
      .send(ar -> {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if(ar.succeeded()){
          System.out.println(ar.result().bodyAsString());
        }else {
          System.out.println("error ar = "+ ar.cause().toString());
        }
      });
    // 带请求参数的信息
    webClient.get(30619,"","/bigdata-marketing-service/labelTask/getActivityEffectDataRecords")
      .addQueryParam("id","1")
      .send(ar -> {
        if(ar.succeeded()){
          System.out.println(ar.result().version());
          System.out.println(ar.result().bodyAsString());
        }else{
          System.out.println("error ar = "+ ar.cause().toString());
        }
      });

    // 填充请求体
    webClient.post(30619,"","/bigdata-marketing-service/labelTask/getMemberLabelTask")
      .timeout(5000)
      .sendJsonObject(new JsonObject().put("id",1),ar -> {
        if(ar.succeeded()){
          System.out.println(ar.result().bodyAsString());
        }else {
          System.out.println("error ar = "+ ar.cause().toString());
        }
      });

    // 将返回的 json 字符串映射成 pojo 对象

  }
}
