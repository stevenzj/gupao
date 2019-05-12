/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.util.concurrent.TimeUnit;

/**
 * @Title: ThreadTestDemo
 * @Description:
 * @Author zhujing
 * @Date 2019/5/11
 * @Version V1.0
 */
public class ThreadTestDemo {

    //https://www.cnblogs.com/skywang12345/p/3479949.html
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread111("Thread111");
        System.out.println("AAAAA, " + t1.getName() + ", " + t1.getState());

        t1.start();
        System.out.println("BBBBB, " + t1.getName() + ", " + t1.getState());

        Thread.sleep(300);
        t1.interrupt();
        System.out.println("CCCCC, " + t1.getName() + ", " + t1.getState());

        Thread.sleep(300);
        System.out.println("DDDDD, " + t1.getName() + ", " + t1.getState());
    }


    public static class Thread111 extends Thread{

        public Thread111(String name) {
            super(name);
        }

        @Override
        public void run() {
            try {
                int i = 0;
                while (!isInterrupted()){
                        Thread.sleep(100);
                    i++;
                    System.out.println("---->" + Thread.currentThread().getName()
                                    + ", state: " + this.getState()
                                    + ", i: " + i
                                    + ", time: " + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
