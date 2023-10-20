# Creational - Singleton Design Pattern
Singleton provide a way for create a object. This pattern concern a single class, this class is only generate object of itself, and ensure that only one object can be generated, which means that we call this object in anywhere is still a same object (Data is shared). This class provide the ways to access the unique object, and you can access directly without instantiate an object of this class.

## Type of Singleton:
**Eager Instantiation:** Create an object when class loaded.
**Lazy Instantiation:** Create an object when using it first time.

## Eager Instantiation
There are two ways to implement a Eager pattern:

#### Static variable type
Step:
1. constructor should be private: prevent outside create the object of this class
2. create a static object of this class. (Use static to make sure it is a unique object, and shared the same data anywhere)
3. create a static method provide the way for get instance of this class in the outside
```java
public class EagerSingleton_One {

    //Step 2.
    private static EagerSingleton_One instance = new EagerSingleton_One();

    //Step 1.
    private EagerSingleton_One(){

    }

    //Step 3.
    public static EagerSingleton_One getInstance(){
        return instance;
    }

    public void printInfo(){
        System.out.println("My name is Jielim!");
    }

}
```

#### Static code block type
Step:
1. Create a static instance of this class but don't assign it a value (null)
2. Using static code block to assign a value to instance object
3. (Same) create a private constructor
4. (Same) create a static method to return your instance
```java
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
```

#### Usage

```java
public static void main(String[] args) {
        //create Singleton object
        EagerSingleton_One instance01 = EagerSingleton_One.getInstance();
        instance01.printInfo();//output:My name is Jielim!
}
```
As you can see, we using `EagerSingleton_One instance01 = EagerSingleton_One.getInstance();` instead of `EagerSingleton_One eagerSingletonOne = new EagerSingleton_One();`. 

It is because we make the constructor into private access modifier to prevent other instance object create from external. We provide the method called `getInstance()` to allow external access to the same instance object.

```java
public static void main(String[] args) {
        EagerSingleton_One instance01 = EagerSingleton_One.getInstance();
        EagerSingleton_One instance02 = EagerSingleton_One.getInstance();
        System.out.println("Same :" + (instance01 == instance02));
        //Output: Same :true
}
```
As you can see, we create two instance by using the the `getInstance()` method, and we using `==` operator to **compare the address of both**, it's **return true**.

#### Question:
1. Why it is a same instance object? 
Because we using **static** keyword to declared an object.
2.  What different between using static variable and static code block to implement Singleton? 
Static variable type is loaded when the program starting; Static code block type is loaded when the first time you use/call this class such as `EagerSingleton_One.xxXX()`.

---

<br>

## Lazy Instantiation
It is very **similar** with Eager Singleton，the difference is the `getInstance()` method.

```java
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){}

    public static LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
```
```java
public static void main(String[] args) {
        LazySingleton instance01 = LazySingleton.getInstance();
        LazySingleton instance02 = LazySingleton.getInstance();

        System.out.println("Same:" + (instance01 == instance02));//true (same object)
    }
```

## What differences between Eager and Lazy Type of Singleton?
1. ***Load timing:*** Eager Singleton instance object is loaded when program starting or Class loaded; Lazy Singleton is loaded when the `getInstance()` method is called for the first time. This affects the memory consumption of the entire program
2. ***Thread Safety:*** Eager Singleton is thread safety but Lazy Singleton is not safety. 

##### Why Lazy Singleton is not safety in thread?
```java
public static LazySingleton getInstance(){
    if (instance == null){
        //inside if-statement point
        instance = new LazySingleton();
    }
    return instance;
}
```
You can imagine that if two threads access `getInstance()` method at the same time and also deterimine `instance == null`, there is a probability that both threads judge null and create the object at the same time.

##### Resolve the thread safety problem of Lazy Sigleton
Idea: double verification
```java
public static LazySingleton getInstance() {
    if (instance == null) {
        //both threads judge null and come in at the same time, using synchronized keywords to make sure only on
        synchronized (LazySingleton.class) {
            //Only one thread is allowed to judge the condition at the same time
            if (instance == null) {
                instance = new LazySingleton();
            }
        }
    }
    return instance;
}
```