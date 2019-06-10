/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
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

    public static void main(String[] args) {
        /*int i = 10;
        System.out.println("2进制, " + Integer.toBinaryString(i));
        System.out.println("8进制, " + Integer.toOctalString(i));
        System.out.println("16进制, " + Integer.toHexString(i));

        i = i << 1;
        System.out.println("左移1位, " + Integer.toBinaryString(i));
        System.out.println("十进制, " + i);

        i = i >> 1;
        System.out.println("右移1位, " + Integer.toBinaryString(i));
        System.out.println("十进制, " + i);*/

        /*System.out.println("8的二进制: " + Integer.toBinaryString(8));
        System.out.println("3的二进制: " + Integer.toBinaryString(3));
        System.out.println("8^3: " + (8^3));
        // 1000
        // 0011
        // 1011
        System.out.println(Integer.valueOf("1011", 2));*/

        /*System.out.println("7的二进制: " + Integer.toBinaryString(7));
        System.out.println("3的二进制: " + Integer.toBinaryString(3));
        System.out.println("7&3: " + (7&3));
        // 0111
        // 0011
        // 0011
        System.out.println(Integer.valueOf("0011", 2));*/

        /*System.out.println("9的二进制: " + Integer.toBinaryString(9));
        System.out.println("4的二进制: " + Integer.toBinaryString(4));
        System.out.println("9|4: " + (9|4));
        // 1001
        // 0100
        // 1101
        System.out.println(Integer.valueOf("1101", 2));*/

        /*System.out.println("37的二进制: " + Integer.toBinaryString(37));
        System.out.println("~37: " + ~37);
        // 0010 0101
        // 1101 1010
        System.out.println(Integer.valueOf("11011010", 2));*/

        /*int i = 2 << 1;
        System.out.println(i);*/

        //System.out.println( Runtime.getRuntime().availableProcessors() );

        /*System.out.println("15的二进制: " + Integer.toBinaryString(15));// 0000 1111 & 0001 0100 = 0000 0100
        System.out.println("20的二进制: " + Integer.toBinaryString(20));// 0001 1111 & 0001 0100 = 0001 0100
        System.out.println("20&15结果: " + (20&15));

        System.out.println("31的二进制: " + Integer.toBinaryString(31));
        System.out.println("20的二进制: " + Integer.toBinaryString(20));
        System.out.println("20&31结果: " + (20&31));*/

        /*for(int i = 1; i < 17; i++){
            System.out.println(i&16);
        }

        System.out.println(Runtime.getRuntime().availableProcessors());*/

        /*System.out.println(-1 << 29);
        System.out.println(0 << 29);
        System.out.println(1 << 29);
        System.out.println(2 << 29);
        System.out.println(3 << 29);

        System.out.println((1 << 29) - 1);*/

        System.out.println("18的二进制: " + Integer.toBinaryString(18));
        System.out.println(Integer.valueOf("10010010", 2));


        System.out.println(Integer.valueOf("10101100", 2));

        System.out.println(1<<3|2);
        System.out.println(2<<3|0);

        System.out.println(Integer.valueOf("00111000", 2));

        System.out.println(Integer.valueOf("00000001", 2));
    }
}
