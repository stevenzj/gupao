/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.myprxoy;

import proxy.Person;
import proxy.jdkproxy.Girl;

/**
 * @Title: Test
 * @Description:
 * @Author zhujing
 * @Date 2019/4/12
 * @Version V1.0
 */
public class Test {
    public static void main(String[] args) {
        JDKMeiPoProxy111 proxy111 = new JDKMeiPoProxy111();
        Person person = (Person) proxy111.getInstance(new Girl());
        person.findLove();
    }
}
