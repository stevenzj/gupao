/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.thread.demo.test4.protocol;

import java.io.Serializable;

/**
 * @Title: STProtocol
 * @Description:
 * @Author zhujing
 * @Date 2019/6/19
 * @Version V1.0
 */
public class STProtocol  implements Serializable{

    private static final long serialVersionUID = -4442720370036255917L;

    private String className;

    private String methodName;

    private Class<?>[] paramTypes;

    private Object[] params;

    public STProtocol(String className, String methodName, Class<?>[] paramTypes, Object[] params) {
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
