/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @Title: NIOServer
 * @Description:
 * @Author zhujing
 * @Date 2019/6/13
 * @Version V1.0
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(8080));
        // 非阻塞
        server.configureBlocking(false);

        // 选择器, 多路复用
        Selector selector = Selector.open();
        // 服务注册, 开始接收数据
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("----> 服务启动 <----");

        while (true){
            int select = selector.select();
            if(select == 0){
                continue;
            }
            Set<SelectionKey> keySet = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keySet.iterator();
            if(keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                keyIterator.remove();

                if(key.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel client = serverSocketChannel.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);

                    //将此对应的channel设置为准备接受其他客户端请求
                    //key.interestOps(SelectionKey.OP_ACCEPT);
                }
                else if(key.isReadable()){
                    try {
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        StringBuilder sb = new StringBuilder();
                        while ( client.read(buffer) > 0 ){
                            buffer.flip();
                            sb.append(Charset.forName("UTF-8").decode(buffer));
                        }
                        System.out.println("receive client info: " + sb.toString());

                        //key.interestOps(SelectionKey.OP_READ);

                        client.write(Charset.forName("UTF-8").encode("Received"));
                    } finally {
                        key.cancel();
                        key.channel().close();
                    }
                }
            }
        }
    }
}
