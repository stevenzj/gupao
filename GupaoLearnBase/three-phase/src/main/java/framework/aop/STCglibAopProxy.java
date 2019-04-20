/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.aop;

import framework.aop.support.STAdviseSupport;

/**
 * @Title: STCglibAopProxy
 * @Description:
 * @Author zhujing
 * @Date 2019/4/20
 * @Version V1.0
 */
public class STCglibAopProxy implements STAopProxy{

    private STAdviseSupport adviseSupport;

    public STCglibAopProxy(STAdviseSupport adviseSupport) {
        this.adviseSupport = adviseSupport;
    }

    @Override
    public STAopProxy getProxy() {
        return null;
    }

    @Override
    public STAopProxy getProxy(ClassLoader classLoader) {
        return null;
    }
}
