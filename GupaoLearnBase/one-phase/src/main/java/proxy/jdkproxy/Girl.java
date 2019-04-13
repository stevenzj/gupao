/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.jdkproxy;

import proxy.Person;

/**
 * @Title: Girl
 * @Description:
 * @Author zhujing
 * @Date 2019/4/8
 * @Version V1.0
 */
public class Girl implements Person {
    @Override
    public void findLove() {
        System.out.println("高");
        System.out.println("富");
        System.out.println("帅");
    }
}
