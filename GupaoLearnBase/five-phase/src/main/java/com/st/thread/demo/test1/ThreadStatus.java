/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test1;

import java.util.concurrent.TimeUnit;

/**
 * @Title: ThreadStatus
 * @Description:
 * @Author zhujing
 * @Date 2019/5/10
 * @Version V1.0
 */
public class ThreadStatus {

    public static void main(String[] args) {

        new Thread(() -> {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AAAAA").start();

        new Thread(() -> {
            while (true){
                synchronized (ThreadStatus.class){
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                /* 这样会报异常, https://blog.csdn.net/wangshuang1631/article/details/53815519
                try {
                    ThreadStatus.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }, "BBBBB").start();

        new Thread(new BlockedDemo(), "BlockedDemo11111").start();
        new Thread(new BlockedDemo(), "BlockedDemo22222").start();
    }

    static class BlockedDemo extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (BlockedDemo.class){
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
