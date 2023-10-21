package Singleton.Eager_Enum;

public enum EagerSingleton_Enum {
    //same like: public final static EagerSingleton_Enum INSTANCE = new EagerSingleton_Enum();
    INSTANCE;

    //constructor is private modifier
    EagerSingleton_Enum() {
    }

    public void printSomething(){
        System.out.println("Hello Singleton!");
    }

}
