/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.nio.ByteBuffer;

/**
 * @Title: NIOTest3
 * @Description:
 * @Author zhujing
 * @Date 2019/6/14
 * @Version V1.0
 */
public class NIOTest3 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for(int i = 0; i < buffer.capacity(); i++){
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        for(int i = 0; i < buffer.capacity(); i++){
            byte b = buffer.get(i);
            b *= 10;
            buffer.put(i, b);
        }

        readOnlyBuffer.position(0);
        readOnlyBuffer.limit(buffer.capacity());

        while (readOnlyBuffer.remaining() > 0){
            System.out.println(readOnlyBuffer.get());
        }

        /*System.out.println("----------------------------------------------");

        for(int i = 0; i < readOnlyBuffer.capacity(); i++){
            byte b = buffer.get(i);
            b *= 10;
            readOnlyBuffer.put(i, b);
        }

        while (readOnlyBuffer.remaining() > 0){
            System.out.println(readOnlyBuffer.get());
        }*/
    }
}
