/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.webmvc.servlet;

import framework.annotation.STController;
import framework.annotation.STRequestMapping;
import framework.context.STApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: STDispatcherServlet
 * @Description:
 * @Author zhujing
 * @Date 2019/4/18
 * @Version V1.0
 */
public class STDispatcherServlet extends HttpServlet {

    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private STApplicationContext context = null;

    private List<STHandlerMapping> mappingList = new ArrayList<>();

    private Map<STHandlerMapping, STHandlerAdapter> handlerAdapterMap = new HashMap<>();

    private List<STViewResolver> viewResolverList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化ApplicationContext
        context = new STApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));

        // 初始化九大组件
        this.initStrategies(context);
    }

    /**
     * 初始化九大组件
     */
    protected void initStrategies(STApplicationContext context){
        //多文件上传的组件
        initMultipartResolver(context);

        //初始化本地语言环境
        initLocaleResolver(context);

        //初始化模板处理器
        initThemeResolver(context);

        //handlerMapping1
        initHandlerMappings(context);

        //初始化参数适配器2
        initHandlerAdapters(context);

        //初始化异常拦截器
        initHandlerExceptionResolvers(context);

        //初始化视图预处理器
        initRequestToViewNameTranslator(context);

        //初始化视图转换器3
        initViewResolvers(context);

        //
        initFlashMapManager(context);
    }

    private void initMultipartResolver(STApplicationContext context) {
    }

    private void initLocaleResolver(STApplicationContext context) {
    }

    private void initThemeResolver(STApplicationContext context) {
    }

    private void initHandlerMappings(STApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        try {
            for(String beanName : beanNames){
                Object bean = context.getBean(beanName);
                Class<?> clazz = bean.getClass();

                // class上没有配置@Controller的不管
                if(!clazz.isAnnotationPresent(STController.class)){
                    continue;
                }

                String baseURL = "";
                if(clazz.isAnnotationPresent(STRequestMapping.class)){
                    STRequestMapping requestMapping = clazz.getAnnotation(STRequestMapping.class);
                    baseURL = requestMapping.value();
                }

                Method[] methods = clazz.getMethods();
                for(Method method : methods) {
                    // method上没有配置@RequestMapping的不管
                    if(!method.isAnnotationPresent(STRequestMapping.class)){
                        continue;
                    }

                    STRequestMapping requestMapping = method.getAnnotation(STRequestMapping.class);
                    String requestMappingStr = requestMapping.value().replaceAll("\\*", ".*");
                    String regex = (baseURL + requestMappingStr).replaceAll("/+", "/");
                    // 生成url为aaa/bbb/*的正则
                    Pattern pattern = Pattern.compile(regex);

                    // 保存数据
                    mappingList.add(new STHandlerMapping(bean, method, pattern));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initHandlerAdapters(STApplicationContext context) {
        for (STHandlerMapping handlerMapping : this.mappingList) {
            // 建立HandlerMapping与HandlerAdapter的一一对应关系
            this.handlerAdapterMap.put(handlerMapping, new STHandlerAdapter());
        }
    }

    private void initHandlerExceptionResolvers(STApplicationContext context) {
    }

    private void initRequestToViewNameTranslator(STApplicationContext context) {
    }

    private void initViewResolvers(STApplicationContext context) {
        String templateRootStr = context.getConfig().getProperty("templateRoot");
        String filePath = this.getClass().getClassLoader().getResource(templateRootStr).getFile();

        File file = new File(filePath);
        for (File listFile : file.listFiles()) {
            this.viewResolverList.add(new STViewResolver(templateRootStr));
        }
    }

    private void initFlashMapManager(STApplicationContext context) {
    }


    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        // 获取HandlerMapping
        STHandlerMapping handlerMapping = this.getHandlerMapping(req);
        if(handlerMapping == null){
            // 404其实就是没找到HandlerMapping
            this.processDispatchResult(req, resp, new STModelAndView("404"));
            return;
        }

        // 获取HandlerAdaptcher
        STHandlerAdapter handlerAdapter = this.getHandlerAdaptch(handlerMapping);
        STModelAndView modelAndView = handlerAdapter.handle(req, resp, handlerMapping);

        // 输出
        this.processDispatchResult(req, resp, modelAndView);

    }

    private STHandlerMapping getHandlerMapping(HttpServletRequest req) {
        if(this.mappingList.isEmpty()){
            return null;
        }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();

        // replace classpath*
        url = url.replaceAll(contextPath, "").replaceAll("/+", "");

        for (STHandlerMapping mapping : this.mappingList) {
            // regex match
            Matcher matcher = mapping.getPattern().matcher(url);
            return matcher.matches() ? mapping : null;
        }
        return null;
    }

    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
                                       STModelAndView modelAndView){
        if(modelAndView == null){
            return;
        }

        if(this.viewResolverList.isEmpty()){
            return;
        }

        for (STViewResolver viewResolver : this.viewResolverList) {
            STView view = viewResolver.resolveViewName(modelAndView.getViewName(), null);
            view.render(modelAndView.getModel(), request, response);
        }

    }

    private STHandlerAdapter getHandlerAdaptch(STHandlerMapping handlerMapping) {
        if(this.handlerAdapterMap.isEmpty()){
            return null;
        }
        STHandlerAdapter adapter = handlerAdapterMap.get(handlerMapping);
        if(adapter.support(handlerMapping)){
            return adapter;
        }
        return null;
    }
}
