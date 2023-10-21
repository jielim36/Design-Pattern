package Singleton.SingletonBreakProblem.Deserialization;

import java.io.Serializable;

public enum EagerSingleton_Enum implements Serializable {
    //same like: public final static EagerSingleton_Enum INSTANCE = new EagerSingleton_Enum();
    INSTANCE;

    //constructor is private modifier
    EagerSingleton_Enum() {
    }

    public void printSomething(){
        System.out.println("Hello Singleton!");
    }

}
