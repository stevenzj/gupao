/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.mybatis.test1;

import com.st.mybatis.test1.entity.AccountAndPassport;
import com.st.mybatis.test1.mapper.AccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @Title: TestE
 * @Description:
 * @Author zhujing
 * @Date 2019/4/27
 * @Version V1.0
 */
public class TestE {

    public static void main(String[] args) throws Exception {
        String resourceStr = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resourceStr);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        AccountMapper accountMapper = session.getMapper(AccountMapper.class);
        AccountAndPassport accountAndPassport = accountMapper.getAllNew(1L);
        System.out.println(accountAndPassport.getClass());

        // lazyLoadingEnabled=false时, 会触发2条sql
        // lazyLoadingEnabled=true时, 会在使用accountAndPassport对象时触发第2条sq, equals,clone,hashCode,toString也会触发延迟加载
        //System.out.println(accountAndPassport);

        // aggressiveLazyLoading=true时, 与accountAndPassport对象相关的所有方法都会触发延迟加载
        //System.out.println(accountAndPassport.getClass());
    }
}
