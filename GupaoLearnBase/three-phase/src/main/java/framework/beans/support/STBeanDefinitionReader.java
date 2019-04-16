/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.beans.support;

import framework.beans.config.STBeanDefinition;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Title: STBeanDefinitionReader
 * @Description:
 * @Author zhujing
 * @Date 2019/4/14
 * @Version V1.0
 */
public class STBeanDefinitionReader {

    private static final String SCAN_PACKAGE = "scanPackage";

    private List<String> registerBeanClasses = new ArrayList<>();

    private Properties propertiesConfig = new Properties();

    public Properties getProperties(){
        return this.propertiesConfig;
    }

    public STBeanDefinitionReader(String... locations){
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replaceAll("classpath:", ""));
        try {
            propertiesConfig.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(is != null){
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        this.doScan(propertiesConfig.getProperty(SCAN_PACKAGE));
    }

    public List<STBeanDefinition> loadBeanDefinitions(){
        List<STBeanDefinition> list = new ArrayList<>();
        for(String className : registerBeanClasses){
            STBeanDefinition beanDefinition = this.doCreateBeanDefinition(className);
            if(beanDefinition != null){
                list.add(beanDefinition);
            }
        }
        return list;
    }

    private void doScan(String scanPackageStr){
        URL url = this.getClass().getResource("/" + scanPackageStr.replaceAll("\\.", "/"));
        File files = new File(url.getFile());
        for(File f : files.listFiles()) {
            if(f.isDirectory()){
                this.doScan(scanPackageStr + "." + f.getName());
            }else {
                if(!f.getName().endsWith(".class")){
                    continue;
                }else {
                    String className = scanPackageStr + "." + f.getName().replaceAll(".class", "");
                    registerBeanClasses.add(className);
                }
            }
        }
    }

    private STBeanDefinition doCreateBeanDefinition(String className){
        try {
            Class<?> beanClass = Class.forName(className);

            // 暂时不处理接口
            if(beanClass.isInterface()){
                return null;
            }

            STBeanDefinition beanDefinition = new STBeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(beanClass.getSimpleName());
            beanDefinition.setLazyInit(false);
            return beanDefinition;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
