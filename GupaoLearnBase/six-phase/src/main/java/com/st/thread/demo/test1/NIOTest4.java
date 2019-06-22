/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Title: NIOTest4
 * @Description:
 * @Author zhujing
 * @Date 2019/6/15
 * @Version V1.0
 */
public class NIOTest4 {
    public static byte[] aaa = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) throws Exception{
        FileOutputStream fos = new FileOutputStream("D:\\testbbb.txt");

        FileChannel channel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(10);
        for(byte a : aaa){
            buffer.put(a);
        }
        buffer.flip();

        channel.write(buffer);
        fos.close();
    }
}
