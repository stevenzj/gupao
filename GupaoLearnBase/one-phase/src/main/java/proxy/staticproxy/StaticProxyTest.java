/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.staticproxy;

/**
 * @Title: StaticProxyTest
 * @Description:
 * @Author zhujing
 * @Date 2019/4/11
 * @Version V1.0
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.findLove();
    }
}
