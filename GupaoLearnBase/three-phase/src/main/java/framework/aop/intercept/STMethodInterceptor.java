/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.aop.intercept;

/**
 * @Title: STMethodInterceptor
 * @Description:
 * @Author zhujing
 * @Date 2019/4/20
 * @Version V1.0
 */
public interface STMethodInterceptor {
    Object invoke(STMethodInvocation methodInvocation) throws Throwable;
}
