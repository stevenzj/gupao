package singleton;

public class HungerSingleton {

    private static final HungerSingleton instance = new HungerSingleton();

    private void SingletonOne(){}

    public static HungerSingleton getInstance(){
        return instance;
    }
}
