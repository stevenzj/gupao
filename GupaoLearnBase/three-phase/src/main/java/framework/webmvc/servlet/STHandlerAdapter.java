/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.webmvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: STHandlerAdapter
 * @Description:
 * @Author zhujing
 * @Date 2019/4/18
 * @Version V1.0
 */
public class STHandlerAdapter {

    public boolean support(Object handler){
        return handler instanceof STHandlerMapping;
    }

    public STModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) {

    }
}
