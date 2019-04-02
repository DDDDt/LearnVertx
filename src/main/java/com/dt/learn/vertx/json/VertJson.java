package com.dt.learn.vertx.json;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dt 2019/4/1 11:25
 * vert.x 自带的 json
 */
public class VertJson {

  public static void main(String[] args) {

    //创建 JSON 对象
    String jsonString = "{\"foo\":\"bar\"}";
    JsonObject json = new JsonObject(jsonString);
    System.out.println(json.getString("foo"));

    // 通过 Map 创建 JSON 对象
    Map<String,Object> map = new HashMap<>(2);
    map.put("foo","bar");
    map.put("xyz","3");
    JsonObject entries = new JsonObject(map);
    System.out.println(entries.toString());

    // 将键值对放入 JSON 对象，使用 put 方法可以将值放入到 JSON 对象里.
    entries.put("num",123);
    System.out.println(entries.toString());

    // 常见 JSON 数组
    String jsonStringArray = "[\"foo\",\"bar\"]";
    JsonArray array = new JsonArray(jsonStringArray);
    System.out.println(array.toString());

  }

}
