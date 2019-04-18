/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.zj.demo.test1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Title: Test1AAA
 * @Description:
 * @Author zhujing
 * @Date 2019/4/17
 * @Version V1.0
 */
@Component
public class TestAService {

    @Autowired
    private TestBService testBService;

    @Autowired
    private TestCService testCService;

}
