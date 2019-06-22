/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test4.provider;

import com.st.thread.demo.test4.api.ITestServiceA;

/**
 * @Title: TestServiceA
 * @Description:
 * @Author zhujing
 * @Date 2019/6/19
 * @Version V1.0
 */
public class TestServiceA implements ITestServiceA {
    @Override
    public String test(String data) {
        return "Hello " + data + "!";
    }
}
