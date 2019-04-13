/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.dbroute;

/**
 * @Title: OrderServiceImpl
 * @Description:
 * @Author zhujing
 * @Date 2019/4/11
 * @Version V1.0
 */
public class OrderServiceImpl implements OrderService{

    private OrderDao orderDao;

    public OrderServiceImpl(){
        orderDao = new OrderDao();
    }

    @Override
    public int createOrder(Order order) {
        return orderDao.insertOrder(order);
    }
}
