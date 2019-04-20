/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.aop;

/**
 * @Title: STAopProxy
 * @Description:
 * @Author zhujing
 * @Date 2019/4/20
 * @Version V1.0
 */
public interface STAopProxy {

    public Object getProxy();

    public Object getProxy(ClassLoader classLoader);
}
