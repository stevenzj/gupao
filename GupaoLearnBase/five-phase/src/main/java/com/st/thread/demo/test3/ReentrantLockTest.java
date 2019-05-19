/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test3;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: ReentrantLockTest
 * @Description:
 * @Author zhujing
 * @Date 2019/5/16
 * @Version V1.0
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        //lock.unlock();
    }
}
