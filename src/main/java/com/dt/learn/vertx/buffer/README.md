## Buffer
在 Vert.x 内部, 大部分数据被重新组织( shuffle , 表意为洗牌) 成 `Buffer` 格式.  
一个 `Buffer` 是可以读取或写入的 0 个或多个字节序列, 并且根据需要可以自动扩容, 将任意
字节写入 `Buffer` . 也可以将 `Buffer` 想象成字节数组.

### 创建 Buffer
可以使用静态方法 `Buffer.buffer` 来创建 Buffer  
`Buffer` 可以从字符串或字节数组初始化, 或者直接创建空的 `Buffer`.  

###向 Buffer 写入数据
向 `buffer` 写入数据的方式有两种: 追加和随机写入. 任何一种情况下 `buffer` 都会自动进行扩容, 
所以不可能在使用 `buffer` 时遇到 `IndexOutBoundsException`.
#### 1. 追加到 Buffer
`appendxxx` 方法追加数据到 `Buffer`.
#### 2. 随机访问写 Buffer
`setxxx` 方法写入数据到 `Buffer` , 通过指定索引值的方法.

