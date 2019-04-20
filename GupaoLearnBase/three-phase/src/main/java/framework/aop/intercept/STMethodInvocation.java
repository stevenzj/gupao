/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.aop.intercept;

import framework.aop.aspect.STJoinPoint;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Title: STMethodInvocation
 * @Description:
 * @Author zhujing
 * @Date 2019/4/20
 * @Version V1.0
 */
public class STMethodInvocation implements STJoinPoint {

    private Object proxy;
    private Method method;
    private Object target;
    private Object [] arguments;
    // 调用链
    private List<Object> interceptorsAndDynamicMethodMatchers;
    private Class<?> targetClass;

    //定义一个索引，从-1开始来记录当前拦截器执行的位置
    private int currentInterceptorIndex = -1;

    public STMethodInvocation(Object proxy, Method method, Object target, Object[] arguments,
                              List<Object> interceptorsAndDynamicMethodMatchers, Class<?> targetClass) {
        this.proxy = proxy;
        this.method = method;
        this.target = target;
        this.arguments = arguments;
        this.interceptorsAndDynamicMethodMatchers = interceptorsAndDynamicMethodMatchers;
        this.targetClass = targetClass;
    }

    public Object procced() throws Throwable{
        if(this.currentInterceptorIndex == interceptorsAndDynamicMethodMatchers.size() - 1){
            this.method.invoke(this.target, this.arguments);
        }

        Object interceptorOrInterceptionAdvice = this.interceptorsAndDynamicMethodMatchers.get(++currentInterceptorIndex);
        if(interceptorOrInterceptionAdvice instanceof STMethodInterceptor){
            STMethodInterceptor methodInterceptor = (STMethodInterceptor) interceptorOrInterceptionAdvice;
            // 如果它是调用链中的一环节, 则执行此调用链
            return methodInterceptor.invoke(this);
        }else {
            // 不是则递归, 直至执行自己的方法
            return procced();
        }
    }

    @Override
    public Object getThis() {
        return this.target;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public void setUserAttribute(String key, Object value) {

    }

    @Override
    public Object getUserAttribute(String key) {
        return null;
    }
}
