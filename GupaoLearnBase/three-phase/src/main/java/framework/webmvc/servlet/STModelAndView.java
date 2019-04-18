/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.webmvc.servlet;

import java.util.Map;

/**
 * @Title: STModelAndView
 * @Description:
 * @Author zhujing
 * @Date 2019/4/18
 * @Version V1.0
 */
public class STModelAndView {

    private String viewName;

    private Map<String, ?> model;

    public STModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public STModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }
}
