/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.mybatis.test1.entity;

/**
 * @Title: Passport
 * @Description:
 * @Author zhujing
 * @Date 2019/4/26
 * @Version V1.0
 */
public class Passport {

    private Long passportId;

    private int age;

    private String hobby;

    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "passportId=" + passportId +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
