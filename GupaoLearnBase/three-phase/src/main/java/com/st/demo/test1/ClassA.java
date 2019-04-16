/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.demo.test1;

import framework.annotation.GPAutowired;
import framework.annotation.GPController;

/**
 * @Title: ClassA
 * @Description:
 * @Author zhujing
 * @Date 2019/4/16
 * @Version V1.0
 */
@GPController
public class ClassA {

    @GPAutowired
    private ClassB classB;

    @GPAutowired
    private ClassC classC;

    public String getAAA(){
        return "AAA";
    }
}
