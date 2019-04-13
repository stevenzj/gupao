/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package proxy.dbroute.db;

/**
 * @Title: DynamicDataSourceEntity
 * @Description:
 * @Author zhujing
 * @Date 2019/4/11
 * @Version V1.0
 */
public class DynamicDataSourceEntity {

    public final static String DEFAULT_SOURCE = null;

    private static final ThreadLocal<String> l = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return super.initialValue();
        }
    };

    public static String get(){
        return l.get();
    }

    public static void set(String source){
        l.set(source);
    }

    public static void set(int year){
        System.out.println("设置数据库为DB_" + year);
        l.set("DB_" + year);
    }

    public static void restore(){
        l.set(DEFAULT_SOURCE);
    }
}
