/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test3;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Title: STTomcat
 * @Description:
 * @Author zhujing
 * @Date 2019/6/17
 * @Version V1.0
 */
public class STTomcat {

    private int port = 8080;

    private Properties webProp = new Properties();

    private Map<String, STServlet> mapping = new HashMap<>();

    public void init(){
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("web.properties");
            webProp.load(is);

            for (Object o : webProp.keySet()) {
                String key = o.toString();
                if(key.endsWith(".url")){
                    String className = key.replaceAll("\\.url$", "");
                    String property = webProp.getProperty(className + ".className");
                    STServlet servlet = (STServlet) Class.forName(property).newInstance();

                    String url = webProp.getProperty(key);
                    mapping.put(url, servlet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        init();

        // 主线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        // 子线程
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workGroup)
                    // 主线程处理类
                    .channel(NioServerSocketChannel.class)
                    // 子线程处理类, 业务处理
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 出去的数据需要被加密
                            ch.pipeline().addLast(new HttpResponseEncoder());

                            // 进来的数据需要被解密
                            ch.pipeline().addLast(new HttpRequestDecoder());

                            // 自己的业务处理逻辑
                            ch.pipeline().addLast(new STTomcatHandle());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = server.bind(port).sync();
            System.out.println("服务启动, 端口: " + port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    private class STTomcatHandle extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if(msg instanceof HttpRequest){
                HttpRequest req = (HttpRequest) msg;

                STHttpRequest request = new STHttpRequest(req);
                STHttpResponse response = new STHttpResponse(ctx, req);

                if(mapping.containsKey(request.getUrl())){
                    mapping.get(request.getUrl()).service(request, response);
                }else {
                    response.write("404 NOT FOUND");
                }
            }
        }
    }

    public static void main(String[] args) {
        new STTomcat().start();
    }

}
