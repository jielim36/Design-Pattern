package Singleton.Eager;

public class Client {
    public static void main(String[] args) {
        //create Singleton object
        //EagerSingleton_One eagerSingletonOne = new EagerSingleton_One();
        EagerSingleton_One instance01 = EagerSingleton_One.getInstance();
        instance01.printInfo();//output:My name is Jielim!

        //determine the object is a same object or not?
        EagerSingleton_One instance02 = EagerSingleton_One.getInstance();
        System.out.println("Same :" + (instance01 == instance02));//Same :true
    }
}
