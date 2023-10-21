package Singleton.Lazy;

public class Client {
    public static void main(String[] args) {
        LazySingleton_One instance01 = LazySingleton_One.getInstance();
        LazySingleton_One instance02 = LazySingleton_One.getInstance();

        System.out.println("Same:" + (instance01 == instance02));//true
    }
}
