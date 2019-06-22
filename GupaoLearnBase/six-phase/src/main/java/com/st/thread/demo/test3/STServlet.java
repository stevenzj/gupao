/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test3;

/**
 * @Title: STServlet
 * @Description:
 * @Author zhujing
 * @Date 2019/6/17
 * @Version V1.0
 */
public abstract class STServlet {
    public void service(STHttpRequest request, STHttpResponse response) throws Exception{
        if(request.getMethod().equalsIgnoreCase("get")){
            doGet(request, response);
        }else {
            doPost(request, response);
        }

    }

    protected abstract void doGet(STHttpRequest request, STHttpResponse response) throws Exception;

    protected abstract void doPost(STHttpRequest request, STHttpResponse response) throws Exception;
}
