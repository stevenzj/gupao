/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.beans.support;

import framework.beans.config.STBeanDefinition;
import framework.context.support.STAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: STDefaultListableBeanFactory
 * @Description:
 * @Author zhujing
 * @Date 2019/4/14
 * @Version V1.0
 */
public class STDefaultListableBeanFactory extends STAbstractApplicationContext {

    protected final Map<String, STBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);


}
