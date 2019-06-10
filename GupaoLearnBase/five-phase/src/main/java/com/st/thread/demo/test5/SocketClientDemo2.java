/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test5;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Title: SocketClientDemo2
 * @Description:
 * @Author zhujing
 * @Date 2019/6/10
 * @Version V1.0
 */
public class SocketClientDemo2 {

    public static void main(String[] args) {
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setName("steven");
            userDTO.setAge(18);

            Socket socket = new Socket("10.101.128.174", 8080);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(userDTO);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
