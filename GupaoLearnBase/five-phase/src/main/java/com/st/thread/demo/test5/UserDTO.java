/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test5;

import java.io.Serializable;

/**
 * @Title: UserDTO
 * @Description:
 * @Author zhujing
 * @Date 2019/6/10
 * @Version V1.0
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 482971131272412839L;

    private transient String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
