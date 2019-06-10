/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test3;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: LockTest
 * @Description:
 * @Author zhujing
 * @Date 2019/5/21
 * @Version V1.0
 */
public class LockTest{

    private static Lock lock = new ReentrantLock();

    private static Condition putCondition = lock.newCondition();

    private static Condition tackeCondition = lock.newCondition();

    private LinkedBlockingQueue<Object> lbq = new LinkedBlockingQueue<>(5);

    private void put(Object data) throws Exception {
        lock.lock();
        try {
            if(lbq.size() == 5){
                System.out.println("---->" + Thread.currentThread().getName() + " putCondition await");
                putCondition.await();
            }
            tackeCondition.signal();
            lbq.put(data);
        } finally {
            lock.unlock();
        }
    }

    private Object take() throws Exception {
        lock.lock();
        try {
            if(lbq.isEmpty()){
                System.out.println("---->" + Thread.currentThread().getName() + " tackeCondition await");
                tackeCondition.await();
            }
            putCondition.signal();
            return lbq.take();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockTest test = new LockTest();
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                try {
                    test.put(new Object());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, " Thread[" + i + "]").start();
        }

        for(int x = 0; x < 10; x++){
            new Thread(() -> {
                try {
                    test.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "take Thread[" + x + "]").start();
        }
    }

}
