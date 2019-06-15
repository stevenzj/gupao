/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Title: BIOServer
 * @Description:
 * @Author zhujing
 * @Date 2019/6/13
 * @Version V1.0
 */
public class BIOServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8080);
            socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str = br.readLine();
            System.out.println("receive client info: " + str);

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println(str);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
