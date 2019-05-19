/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Title: ReentrantReadWriteLockTest
 * @Description:
 * @Author zhujing
 * @Date 2019/5/16
 * @Version V1.0
 */
public class ReentrantReadWriteLockTest {

    static Map<String, Object> dataMap = new ConcurrentHashMap<>();

    static ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();

    static Lock readLock = rrwl.readLock();

    static Lock writeLock = rrwl.writeLock();

    public static final Object getObject(String key){
        readLock.lock();
        try {
            return dataMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static final void setObject(String key, Object obj){
        writeLock.lock();
        try {
            dataMap.put(key, obj);
        } finally {
            writeLock.unlock();
        }
    }
}
