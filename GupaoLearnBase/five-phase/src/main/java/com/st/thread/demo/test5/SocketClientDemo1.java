/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test5;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @Title: SocketClientDemo1
 * @Description:
 * @Author zhujing
 * @Date 2019/5/30
 * @Version V1.0
 */
public class SocketClientDemo1 {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("10.101.128.174", 8080);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println("Hello");
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
