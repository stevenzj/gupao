/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test2;

import java.io.IOException;

/**
 * @Title: SynchronizedDemo2
 * @Description:
 * @Author zhujing
 * @Date 2019/5/13
 * @Version V1.0
 */
public class SynchronizedDemo2 {
    static Integer count=0;
    public static void incr(){
        synchronized (count) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
        System.out.println("AAAAA: " + count);
    }
    public static void main(String[] args) throws Exception {
        for(int i=0;i<1000;i++){
            new Thread(()->SynchronizedDemo2.incr()).start();
        }
        Thread.sleep(5000);
        System.out.println("result:"+count);
    }
}
