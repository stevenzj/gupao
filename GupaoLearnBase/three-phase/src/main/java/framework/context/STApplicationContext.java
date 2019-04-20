/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.context;

import framework.annotation.STAutowired;
import framework.annotation.STController;
import framework.annotation.STService;
import framework.aop.config.STAopConfig;
import framework.aop.support.STAdviseSupport;
import framework.core.STBeanFactory;
import framework.beans.STBeanWrapper;
import framework.beans.config.STBeanDefinition;
import framework.beans.support.STBeanDefinitionReader;
import framework.beans.support.STDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

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

                //TODO aspect
                STAdviseSupport adviseSupport = initAdviseSupport(stBeanDefinition);
                adviseSupport.setTarget(instance);
                adviseSupport.setTargetClazz(c);


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
        if(!(clazz.isAnnotationPresent(STController.class) || clazz.isAnnotationPresent(STService.class))){
            return;
        }

        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            if(!field.isAnnotationPresent(STAutowired.class)){
                continue;
            }

            STAutowired STAutowired = field.getAnnotation(STAutowired.class);
            String autowireBeanName = STAutowired.value().trim();
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

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public Properties getConfig(){
        return this.definitionReader.getConfig();
    }

    private STAdviseSupport initAdviseSupport(STBeanDefinition beanDefinition) {
        STAopConfig aopConfig = new STAopConfig();
        aopConfig.setPointCut(this.definitionReader.getConfig().getProperty("pointCut"));
        aopConfig.setAspectClass(this.definitionReader.getConfig().getProperty("aspectClass"));
        aopConfig.setAspectBefore(this.definitionReader.getConfig().getProperty("aspectBefore"));
        aopConfig.setAspectAfter(this.definitionReader.getConfig().getProperty("aspectAfter"));
        aopConfig.setAspectAfterThrow(this.definitionReader.getConfig().getProperty("aspectAfterThrow"));
        aopConfig.setAspectAfterThrowingName(this.definitionReader.getConfig().getProperty("aspectAfterThrowingName"));
        return new STAdviseSupport(aopConfig);
    }
}
