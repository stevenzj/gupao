/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.jdkproxy;

import proxy.Person;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @Title: JDKMeiPoProxyTest
 * @Description:
 * @Author zhujing
 * @Date 2019/4/8
 * @Version V1.0
 */
public class JDKMeiPoProxyTest {
    public static void main(String[] args) throws Exception {
        JDKMeiPoProxy proxy = new JDKMeiPoProxy();
        Person person = (Person) proxy.getInstance(new Girl());
        person.findLove();

        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
        FileOutputStream fileOutputStream = new FileOutputStream("D://$Proxy0.class");
        fileOutputStream.write(bytes);
        fileOutputStream.close();

    }
}
