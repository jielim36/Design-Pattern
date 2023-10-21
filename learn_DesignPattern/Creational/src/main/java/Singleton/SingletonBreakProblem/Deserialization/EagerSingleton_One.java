package Singleton.SingletonBreakProblem.Deserialization;

/*
Eager Singleton (Static variable)
Step:
1.constructor should be private: prevent outside create the object of this class
2.create a static object of this class. (Use static to make sure it is a unique object, and shared the same data anywhere)
3.create a static method provide the way for get instance of this class in the outside
 */

import java.io.Serializable;

public class EagerSingleton_One implements Serializable {

    private static EagerSingleton_One instance = new EagerSingleton_One();

    private EagerSingleton_One(){

    }
    public static EagerSingleton_One getInstance(){
        return instance;
    }

    public void printInfo(){
        System.out.println("My name is Jielim!");
    }

    //when executing deserialization, system will automatically invoke this method
    public Object readResolve(){
        return instance;
    }
}
