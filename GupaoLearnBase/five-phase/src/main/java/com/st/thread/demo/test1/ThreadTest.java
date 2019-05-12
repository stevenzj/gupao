/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.util.concurrent.TimeUnit;

/**
 * @Title: ThreadTest
 * @Description:
 * @Author zhujing
 * @Date 2019/5/12
 * @Version V1.0
 */
public class ThreadTest {

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("---->" + i);
        });

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
