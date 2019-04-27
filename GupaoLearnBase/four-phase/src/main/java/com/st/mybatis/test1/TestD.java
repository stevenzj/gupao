/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.mybatis.test1;

import com.st.mybatis.test1.entity.Account;
import com.st.mybatis.test1.entity.AccountAndPassport;
import com.st.mybatis.test1.entity.Passport;
import com.st.mybatis.test1.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @Title: TestD
 * @Description:
 * @Author zhujing
 * @Date 2019/4/26
 * @Version V1.0
 */
public class TestD {
    public static void main(String[] args) throws Exception {
        String resourceStr = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resourceStr);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        Configuration configuration = session.getConfiguration();
        AccountMapper accountMapper = session.getMapper(AccountMapper.class);
        AccountAndPassport accountAndPassport = accountMapper.getAll(1L);
        System.out.println(accountAndPassport.getClass());

        //Passport passport = accountAndPassport.getPassport();
        //System.out.println(passport.getHobby());
    }
}
