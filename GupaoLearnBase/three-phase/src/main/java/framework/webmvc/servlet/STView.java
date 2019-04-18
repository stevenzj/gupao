/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package framework.webmvc.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title: STView
 * @Description:
 * @Author zhujing
 * @Date 2019/4/18
 * @Version V1.0
 */
public class STView {

    private File file;

    public STView(File file) {
        this.file = file;
    }

    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) {
        try {
            StringBuffer sb = new StringBuffer();

            RandomAccessFile raf = new RandomAccessFile(this.file, "r");
            String line = null;
            while (null != (line = raf.readLine())) {
                Pattern pattern = Pattern.compile("￥\\{[^\\}]+\\}",Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(line);
                if(matcher.find()){
                    String group = matcher.group();
                    String groupName = group.replaceAll("￥\\{|\\}", "");
                    Object result = model.get(groupName);
                    if(result == null){
                        continue;
                    }
                    line = matcher.replaceFirst(this.makeStringForRegExp(result.toString()));
                    matcher = pattern.matcher(line);
                }
                sb.append(line);
            }
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //处理特殊字符
    public static String makeStringForRegExp(String str) {
        return str.replace("\\", "\\\\").replace("*", "\\*")
                .replace("+", "\\+").replace("|", "\\|")
                .replace("{", "\\{").replace("}", "\\}")
                .replace("(", "\\(").replace(")", "\\)")
                .replace("^", "\\^").replace("$", "\\$")
                .replace("[", "\\[").replace("]", "\\]")
                .replace("?", "\\?").replace(",", "\\,")
                .replace(".", "\\.").replace("&", "\\&");
    }

}
