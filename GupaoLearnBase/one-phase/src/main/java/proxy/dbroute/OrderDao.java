/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.dbroute;

/**
 * @Title: OrderDao
 * @Description:
 * @Author zhujing
 * @Date 2019/4/11
 * @Version V1.0
 */
public class OrderDao {

    public int insertOrder(Order order){
        System.out.println("插入一条Order数据");
        return 1;
    }
}
