/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.aop;

import framework.aop.intercept.STMethodInvocation;
import framework.aop.support.STAdviseSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @Title: STJdkDynamicAopProxy
 * @Description:
 * @Author zhujing
 * @Date 2019/4/20
 * @Version V1.0
 */
public class STJdkDynamicAopProxy implements STAopProxy, InvocationHandler {

    private STAdviseSupport advise;

    public STJdkDynamicAopProxy(STAdviseSupport config) {
        this.advise = config;
    }

    @Override
    public Object getProxy() {
        return this.getProxy(this.advise.getTargetClazz().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.advise.getTargetClazz().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> cached = this.advise.getInterceptorsAndDynamicInterceptionAdvice(method, advise.getTargetClazz());
        STMethodInvocation invocation = new STMethodInvocation(
                proxy, method, advise.getTarget(), args, cached, advise.getTargetClazz());
        return invocation.procced();
    }
}
