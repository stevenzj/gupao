/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test3;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.OutputStream;

/**
 * @Title: STHttpResponse
 * @Description:
 * @Author zhujing
 * @Date 2019/6/17
 * @Version V1.0
 */
public class STHttpResponse {
    private ChannelHandlerContext ctx;
    private HttpRequest request;

    public STHttpResponse(ChannelHandlerContext ctx, HttpRequest request) {
        this.ctx = ctx;
        this.request = request;
    }

    public void write(String data){
        try {
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(data.getBytes()));
            response.headers().set("Content-Type", "text/html");
            ctx.write(response);
            ctx.flush();
        } finally {
            ctx.close();
        }
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }
}
