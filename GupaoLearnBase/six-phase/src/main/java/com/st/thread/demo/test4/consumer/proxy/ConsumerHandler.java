/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test4.consumer.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Title: ConsumerHandler
 * @Description:
 * @Author zhujing
 * @Date 2019/6/20
 * @Version V1.0
 */
public class ConsumerHandler extends ChannelInboundHandlerAdapter {
    private Object response;

    public Object getResponse() {
        return response;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        this.response = msg;
    }
}
