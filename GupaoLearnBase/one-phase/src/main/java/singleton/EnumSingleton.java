package singleton;

public enum EnumSingleton {

    INSTANCE;

    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
