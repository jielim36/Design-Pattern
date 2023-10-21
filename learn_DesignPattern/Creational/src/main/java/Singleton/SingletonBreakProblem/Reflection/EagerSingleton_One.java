package Singleton.SingletonBreakProblem.Reflection;

/*
Eager Singleton (Static variable)
Step:
1.constructor should be private: prevent outside create the object of this class
2.create a static object of this class. (Use static to make sure it is a unique object, and shared the same data anywhere)
3.create a static method provide the way for get instance of this class in the outside
 */

import java.io.Serializable;

public class EagerSingleton_One implements Serializable {

    private static EagerSingleton_One instance = null;
    private static boolean flag = false;

    private EagerSingleton_One() {
        synchronized (EagerSingleton_One.class){
            if (flag){
                throw new RuntimeException("You have broken Singleton class!");
            }
            flag = true;
        }
    }

    public static EagerSingleton_One getInstance() {
        if (instance == null) {
            synchronized (EagerSingleton_One.class) {
                if (instance == null) {
                    instance = new EagerSingleton_One();
                }
            }
        }
        return instance;
    }
}