/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.zj.demo.test1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Title: TestMain
 * @Description:
 * @Author zhujing
 * @Date 2019/4/17
 * @Version V1.0
 */
public class TestMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
        TestAService testAService = (TestAService) context.getBean("testAService");
    }
}
