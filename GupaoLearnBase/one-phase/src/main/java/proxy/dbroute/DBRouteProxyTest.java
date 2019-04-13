/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.dbroute;

import java.util.Date;

/**
 * @Title: DBRouteProxyTest
 * @Description:
 * @Author zhujing
 * @Date 2019/4/11
 * @Version V1.0
 */
public class DBRouteProxyTest {

    public static void main(String[] args) {
        Order order = new Order();
        order.setCreateTime(new Date().getTime());
        order.setId("666");
        order.setOrderInfo("内容");

        OrderService orderService = new OrderServiceStaticProxy(new OrderServiceImpl());
        orderService.createOrder(order);
    }
}
