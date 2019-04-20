/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * @Title: STAbstractAspectAdvice
 * @Description:
 * @Author zhujing
 * @Date 2019/4/20
 * @Version V1.0
 */
public abstract class STAbstractAspectAdvice implements STAdvice{

    private Method aspectMethod;
    private Object aspectObject;

    public STAbstractAspectAdvice(Method aspectMethod, Object aspectObject) {
        this.aspectMethod = aspectMethod;
        this.aspectObject = aspectObject;
    }

    public Object invokeAdviceMethod(STJoinPoint joinPoint, Object returnValue, Throwable tx) throws Throwable{
        Class<?> [] paramTypes = this.aspectMethod.getParameterTypes();
        if(null == paramTypes || paramTypes.length == 0){
            return this.aspectMethod.invoke(aspectObject);
        }else{
            Object [] args = new Object[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i ++) {
                if(paramTypes[i] == STJoinPoint.class){
                    args[i] = joinPoint;
                }else if(paramTypes[i] == Throwable.class){
                    args[i] = tx;
                }else if(paramTypes[i] == Object.class){
                    args[i] = returnValue;
                }
            }
            return this.aspectMethod.invoke(aspectObject, args);
        }
    }
}
