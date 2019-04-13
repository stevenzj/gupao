/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.myprxoy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @Title: MyClassLoader
 * @Description:
 * @Author zhujing
 * @Date 2019/4/12
 * @Version V1.0
 */
public class MyClassLoader extends ClassLoader{

    private File classPathFile;
    public MyClassLoader(){
        String path = MyClassLoader.class.getResource("").getPath();
        classPathFile = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) {
        String className = MyClassLoader.class.getPackage().getName() +"." + name;
        if(classPathFile != null){
            File file = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
            if(file.exists()){
                try {
                    FileInputStream fis = new FileInputStream(file);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len = 0;
                    while ((len = fis.read(buff)) != -1){
                        baos.write(buff, 0, len);
                    }
                    return defineClass(className, baos.toByteArray(), 0, baos.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
