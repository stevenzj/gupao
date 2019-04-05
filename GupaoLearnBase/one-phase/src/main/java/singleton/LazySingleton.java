package singleton;

public class LazySingleton {

    private static LazySingleton instance;

    private void LazySingleton(){
    }

    public static LazySingleton getInstance() {
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
