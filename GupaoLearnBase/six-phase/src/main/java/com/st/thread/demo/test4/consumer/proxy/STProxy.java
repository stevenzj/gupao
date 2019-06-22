/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test4.consumer.proxy;

import com.st.thread.demo.test4.protocol.STProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Title: STProxy
 * @Description:
 * @Author zhujing
 * @Date 2019/6/20
 * @Version V1.0
 */
public class STProxy {

    public static  <T> T create(Class<?> clazz){
        STProxyInvoke invoke = new STProxyInvoke(clazz);
        Class[] clazzInterfaces = clazz.isInterface()
                ? new Class[]{clazz}
                : clazz.getInterfaces();
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazzInterfaces, invoke);
    }

    private static class STProxyInvoke implements InvocationHandler{

        private Class<?> clazz;

        public STProxyInvoke(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals(Object.class)){
                return method.invoke(this, args);
            }else {
                return rpcInvoke( new STProtocol(
                        this.clazz.getName(), method.getName(), method.getParameterTypes(), args) );
            }
        }

        public Object rpcInvoke(STProtocol protocol) {
            final ConsumerHandler consumerHandler = new ConsumerHandler();
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap client = new Bootstrap();
                client.group(group)
                        .channel(NioSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline pipeline = ch.pipeline();

                                pipeline.addLast(new LengthFieldPrepender(4));

                                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));

                                pipeline.addLast(new ObjectEncoder());

                                pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                                pipeline.addLast(consumerHandler);
                            }
                        });

                ChannelFuture future = client.connect("10.101.128.174", 8080).sync();
                future.channel().writeAndFlush(protocol).sync();
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
            return consumerHandler.getResponse();
        }
    }
}
