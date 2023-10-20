package Singleton.Lazy;

public class Client {
    public static void main(String[] args) {
        LazySingleton instance01 = LazySingleton.getInstance();
        LazySingleton instance02 = LazySingleton.getInstance();

        System.out.println("Same:" + (instance01 == instance02));//true
    }
}
