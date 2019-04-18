/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.webmvc.servlet;

import java.io.File;
import java.util.Locale;

/**
 * @Title: STViewResolver
 * @Description:
 * @Author zhujing
 * @Date 2019/4/18
 * @Version V1.0
 */
public class STViewResolver {

    private File file;

    public STViewResolver(String templateRoot) {
        String templateRootStr = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        file = new File(templateRootStr);
    }

    public STView resolveViewName(String viewName, Locale locale){
        if(viewName == null || viewName.equals("")){
            return null;
        }

        viewName = viewName.endsWith(".html") ? viewName : viewName + ".html";

        String fileStr = (file.getPath() + "/" + viewName).replaceAll("/+", "/");
        return new STView(new File(fileStr));
    }
}
