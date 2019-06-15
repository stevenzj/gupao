/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.io.*;
import java.net.Socket;

/**
 * @Title: BIOClient
 * @Description:
 * @Author zhujing
 * @Date 2019/6/13
 * @Version V1.0
 */
public class BIOClient {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        try {
            socket = new Socket("10.101.128.174", 8080);

            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println("hello");
            pw.flush();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String str = br.readLine();
            System.out.println("receive server info: " + str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                br.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
