/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.webmvc.servlet;

import java.io.File;

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
}
