/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: ThreadPoolTest
 * @Description:
 * @Author zhujing
 * @Date 2019/5/27
 * @Version V1.0
 */
public class ThreadPoolTest implements Runnable {

    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    private static ExecutorService executorService1 = Executors.newSingleThreadExecutor();

    private static ExecutorService executorServic2 = Executors.newCachedThreadPool();

    private static ExecutorService executorServic3 = Executors.newScheduledThreadPool(3);

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        try {
            for(int i = 0; i < 1000; i++){
                executorService.execute(new ThreadPoolTest());
            }
        } finally {
            executorService.shutdown();
        }
    }
}
