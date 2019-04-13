/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.myprxoy;

import java.lang.reflect.Method;

/**
 * @Title: MyInvocationHandler
 * @Description:
 * @Author zhujing
 * @Date 2019/4/12
 * @Version V1.0
 */
public interface MyInvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
