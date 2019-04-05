package singleton;

public class DoubleCheckLazySingleton {

    private static DoubleCheckLazySingleton instance = null;

    private DoubleCheckLazySingleton(){}

    public static DoubleCheckLazySingleton getInstance(){
        if(instance == null){
            synchronized (DoubleCheckLazySingleton.class){
                if(instance == null){
                    instance = new DoubleCheckLazySingleton();
                }
            }
        }
        return instance;
    }
}
