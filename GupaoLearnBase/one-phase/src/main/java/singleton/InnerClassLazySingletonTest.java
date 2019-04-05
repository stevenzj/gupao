package singleton;

import java.lang.reflect.Constructor;

public class InnerClassLazySingletonTest {
    public static void main(String[] args) {
        try {
            Class<?> clazz = InnerClassLazySingleton.class;
            Constructor<?> constructor = clazz.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            Object o1 = constructor.newInstance();

            Object o2 = InnerClassLazySingleton.getInstance();

            System.out.println(o1 == o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
