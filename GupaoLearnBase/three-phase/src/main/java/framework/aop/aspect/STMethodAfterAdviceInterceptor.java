/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.aop.aspect;

import framework.aop.intercept.STMethodInterceptor;
import framework.aop.intercept.STMethodInvocation;

import java.lang.reflect.Method;

/**
 * @Title: STMethodAfterAdviceInterceptor
 * @Description:
 * @Author zhujing
 * @Date 2019/4/20
 * @Version V1.0
 */
public class STMethodAfterAdviceInterceptor extends STAbstractAspectAdvice implements STAdvice, STMethodInterceptor {

    private STJoinPoint joinPoint;

    public STMethodAfterAdviceInterceptor(Method aspectMethod, Object aspectObject) {
        super(aspectMethod, aspectObject);
    }

    @Override
    public Object invoke(STMethodInvocation invocation) throws Throwable {
        Object result = invocation.procced();
        this.joinPoint = invocation;
        this.afterReturn(result, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return result;
    }

    private void afterReturn(Object result, Method method, Object[] args, Object obj) throws Throwable{
        super.invokeAdviceMethod(joinPoint, result, null);
    }
}
