/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

/**
 * @Title: NIOClient
 * @Description:
 * @Author zhujing
 * @Date 2019/6/13
 * @Version V1.0
 */
public class NIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("10.101.128.174", 8080));
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);

        String uuid = UUID.randomUUID().toString();
        socketChannel.write(Charset.forName("UTF-8").encode(uuid));

        if(selector.select() != 0){
            Set<SelectionKey> keySet = selector.keys();
            Iterator<SelectionKey> keyIterator = keySet.iterator();
            if(keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                //keyIterator.remove();

                try {
                    if(key.isReadable()){
                        SocketChannel sc = (SocketChannel)key.channel();

                        StringBuilder sb = new StringBuilder();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        while (sc.read(buffer) > 0) {
                            buffer.flip();
                            sb.append(Charset.forName("UTF-8").decode(buffer));
                        }

                        System.out.println(sb.toString());
                    }
                } finally {
                    key.cancel();
                    key.channel().close();
                }
            }
        }


    }
}
