/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.demo.test1;

import framework.annotation.STAutowired;
import framework.annotation.STController;

/**
 * @Title: ClassA
 * @Description:
 * @Author zhujing
 * @Date 2019/4/16
 * @Version V1.0
 */
@STController
public class ClassA {

    @STAutowired
    private ClassB classB;

    @STAutowired
    private ClassC classC;

    public String getAAA(){
        return "AAA";
    }
}
