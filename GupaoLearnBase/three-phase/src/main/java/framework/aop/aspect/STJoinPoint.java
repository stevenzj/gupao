/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @Title: STJoinPoint
 * @Description:
 * @Author zhujing
 * @Date 2019/4/20
 * @Version V1.0
 */
public interface STJoinPoint {

    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}
