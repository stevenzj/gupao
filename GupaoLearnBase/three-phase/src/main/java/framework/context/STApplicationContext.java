/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.context;

import framework.annotation.GPAutowired;
import framework.annotation.GPController;
import framework.annotation.GPService;
import framework.core.STBeanFactory;
import framework.beans.STBeanWrapper;
import framework.beans.config.STBeanDefinition;
import framework.beans.support.STBeanDefinitionReader;
import framework.beans.support.STDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.ObjDoubleConsumer;

/**
 * @Title: STApplicationContext
 * @Description: IOC->DI->MVC->AOP
 * @Author zhujing
 * @Date 2019/4/14
 * @Version V1.0
 */
public class STApplicationContext extends STDefaultListableBeanFactory implements STBeanFactory {

    private String[] configLocations;

    private STBeanDefinitionReader definitionReader;

    // 单例Bean集合
    private Map<String, Object> singletionObjects = new ConcurrentHashMap<>();

    // 所有Bean集合
    private Map<String, STBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public STApplicationContext(String... configLocations){
        this.configLocations = configLocations;
        reflush();
    }

    @Override
    protected void reflush() {
        //1.定位, 定位配置文件位置
        definitionReader = new STBeanDefinitionReader(this.configLocations);

        //2.加载，扫描，封装成BeanDefinition
        List<STBeanDefinition> beanDefinitions = definitionReader.loadBeanDefinitions();

        //3.注册, 配置信息放到容器里
        this.doRegisterBeanDefinition(beanDefinitions);

        //4.初始化类, 暂时不处理需要懒加载的类
        this.doAutowired();
    }

    @Override
    public Object getBean(String beanName) {
        //1.初始化Bean
        STBeanWrapper beanWrapper = instantiateBean(beanName, super.beanDefinitionMap.get(beanName));

        this.factoryBeanInstanceCache.put(beanName, beanWrapper);

        //2.注入其他Bean
        populateBean(beanName, super.beanDefinitionMap.get(beanName), beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    private void doRegisterBeanDefinition(List<STBeanDefinition> beanDefinitions) {
        for(STBeanDefinition beanDefinition : beanDefinitions){
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
    }

    private void doAutowired() {
        for (Map.Entry<String, STBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String factoryBeanName = beanDefinitionEntry.getKey();
            STBeanDefinition beanDefinition = beanDefinitionEntry.getValue();
            if(!beanDefinition.isLazyInit()){
                this.getBean(factoryBeanName);
            }
        }
    }

    private STBeanWrapper instantiateBean(String beanName, STBeanDefinition stBeanDefinition) {
        String className = stBeanDefinition.getBeanClassName();

        Object instance = null;
        try {
            if(this.singletionObjects.containsKey(className)){
                instance = singletionObjects.get(className);
            }else {
                Class<?> c = Class.forName(className);
                instance = c.newInstance();

                this.singletionObjects.put(className, instance);
                this.singletionObjects.put(stBeanDefinition.getFactoryBeanName(), instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new STBeanWrapper(instance);
    }

    private void populateBean(String beanName, STBeanDefinition stBeanDefinition, STBeanWrapper stBeanWrapper) {
        Object instance = stBeanWrapper.getWrappedInstance();
        Class<?> clazz = stBeanWrapper.getWrappedClass();
        if(!(clazz.isAnnotationPresent(GPController.class) || clazz.isAnnotationPresent(GPService.class))){
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            if(!field.isAnnotationPresent(GPAutowired.class)){
                continue;
            }

            GPAutowired gpAutowired = field.getAnnotation(GPAutowired.class);
            String autowireBeanName = gpAutowired.value().trim();
            if(autowireBeanName.equals("")){
                autowireBeanName = field.getType().getName();
            }

            field.setAccessible(true);

            try {
                field.set(instance, this.factoryBeanInstanceCache.get(autowireBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
