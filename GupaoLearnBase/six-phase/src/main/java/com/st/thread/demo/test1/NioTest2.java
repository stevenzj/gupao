/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.nio.ByteBuffer;

/**
 * @Title: NioTest2
 * @Description:
 * @Author zhujing
 * @Date 2019/6/14
 * @Version V1.0
 */
public class NioTest2 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for(int i = 0; i < buffer.capacity(); i++){
            buffer.put((byte) i);
        }

        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();
        for(int x = 0; x < slice.capacity(); x++){
            byte b = slice.get();
            b *= 10;
            slice.put(x, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }
    }
}
