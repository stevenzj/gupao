/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test2;

/**
 * @Title: OneServlet
 * @Description:
 * @Author zhujing
 * @Date 2019/6/17
 * @Version V1.0
 */
public class OneServlet extends STServlet{
    @Override
    protected void doGet(STHttpRequest request, STHttpResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(STHttpRequest request, STHttpResponse response) throws Exception {
        response.write("This is One Servlet");
    }
}
