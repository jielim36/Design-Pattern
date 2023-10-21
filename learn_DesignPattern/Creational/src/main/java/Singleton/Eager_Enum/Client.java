package Singleton.Eager_Enum;

public class Client {
    public static void main(String[] args) {
        EagerSingleton_Enum instance01 = EagerSingleton_Enum.INSTANCE;
        EagerSingleton_Enum instance02 = EagerSingleton_Enum.INSTANCE;
        System.out.println(instance01 == instance02);//output: true

        instance01.printSomething();//output: Hello Singleton!
    }
}
