package com.dt.learn.vertx.jdbc;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

/**
 * @author dt 2019/4/4 10:40
 */
public class VertMysql extends AbstractVerticle {
  @Override
  public void start() throws Exception{

    JsonObject config = new JsonObject()
      .put("host","")
      .put("port",3306)
      .put("maxPoolSize",1)
      .put("username","")
      .put("password","")
      .put("database","");

    AsyncSQLClient client = MySQLClient.createShared(vertx, config);

    client.getConnection(con -> {
      if(con.succeeded()){
        SQLConnection conn = con.result();
        conn.query("select * from MS_EST_WH_CITY_INFO",res -> {
          if(res.succeeded()){
            ResultSet result = res.result();
            System.out.println(result.getColumnNames().toString());
          }else {
            System.out.println("获取连接失败");
          }
        });
      }else{
        System.out.println("获取连接失败"+con.cause().toString());
      }
    });

    client.close(cl -> {
      if(cl.succeeded()){
        System.out.println("mysql 连接关闭成功");
      }else{
        System.out.println("mysql 连接关闭失败 "+cl.cause().toString());
      }
    });

  }
}
