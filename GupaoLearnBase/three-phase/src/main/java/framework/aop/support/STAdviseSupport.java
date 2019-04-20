/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.aop.support;

import framework.aop.aspect.STMethodAfterAdviceInterceptor;
import framework.aop.aspect.STMethodBeforeAdviceInterceptor;
import framework.aop.config.STAopConfig;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: STAdviseSupport
 * @Description:
 * @Author zhujing
 * @Date 2019/4/20
 * @Version V1.0
 */
public class STAdviseSupport {

    private Class<?> targetClazz;

    private Object target;

    private STAopConfig config;

    private Pattern pointCutClassPattern;

    // List<Object>其实就放的是调用链List
    private transient Map<Method, List<Object>> methodCache;

    public STAdviseSupport(STAopConfig config) {
        this.config = config;
    }

    public Class<?> getTargetClazz() {
        return targetClazz;
    }

    public Object getTarget() {
        return target;
    }

    public void setTargetClazz(Class<?> targetClazz) {
        this.targetClazz = targetClazz;
    }

    public void setTarget(Object target) {
        this.target = target;
        parse();
    }

    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) throws Exception{
        List<Object> cached = methodCache.get(method);
        if(cached.isEmpty()){
            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            cached = methodCache.get(m);

            // 这是啥，没懂。。。
            methodCache.put(m, cached);
        }
        return cached;
    }

    private void parse() {
        String pointCut = config.getPointCut()
                .replaceAll("\\.","\\\\.")
                .replaceAll("\\\\.\\*",".*")
                .replaceAll("\\(","\\\\(")
                .replaceAll("\\)","\\\\)");
        //pointCut=public .* com.gupaoedu.vip.spring.demo.service..*Service..*(.*)
        //pointCut=public \.* com\.gupaoedu\.vip\.spring\.demo\.service\.\.*Service\.\.*(\.*)
        //pointCut=public .* com\.gupaoedu\.vip\.spring\.demo\.service\..*Service\..*(.*)
        //pointCut=public .* com\.gupaoedu\.vip\.spring\.demo\.service\..*Service\..*\(.*)
        //pointCut=public .* com\.gupaoedu\.vip\.spring\.demo\.service\..*Service\..*\(.*\)
        //public .* com\.gupaoedu\.vip\.spring\.demo\.service\..*Service
        //class com\.gupaoedu\.vip\.spring\.demo\.service\..*Service
        //玩正则
        String pointCutForClassRegex = pointCut.substring(0,pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern = Pattern.compile("class " + pointCutForClassRegex.substring(
                pointCutForClassRegex.lastIndexOf(" ") + 1));

        try {
            // 找到需要被织入类中所有方法, 放入Map中方便使用
            Map<String, Method> aspectMethodMap = new HashMap<>();
            Class<?> c = Class.forName(config.getAspectClass());
            for (Method method : c.getMethods()) {
                aspectMethodMap.put(method.getName(), method);
            }

            Pattern pattern = Pattern.compile(pointCut);

            // 用于存放调用链, key切点方法, value调用链
            methodCache = new HashMap<>();
            for (Method method : this.targetClazz.getMethods()) {
                String methodString = method.toString();
                if (methodString.contains("throws")) {
                    methodString = methodString.substring(0, methodString.lastIndexOf("throws")).trim();
                }

                // 如果当前初始化的这个类或类中的方法正是需要被切入的点, 则加入调用链
                Matcher matcher = pattern.matcher(methodString);
                if(matcher.matches()){
                    List<Object> advises = new ArrayList<>();
                    if(config.getAspectBefore() != null && !config.getAspectBefore().equals("")){
                        STMethodBeforeAdviceInterceptor beforeAdviceInterceptor = new STMethodBeforeAdviceInterceptor(
                                aspectMethodMap.get(config.getAspectBefore()), c.newInstance());
                        advises.add(beforeAdviceInterceptor);
                    }else if(config.getAspectAfter() != null && !config.getAspectAfter().equals("")){
                        STMethodAfterAdviceInterceptor afterAdviceInterceptor = new STMethodAfterAdviceInterceptor(
                                aspectMethodMap.get(config.getAspectAfter()), c.newInstance());
                        advises.add(afterAdviceInterceptor);
                    }else if(config.getAspectAfterThrow() != null && !config.getAspectAfterThrow().equals("")){

                    }
                    methodCache.put(method, advises);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean pointCutMatch() {
        return pointCutClassPattern.matcher(this.targetClazz.toString()).matches();
    }
}
