package com.dt.learn.vertx.buffer;

import io.vertx.core.buffer.Buffer;

/**
 * @author dt 2019/4/2 11:35
 */
public class VertBuffer {

  public static void main(String[] args) {

    // 创建一个控的 buffer
    Buffer buffer = Buffer.buffer();
    // 默认已 UTF-8
    Buffer strBuffer = Buffer.buffer("dt");
    System.out.println(strBuffer.toString());

    // 向 Buffer 写入数据


  }

}
