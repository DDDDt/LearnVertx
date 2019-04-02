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

    // 追加到 buffer 后
    Buffer buffer1 = strBuffer.appendString(" hello dt");
    System.out.println(buffer1.toString());

    // 随机访问写 buffer -> 指定一个索引值
    Buffer buffer2 = buffer1.setString(1, " ddd ");
    System.out.println(buffer2.toString());
    System.out.println(buffer1.toString());
    System.out.println(strBuffer.toString());

    // 从 Buffer 中读取 -> getxxx 方法从 buffer 中读取数据
    System.out.println(strBuffer.length());
    for(int i = 0; i < strBuffer.length(); i++){
      System.out.println("值 = "+ strBuffer.getString(0,i));
    }

  }

}
