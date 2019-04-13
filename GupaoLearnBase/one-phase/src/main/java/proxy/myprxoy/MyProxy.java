/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.myprxoy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;

/**
 * @Title: MyProxy
 * @Description:
 * @Author zhujing
 * @Date 2019/4/12
 * @Version V1.0
 */
public class MyProxy {

    public static final String ln = "\r\n";

    public static Object newProxyInstance(MyClassLoader loader, Class<?>[] interfaces, MyInvocationHandler h){
        try {
            String source = getSource(interfaces);

            String path = MyProxy.class.getResource("").getPath();
            File file = new File(path + "$Proxy0.java");
            FileWriter fw = new FileWriter(file);
            fw.write(source);
            fw.close();

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(file);

            JavaCompiler.CompilationTask task = compiler.getTask(null, null, null, null, null, iterable);
            task.call();
            manager.close();

            Class<?> aClass = loader.findClass("$Proxy0");
            Constructor<?> c = aClass.getConstructor(MyInvocationHandler.class);
            return c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getSource(Class<?>[] interfaces){
        StringBuffer sb = new StringBuffer();
        sb.append("package proxy.myprxoy;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("import proxy.Person;" + ln);

        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + "{" + ln);

        sb.append("MyInvocationHandler h;" + ln);
        sb.append("public $Proxy0(MyInvocationHandler h){" + ln);
        sb.append("this.h = h;" + ln);
        sb.append("}" + ln);

        sb.append("public void findLove(){" + ln);
        sb.append("try{" + ln);
        sb.append("Method m = proxy.Person.class.getMethod(\"findLove\", new Class[]{});" + ln);
        sb.append("this.h.invoke(this, m, new Object[]{});" + ln);
        sb.append("}catch(Error _ex) { }");
        sb.append("catch(Throwable e){" + ln);
        sb.append("throw new UndeclaredThrowableException(e);" + ln);
        sb.append("}}}");
        return sb.toString();
    }

}
