/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Title: SocketServerDemo2
 * @Description:
 * @Author zhujing
 * @Date 2019/6/10
 * @Version V1.0
 */
public class SocketServerDemo2 {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            UserDTO userDTO = (UserDTO) ois.readObject();
            System.out.println(userDTO);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
