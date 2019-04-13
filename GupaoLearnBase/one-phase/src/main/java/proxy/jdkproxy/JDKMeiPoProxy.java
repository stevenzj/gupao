/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.jdkproxy;

import proxy.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Title: JDKMeiPoProxy
 * @Description:
 * @Author zhujing
 * @Date 2019/4/8
 * @Version V1.0
 */
public class JDKMeiPoProxy implements InvocationHandler {

    private Person personTarget;

    public Object getInstance(Person person){
        this.personTarget = person;
        Class<? extends Person> c = personTarget.getClass();
        return Proxy.newProxyInstance(c.getClassLoader(), c.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object o = method.invoke(this.personTarget, args);
        after();
        return o;
    }

    public void before(){
        System.out.println("筛选环节。。。");
    }

    public void after(){
        System.out.println("办事。。。");
    }
}

