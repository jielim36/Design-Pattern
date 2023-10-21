package Singleton.Lazy;

public class LazySingleton_Two {

    private LazySingleton_Two(){}

    private static class SingletonHolder{
        private static final LazySingleton_Two INSTANCE = new LazySingleton_Two();
    }

    public static LazySingleton_Two getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
