/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test4.consumer;

import com.st.thread.demo.test4.api.ITestServiceA;
import com.st.thread.demo.test4.api.ITestServiceB;
import com.st.thread.demo.test4.consumer.proxy.STProxy;
import com.st.thread.demo.test4.protocol.STProtocol;

/**
 * @Title: STRpcConsumer
 * @Description:
 * @Author zhujing
 * @Date 2019/6/20
 * @Version V1.0
 */
public class STRpcConsumer {
    public static void main(String[] args) {
        ITestServiceA testServiceA = STProxy.create(ITestServiceA.class);
        System.out.println(testServiceA.test("steven"));

        ITestServiceB testServiceB = STProxy.create(ITestServiceB.class);
        System.out.println( testServiceB.add(8, 2) );

        System.out.println( testServiceB.subtract(8, 2) );

        System.out.println( testServiceB.multiply(8, 2) );

        System.out.println( testServiceB.divide(8, 2) );
    }
}
