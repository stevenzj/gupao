/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test2;

/**
 * @Title: SynchronizedDemo
 * @Description:
 * @Author zhujing
 * @Date 2019/5/12
 * @Version V1.0
 */
public class SynchronizedDemo implements Runnable{
    int x = 100;

    public synchronized void m1() {
        x = 1000;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("x=" + x + ", " + Thread.currentThread().getName());
    }

    public synchronized void m2() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        x = 2000;
        System.out.println("xx=" + x + ", " + Thread.currentThread().getName());
    }
    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo sd = new SynchronizedDemo();
        new Thread(()->sd.m1(), "Thread111").start();
        new Thread(()->sd.m2(), "Thread222").start();
        sd.m2();
        System.out.println("Main x=" + sd.x);
    }
    @Override
    public void run() {
        m1();
        //m2();
    }
}
