package singleton;

public enum EnumSingleton {

    INSTANCE;

    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    private EnumSingleton(){
    }

    public static EnumSingleton getInstance(){
        return null;
    }
}
