package singleton;

public class ExecutorThread implements Runnable{
    @Override
    public void run() {
        LazySingleton singleton = LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + "---" + singleton);
    }
}
