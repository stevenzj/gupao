/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test2;

import java.io.OutputStream;

/**
 * @Title: STHttpResponse
 * @Description:
 * @Author zhujing
 * @Date 2019/6/17
 * @Version V1.0
 */
public class STHttpResponse {
    private OutputStream os;
    public STHttpResponse(OutputStream os){
        this.os = os;
    }

    public void write(String data) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK\n").append("Content-Type: text/html;\n").append("\r\n").append(data);
        os.write(sb.toString().getBytes());
    }
}
