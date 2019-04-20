/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.aop.aspect;

import framework.aop.intercept.STMethodInterceptor;
import framework.aop.intercept.STMethodInvocation;

import java.lang.reflect.Method;

/**
 * @Title: STMethodAfterThrowAdviceInterceptor
 * @Description:
 * @Author zhujing
 * @Date 2019/4/20
 * @Version V1.0
 */
public class STMethodAfterThrowAdviceInterceptor extends STAbstractAspectAdvice implements STMethodInterceptor, STAdvice {

    private STJoinPoint joinPoint;

    public STMethodAfterThrowAdviceInterceptor(Method aspectMethod, Object aspectObject) {
        super(aspectMethod, aspectObject);
    }

    @Override
    public Object invoke(STMethodInvocation invocation) throws Throwable {
        this.joinPoint = invocation;

        Object result = null;
        try {
            result = invocation.procced();
        } catch (Throwable throwable) {
            this.afterThrow(result, invocation.getMethod(), invocation.getArguments(), invocation.getThis(), throwable);
            throw throwable;
        }
        return result;
    }

    private void afterThrow(Object result, Method method, Object[] args, Object obj, Throwable throwable) throws Throwable{
        super.invokeAdviceMethod(joinPoint, result, throwable.getCause());
    }
}
