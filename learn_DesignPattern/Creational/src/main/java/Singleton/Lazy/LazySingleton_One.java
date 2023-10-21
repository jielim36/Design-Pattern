package Singleton.Lazy;

public class LazySingleton_One {

    private static LazySingleton_One instance;

    private LazySingleton_One(){}

//    public static LazySingleton getInstance(){
//        if (instance == null){
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    //resolve the thread safety problem
    public static LazySingleton_One getInstance() {
        if (instance == null) {
            //both threads judge null and come in at the same time, using synchronized keywords to make sure only on
            synchronized (LazySingleton_One.class) {
                //Only one thread is allowed to judge the condition at the same time
                if (instance == null) {
                    instance = new LazySingleton_One();
                }
            }
        }
        return instance;
    }

}
