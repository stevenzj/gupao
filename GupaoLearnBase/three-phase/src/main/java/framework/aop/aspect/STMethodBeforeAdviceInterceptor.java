/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.aop.aspect;

import framework.aop.intercept.STMethodInterceptor;
import framework.aop.intercept.STMethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Title: STMethodBeforeAdviceInterceptor
 * @Description:
 * @Author zhujing
 * @Date 2019/4/20
 * @Version V1.0
 */
public class STMethodBeforeAdviceInterceptor extends STAbstractAspectAdvice implements STAdvice, STMethodInterceptor {

    private STJoinPoint joinPoint;

    public STMethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectObject) {
        super(aspectMethod, aspectObject);
    }

    private void before(Method method,Object[] args,Object target) throws Throwable{
        super.invokeAdviceMethod(this.joinPoint,null,null);

    }

    @Override
    public Object invoke(STMethodInvocation invocation) throws Throwable {
        this.joinPoint = invocation;
        before(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return invocation.procced();
    }
}
