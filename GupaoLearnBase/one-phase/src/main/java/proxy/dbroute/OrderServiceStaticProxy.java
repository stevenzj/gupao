/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.dbroute;

import proxy.dbroute.db.DynamicDataSourceEntity;

import java.lang.reflect.InvocationHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Title: OrderServiceStaticProxy
 * @Description:
 * @Author zhujing
 * @Date 2019/4/11
 * @Version V1.0
 */
public class OrderServiceStaticProxy implements OrderService {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    private OrderService orderService;

    public OrderServiceStaticProxy(OrderService orderService){
        this.orderService = orderService;
    }

    @Override
    public int createOrder(Order order) {
        Long createTime = order.getCreateTime();
        Integer i = Integer.valueOf(sdf.format(new Date(createTime)));
        DynamicDataSourceEntity.set(i);

        System.out.println("静态代理类准备插入一条数据到Order库中");
        orderService.createOrder(order);

        DynamicDataSourceEntity.restore();
        return 0;
    }
}
