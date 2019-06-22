/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test2;

import java.io.InputStream;

/**
 * @Title: STHttpRequest
 * @Description:
 * @Author zhujing
 * @Date 2019/6/17
 * @Version V1.0
 */
public class STHttpRequest {
    private String method;

    private String url;

    public STHttpRequest(InputStream is){
        try {
            String content = "";
            byte[] buff = new byte[1024];
            int len;
            if((len = is.read(buff)) > 0){
                content = new String(buff, 0, len);
            }
            System.out.println(content);

            String line = content.split("\\n")[0];
            String [] arr = line.split("\\s");

            this.method = arr[0];
            this.url = arr[1].split("\\?")[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
