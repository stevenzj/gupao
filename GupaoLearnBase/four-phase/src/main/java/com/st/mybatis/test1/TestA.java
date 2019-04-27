/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.mybatis.test1;

import com.st.mybatis.test1.entity.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Title: TestA
 * @Description:
 * @Author zhujing
 * @Date 2019/4/26
 * @Version V1.0
 */
public class TestA {

    public static void main(String[] args) throws Exception {
        String resourceStr = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resourceStr);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);

        SqlSession session = sessionFactory.openSession();

        Account account = session.selectOne("com.st.mybatis.test1.mapper.AccountMapper.getById", "1");
        System.out.println(account);
    }

}
