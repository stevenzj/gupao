/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.mybatis.test1;

import com.st.mybatis.test1.entity.Account;
import com.st.mybatis.test1.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @Title: TestB
 * @Description:
 * @Author zhujing
 * @Date 2019/4/26
 * @Version V1.0
 */
public class TestB {
    public static void main(String[] args) throws Exception {
        String sourceStr = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(sourceStr);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        AccountMapper accountMapper = session.getMapper(AccountMapper.class);
        Account account = accountMapper.getById(1L);
        System.out.println(account);
    }
}
