/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test2;

/**
 * @Title: VolatileDemo
 * @Description:
 * @Author zhujing
 * @Date 2019/5/13
 * @Version V1.0
 */
public class VolatileDemo {

    private volatile static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            System.out.println(flag);
            while (!flag){
                i++;
            }
            System.out.println(i);
        });

        thread.start();
        Thread.sleep(1000);
        flag = true;
    }
}
