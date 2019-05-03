/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.mybatis.test1.interceptor;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * @Title: TimeConsumeInterceptor
 * @Description:
 * @Author zhujing
 * @Date 2019/5/3
 * @Version V1.0
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})})
public class TimeConsumeInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //System.out.println("AAA");

        RoutingStatementHandler rsh = (RoutingStatementHandler) invocation.getTarget();

        long start = System.currentTimeMillis();
        System.out.println("被执行sql----->" + rsh.getBoundSql().getSql());
        System.out.println("参数----->" + rsh.getParameterHandler().getParameterObject());
        Object proceed = invocation.proceed();
        System.out.println("执行结果-----> " + proceed);
        System.out.println("执行耗时-----> " + (System.currentTimeMillis() - start));

        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
