/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.io.FileInputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Title: NIOTest
 * @Description:
 * @Author zhujing
 * @Date 2019/6/14
 * @Version V1.0
 */
public class NIOTest {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("D:\\testaaa.txt");
        FileChannel channel = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);
        out("初始化", buffer);

        channel.read(buffer);
        out("调用read()", buffer);

        buffer.flip();
        out("调用flip()", buffer);

        // 判断是否还有数据可读, limit-position>0
        while (buffer.remaining() > 0){
            byte b = buffer.get();
            //System.out.println((char) b);
        }
        out("调用get()", buffer);

        buffer.clear();
        out("调用clear()", buffer);

        fis.close();
    }

    public static void out(String step, Buffer buffer){
        System.out.println(step
                + "----> position: " + buffer.position()
                + ", limit: " + buffer.limit()
                + ", capacity: " + buffer.capacity());
    }
}
