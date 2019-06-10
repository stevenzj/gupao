/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Title: SocketServerDemo1
 * @Description:
 * @Author zhujing
 * @Date 2019/5/30
 * @Version V1.0
 */
public class SocketServerDemo1 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            /*PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println();*/
            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
