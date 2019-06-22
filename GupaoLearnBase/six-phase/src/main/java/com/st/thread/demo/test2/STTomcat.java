/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
                    String servletName = key.replaceAll("\\.url$", "");
                    String className = webProp.getProperty(servletName + ".className");

                    String url = webProp.getProperty(key);
                    STServlet servletObj = (STServlet) Class.forName(className).newInstance();
                    mapping.put(url, servletObj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            init();

            ServerSocket server = new ServerSocket(port);
            System.out.println("服务启动, 端口: " + port);

            while (true){
                Socket socket = server.accept();
                handle(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle(Socket socket) {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            STHttpRequest request = new STHttpRequest(is);
            STHttpResponse response = new STHttpResponse(os);

            if(mapping.containsKey(request.getUrl())){
                mapping.get(request.getUrl()).service(request, response);
            }else {
                response.write("404 NOT FOUND");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new STTomcat().start();
    }
}
