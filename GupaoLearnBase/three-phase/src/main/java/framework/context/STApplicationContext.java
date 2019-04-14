/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.context;

import framework.beans.STBeanFactory;
import framework.beans.STBeanWrapper;
import framework.beans.config.STBeanDefinition;
import framework.beans.support.STBeanDefinitionReader;
import framework.beans.support.STDefaultListableBeanFactory;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

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
        instantiateBean(beanName, new STBeanDefinition());

        //2.注入其他Bean
        populateBean(beanName, new STBeanDefinition(), new STBeanWrapper());
        return null;
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

    private void instantiateBean(String beanName, STBeanDefinition stBeanDefinition) {
    }

    private void populateBean(String beanName, STBeanDefinition stBeanDefinition, STBeanWrapper stBeanWrapper) {
    }

}
