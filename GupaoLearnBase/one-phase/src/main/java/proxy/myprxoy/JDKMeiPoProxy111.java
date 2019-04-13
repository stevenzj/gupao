/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.myprxoy;

import proxy.Person;

import java.lang.reflect.Method;

/**
 * @Title: JDKMeiPoProxy111
 * @Description:
 * @Author zhujing
 * @Date 2019/4/12
 * @Version V1.0
 */
public class JDKMeiPoProxy111 implements MyInvocationHandler{

    private Person personTarget;

    public Object getInstance(Person person){
        this.personTarget = person;
        Class<? extends Person> clazz = personTarget.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object o = method.invoke(this.personTarget, args);
        return o;
    }
}
