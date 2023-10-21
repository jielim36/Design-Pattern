# Creational - Singleton Design Pattern
Singleton provide a way for create a object. This pattern concern a single class, this class is only generate object of itself, and ensure that only one object can be generated, which means that we call this object in anywhere is still a same object (Data is shared). This class provide the ways to access the unique object, and you can access directly without instantiate an object of this class.

## Type of Singleton:
**Eager Instantiation:** Create an object when class loaded.
**Lazy Instantiation:** Create an object when using it first time.

## Eager Singleton Instantiation
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

## Lazy Singleton Instantiation
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

##### Other ways to implement Lazy Singleton with Thread Safety
```java
public class LazySingleton_Two {

    private LazySingleton_Two(){}

    //static inner class
    private static class SingletonHolder{
        private static final LazySingleton_Two INSTANCE = new LazySingleton_Two();
    }

    public static LazySingleton_Two getInstance(){
        //when we called this method first time, static inner class will load and initialize the INSTACE object
        return SingletonHolder.INSTANCE;
    }

}
```
Static inner class called `SingletonHolder` declared in the inside of `Singleton_Two` class, and the instance of the `Single_Two` declared in the `SingletonHolder` class. Because JVM will not load the static inner class in the process of the outer class, static inner class will be loaded and initialize static field when invoke/call the field or method of the static inner class.
<br>

---
## Eager Singleton with Enum

> Charactericstic of Enum class:
> - Each Enum type inherit from java.lang.Enum, that means enum class can't inherit others.
> - Each enum field is the instance of the enum class, same like: `public static final Class INSTANCE = new Class();`
> - Constructor of enum class is private modifier.
> - Using Class.instanceName to access the specific INSTANCE.

```java
public enum EagerSingleton_Enum {
    //same like: public final static EagerSingleton_Enum INSTANCE = new EagerSingleton_Enum();
    INSTANCE;

    //constructor is private modifier
    EagerSingleton_Enum() {
        //code...
    }

    public void printSomething(){
        System.out.println("Hello Singleton!");
    }

}
```

##### Usage:
```java
public static void main(String[] args) {
    EagerSingleton_Enum instance01 = EagerSingleton_Enum.INSTANCE;
    EagerSingleton_Enum instance02 = EagerSingleton_Enum.INSTANCE;
    System.out.println(instance01 == instance02);//output: true

    instance01.printSomething();//output: Hello Singleton!
}
```

We are highly **recommend** using **enum** to implement a Singleton design pattern, because Enumeration is the only singleton pattern of all the singletons that is **not broken**.

There are **3️ main scenarios that breaks Singleton** even though we make it Thread Safe. They are:
1. **Cloning**
2. **Deserialization**
3. **Reflection**

### 1. Cloning
Example from [here](https://salithachathuranga94.medium.com/stop-breaking-singleton-pattern-in-java-7bf7f87393c3)

### 2. Deserialization
When we write a Singleton Object into byte stream, after performing read the object, we should get the same hash code if we have only one object.
```java
public class Client {
    public static void main(String[] args) throws Exception{
        //case one (normal Singleton)
        writeCaseOne2File();
        //output two times to compare hash code
        readCaseOneFromFile();
        readCaseOneFromFile();

        //case two (Enum Singleton)
        writeCaseTwo2File();
        readCaseTwoFromFile();
        readCaseTwoFromFile();
    }

    public static void readCaseOneFromFile() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("deserialization_caseOne.txt"));
        EagerSingleton_One caseOne_first = (EagerSingleton_One) ois.readObject();

        System.out.println("Case one: "+caseOne_first.hashCode());

        ois.close();
    }

    public static void writeCaseOne2File() throws Exception{
        EagerSingleton_One caseOne = EagerSingleton_One.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("deserialization_caseOne.txt"));
        oos.writeObject(caseOne);
        oos.close();
    }

    public static void readCaseTwoFromFile() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("deserialization_caseTwo.txt"));
        EagerSingleton_Enum caseOne_first = (EagerSingleton_Enum) ois.readObject();

        System.out.println("Case Two: "+caseOne_first.hashCode());

        ois.close();
    }

    public static void writeCaseTwo2File() throws Exception{
        EagerSingleton_Enum caseTwo = EagerSingleton_Enum.INSTANCE;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("deserialization_caseTwo.txt"));
        oos.writeObject(caseTwo);
        oos.close();
    }
}
```
```
Output:
//Normal Singleton
Case one: 1793329556
Case one: 1334729950
//Enum Singleton
Case Two: 1174290147
Case Two: 1174290147
```
As you can see, if we try to write and read an object of the singleton with the stream, in normal singleton will be break because we get the **different hash code** when reading two times. Instead, the **same hash code** can be obtained using the enum case.

**How to resolve the break problem of the normal singleton in deserialization?**
```java
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
```
We add the method called `readResolve()` with `Object` return type. When executing deserialization, system will automatically invoke this method and wwe can get the same hash code now.
```
Output:
//normal singleton
Case one: 2117255219
Case one: 2117255219
//enum singleton
Case Two: 1212899836
Case Two: 1212899836
```

### 3. Reflection break the singleton
```java
public static void main(String[] args) throws Exception {
    Class<EagerSingleton_One> clazzOne = EagerSingleton_One.class;
        Constructor<EagerSingleton_One> constructor = clazzOne.getDeclaredConstructor();
        //cancel accessible
        constructor.setAccessible(true);
        EagerSingleton_One eagerSingletonOne_A = constructor.newInstance();
        EagerSingleton_One eagerSingletonOne_B = constructor.newInstance();

        System.out.println(eagerSingletonOne_A == eagerSingletonOne_B);//false

        //enum singleton
        Class<EagerSingleton_Enum> clazzEnum = EagerSingleton_Enum.class;
        EagerSingleton_Enum[] enumConstants_A = clazzEnum.getEnumConstants();

        System.out.println(enumConstants_A[0] == EagerSingleton_Enum.INSTANCE);//true
}
```
In this case, we using reflection to create two instance of the singleton, and the result of the comparison of both addresses is false.

**How to prevent the break problem of the normal singleton in reflection?**
```java
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
```
In this case, we prevent calling the constructor twice to safeguard against a broken singleton.

---
<br>

## References
1. https://www.freecodecamp.org/news/the-basic-design-patterns-all-developers-need-to-know/
2. https://salithachathuranga94.medium.com/stop-breaking-singleton-pattern-in-java-7bf7f87393c3
3. https://www.bilibili.com/video/BV1Np4y1z7BU?p=29&vd_source=114500c2627a5627c7d30c7176c58bbb