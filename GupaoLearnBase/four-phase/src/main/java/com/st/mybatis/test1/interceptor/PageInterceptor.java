/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.mybatis.test1.interceptor;

import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Properties;

/**
 * @Title: PageInterceptor
 * @Description:
 * @Author zhujing
 * @Date 2019/5/3
 * @Version V1.0
 */
//@Intercepts({ @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}) })
@Intercepts({ @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}) })
public class PageInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("AAA");

        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
        String sql = boundSql.getSql();

        RowBounds rowBounds = (RowBounds) args[2];
        if(rowBounds == RowBounds.DEFAULT){
            return invocation.proceed();
        }

        int offset = rowBounds.getOffset() <= 0 ? 0 : rowBounds.getOffset();
        int limit = rowBounds.getLimit() <= 0 ? 5 : rowBounds.getLimit();
        sql = sql + String.format(" limit %s,%s", offset, limit);

        // 只是帮助分页, 不知道是否有where条件, 所以需要加上参数
        SqlSource sqlSource = new StaticSqlSource(mappedStatement.getConfiguration(), sql, boundSql.getParameterMappings());

        // 没有where条件的sql可以使用这个SqlSource, 不带参数, 直接跟limit语句
        // SqlSource sqlSource = new StaticSqlSource(mappedStatement.getConfiguration(), sql);

        Field field = MappedStatement.class.getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(mappedStatement, sqlSource);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
