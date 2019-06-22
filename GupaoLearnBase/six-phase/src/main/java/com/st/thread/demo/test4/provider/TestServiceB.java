/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test4.provider;

import com.st.thread.demo.test4.api.ITestServiceB;

/**
 * @Title: TestServiceB
 * @Description:
 * @Author zhujing
 * @Date 2019/6/19
 * @Version V1.0
 */
public class TestServiceB implements ITestServiceB {
    @Override
    public int add(int a, int b) {
        return (a + b);
    }

    @Override
    public int subtract(int a, int b) {
        return (a - b);
    }

    @Override
    public int multiply(int a, int b) {
        return (a * b);
    }

    @Override
    public int divide(int a, int b) {
        return (a / b);
    }
}
