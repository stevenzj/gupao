/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.mybatis.test1.entity;

/**
 * @Title: Account
 * @Description:
 * @Author zhujing
 * @Date 2019/4/26
 * @Version V1.0
 */
public class Account {

    private Long id;

    private String name;

    private String pwd;

    private Long passportId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", passportId=" + passportId +
                '}';
    }
}
