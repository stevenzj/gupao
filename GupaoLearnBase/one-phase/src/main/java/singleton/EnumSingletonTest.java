package singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public class EnumSingletonTest {

    public static void main(String[] args) {
        /*EnumSingleton e1 = EnumSingleton.getInstance();
        EnumSingleton e2;

        try {
            FileOutputStream f1 = new FileOutputStream("EnumSingleton.obj");
            ObjectOutputStream o1 = new ObjectOutputStream(f1);
            o1.writeObject(e1);
            o1.flush();
            o1.close();

            FileInputStream f2 = new FileInputStream("EnumSingleton.obj");
            ObjectInputStream o2 = new ObjectInputStream(f2);
            e2 = (EnumSingleton) o2.readObject();
            o2.close();

            System.out.println(e1);
            System.out.println(e2);
            System.out.println(e1 == e2);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        try {
            Class<EnumSingleton> clazz = EnumSingleton.class;
            Constructor<EnumSingleton> c = clazz.getDeclaredConstructor(String.class, int.class);
            c.setAccessible(true);

            EnumSingleton e1 = c.newInstance("steven", 666);
            System.out.println(e1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
