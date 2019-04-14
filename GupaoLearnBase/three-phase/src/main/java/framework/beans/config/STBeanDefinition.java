/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.beans.config;

/**
 * @Title: STBeanDefinition
 * @Description:
 * @Author zhujing
 * @Date 2019/4/14
 * @Version V1.0
 */
public class STBeanDefinition {

    private String beanClassName;

    private boolean isLazyInit = false;

    private String factoryBeanName;

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public boolean isLazyInit() {
        return isLazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        isLazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}
