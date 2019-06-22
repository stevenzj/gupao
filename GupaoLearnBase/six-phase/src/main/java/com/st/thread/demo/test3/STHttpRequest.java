/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test3;

import io.netty.handler.codec.http.HttpRequest;

/**
 * @Title: STHttpRequest
 * @Description:
 * @Author zhujing
 * @Date 2019/6/17
 * @Version V1.0
 */
public class STHttpRequest {
    private HttpRequest request;

    public STHttpRequest(HttpRequest request) {
        this.request = request;
    }

    public String getUrl(){
        return request.uri();
    }

    public String getMethod(){
        return request.method().name();
    }
}
