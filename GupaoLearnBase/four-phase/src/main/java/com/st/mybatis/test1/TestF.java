/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.mybatis.test1;

import com.st.mybatis.test1.entity.Passport;
import com.st.mybatis.test1.mapper.PassportMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Title: TestF
 * @Description:
 * @Author zhujing
 * @Date 2019/4/27
 * @Version V1.0
 */
public class TestF {

    public static void main(String[] args) throws IOException {
        String resourceStr = "mybatis-config.xml";
        InputStream is = Resources.getResourceAsStream(resourceStr);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();

        PassportMapper mapper = session.getMapper(PassportMapper.class);

        RowBounds rowBounds = new RowBounds(0, 5);
        List<Passport> passportList = mapper.getList(rowBounds);
        System.out.println(passportList);
    }
}
