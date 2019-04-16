/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.beans;

/**
 * @Title: STBeanWrapper
 * @Description:
 * @Author zhujing
 * @Date 2019/4/14
 * @Version V1.0
 */
public class STBeanWrapper {

    private Object wrappedInstance;
    private Class<?> wrappedClass;

    public STBeanWrapper(Object wrappedInstance){
        this.wrappedInstance = wrappedInstance;
        this.wrappedClass = wrappedInstance.getClass();
    }

    /**
     * Return the bean instance wrapped by this object.
     */
    public Object getWrappedInstance() {
        return wrappedInstance;
    }

    /**
     * Return the type of the wrapped bean instance.
     */
    public Class<?> getWrappedClass() {
        return wrappedClass;
    }
}
