/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package singleton;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Title: ContainerSingleton
 * @Description:
 * @Author zhujing
 * @Date 2019/4/7
 * @Version V1.0
 */
public class ContainerSingleton {

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    private ContainerSingleton(){

    }

    public static Object getBean(String className){
        Object o = null;
        try {
            synchronized (ioc) {
                if(!ioc.containsKey(className)){
                    o = Class.forName(className).newInstance();
                    ioc.put(className, o);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
