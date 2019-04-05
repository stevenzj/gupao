package singleton;

public class InnerClassLazySingleton {

    private InnerClassLazySingleton(){
        if(InnerHolder.LAZY != null){
            throw new RuntimeException("please use InnerClassLazySingleton getInstance method");
        }
    }

    public static InnerClassLazySingleton getInstance(){
        return InnerHolder.LAZY;
    }

    public static class InnerHolder{
        private static final InnerClassLazySingleton LAZY = new InnerClassLazySingleton();
    }

    public Object readResolve(){
        return InnerHolder.LAZY;
    }
}
