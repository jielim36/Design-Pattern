package Singleton.Eager;
/*
Singleton (Static code block)
Step:
1. Create a static instance of this class but don't assign it a value, make it null
2. Using static code block to assign a value to instance object
3. (Same) create a private constructor
4. (Same) create a static method to return your instance
 */
public class EagerSingleton_Two {

    private static EagerSingleton_Two instance;//not assign a value, so it is null

    static {
        instance = new EagerSingleton_Two();
    }

    private EagerSingleton_Two(){

    }

    public static EagerSingleton_Two getInstance(){
        return instance;
    }

}
