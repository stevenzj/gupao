/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.demo.test2;

import framework.context.STApplicationContext;

/**
 * @Title: TestAAA
 * @Description:
 * @Author zhujing
 * @Date 2019/4/16
 * @Version V1.0
 */
public class TestAAA {

    public static void main(String[] args) {
        STApplicationContext applicationContext = new STApplicationContext("application.properties");
        System.out.println(applicationContext);
    }
}
