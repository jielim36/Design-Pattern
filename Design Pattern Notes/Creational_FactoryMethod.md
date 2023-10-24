# Creational - Factory Method

## Before using Factory Method
Example: Cafe produce coffee

```java
public abstract class Coffee {

    String name;
    
    public void addSugar(){
        System.out.println("Add Sugar!!");
    }

    public void addMilk(){
        System.out.println("Add Milk!!");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
```java
public class LatteCoffee extends Coffee{
    @Override
    public String getName() {
        return "Latte Coffee";
    }
}
```
```java
public class AmericanoCoffee extends Coffee{
    @Override
    public String getName() {
        return "Americano Coffee";
    }
}
```
```java
public class Cafe {
    public Coffee orderCoffee(String type){
        Coffee coffee = null;
        if (type.equals("Americano Coffee")){
            coffee = new AmericanoCoffee();
        } else if (type.equals("Latte Coffee")) {
            coffee = new AmericanoCoffee();
        }else {
            throw new RuntimeException("Invalid type of coffee!!");
        }

        coffee.addMilk();
        coffee.addSugar();

        return coffee;
    }
}
```
```java
public static void main(String[] args) {
        Cafe cafe = new Cafe();
        Coffee coffee = cafe.orderCoffee("Latte Coffee");

        System.out.println("My Coffe: " + coffee.getName());
    }
```
In this case, we have a superclass called `Coffee` and if we want to create a new type of coffee we may add a subclass below it such as `AmericanCoffee` and `LatteCoffee`.

The problem of it is in the `Cafe` class, `orderCoffee()` method using if-else statement to verify the type of coffee and produce the specific coffee and return it. If we add a new coffee type called `MochaCoffee`, we must add a new if-else statement in the `orderCoffee()` method to verify this new coffee. It is violating the software design principles called **Open/Closed Principle**.

If we want to resolve this problem, we should using **Factory Method.** There are three factory method type: 
1. Simple Factory Method
2. Factory Method
3. Abstract Factory

## 1. Simple Factory Method
Simple Factory is not a design pattern, but more of a programming habit.

Structure:
- Abstract Product: Defined the rules of the product, describe the characteristic and functionality of the product.
- Concrete Product: The subclass that implement or inherit the abstract product.
- Concrete Factory: Provide the ways to producing products, we get the product through this method.

Resolve:
We don't change anything in Coffee abstract class and its subclass. We add a new class called `SimpleCoffeFactory`.
```java
public class SimpleCoffeeFactory {

    public Coffee createCoffee(String type){
        Coffee coffee = null;
        if (type.equals("Americano Coffee")){
            coffee = new AmericanoCoffee();
        } else if (type.equals("Latte Coffee")) {
            coffee = new AmericanoCoffee();
        }else {
            throw new RuntimeException("Invalid type of coffee!!");
        }

        return coffee;
    }

}
```
```java
public class Cafe {
    public Coffee orderCoffee(String type){
        SimpleCoffeeFactory simpleCoffeeFactory = new SimpleCoffeeFactory();
        Coffee coffee = simpleCoffeeFactory.createCoffee(type);
        coffee.addSugar();
        coffee.addMilk();
        return coffee;
    }
}
```
We create a new class called `SimpleCoffeeFactory`, give class `SimpleCoffeeFactory` the job of creating the coffee object that was originally given to class `Cafe`, so that all responsibility of these class is cleary, but its also not resolve the problem of **Open/Closed Principle** and we should using **Factory Method** design pattern to solve it.

### 2. Factory Method
Concept: Defined a interface that use for create object, let the subclass decide which type of coffee to instantiate.**The factory method delays the instantiation of a product class to its factory subclass.**

Structure:
- **Abstract Factory:** Provide the interface to create products, we can access its specific factory to create products.
- **Concrete Factory:** Subclasses of the Creator implement the Factory Method to create specific instances of the Product. Each subclass is responsible for instantiating a particular type of Product
- **Abstract Product:** Defined the rules of the product, describe the characteristic and functionality of the product.
- **Concrete Product:** Implement the specific functionality of the abstract product. **The relationship between Concrete Product and Concrete Factory is One to One.**

Abstract Factory:
```java
public interface CoffeeFactory {
    Coffee createCoffee();
}
```
Concrete Factory : `LatteCoffeeFactory` and `AmericanoCoffeeFactory`.
```java
public class LatteCoffeeFactory implements CoffeeFactory{
    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }
}
```
```java
public class AmericanoCoffeeFactory implements CoffeeFactory{
    @Override
    public Coffee createCoffee() {
        return new AmericanoCoffee();
    }
}
```
Abstract Product:
```java
public abstract class Coffee {

    String name;

    public void addSugar(){
        System.out.println("Add Sugar!!");
    }

    public void addMilk(){
        System.out.println("Add Milk!!");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
Concrete Product: `AmericanoCoffee` and `LatteCoffee`.
```java
public class LatteCoffee extends Coffee {
    @Override
    public String getName() {
        return "Latte Coffee";
    }
}
```
```java
public class AmericanoCoffee extends Coffee {
    @Override
    public String getName() {
        return "Americano Coffee";
    }
}
```
Centre:
```java
public class Cafe {

    private CoffeeFactory coffeeFactory;

    public void setCoffeeFactory(CoffeeFactory coffeeFactory){
        this.coffeeFactory = coffeeFactory;
    }

    public Coffee orderCoffee(){
        Coffee coffee = coffeeFactory.createCoffee();
        coffee.addSugar();
        coffee.addMilk();
        return coffee;
    }
}
```
Usage:
```java
public class Test {
    public static void main(String[] args) {
        Cafe cafe = new Cafe();
        CoffeeFactory factory = new AmericanoCoffeeFactory();
        cafe.setCoffeeFactory(factory);//input the specific factory to produce a specific coffee

        Coffee coffee = cafe.orderCoffee();
        System.out.println("My Coffee:"+coffee.getName());
    }
}
```
In this case, we solve the problem of **Open/Closed Principle**, because if we want to create a new type of coffee called `MochaCoffee`, we can directly create a new concrete product class called `MochaCoffee` and concrete factory class called `MochaCoffeeFactory` class, and **we don't even need to change any existing code** like `Cafe` class. However, the **disadvantages of the factory method** is that there are so many classes we need to declare, which increases the complexity of the system.

---
<br>

## Abstract Factory
Suppose our cafe now sells `Coffee` and `Cake` products, and we have `TiramisuCake` and `MatchaCake`, if we continued to use the factory method, we would have too many classes in our system.

Now let's analyse our products, now I have `AmericanoCoffee`, `LatteCoffee`, `TiramisuCake`, and `MatchaCake`. We can categorize them into product categories called `Coffee` and `Cake`, and flavour types called `AmericanFlavour` and `ItalianFlavour`.

Structure:
- **Abstract Factory:** Provide the interface of create products, it can create many type of product such as `Coffee` and `Cake`.
- **Concrete Factory:** Implement abstract factory, create the specific type of products.
- **Abstract Product:** Defined products, describe the features and function of the products.
- **Concrete Product:** Implement abstract product, instance create by concrete factory, **the relationship between concrete product and concrete factory is Many to One.**

Abstract Product: `Coffee` and `Cake`
```java
public abstract class Cake {
    public abstract void show();
}
```
```java
public abstract class Coffee {

    String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

Concrete Product: `AmericanoCoffee`, `LatteCoffee`, `MatchaCake`, and `TiramisuCake`.
```java
public class AmericanoCoffee extends Coffee {
    @Override
    public String getName() {
        return "Americano Coffee";
    }
}
```
```java
public class LatteCoffee extends Coffee {
    @Override
    public String getName() {
        return "Latte Coffee";
    }
}
```
```java
public class MatchaCake extends Cake{
    @Override
    public void show() {
        System.out.println("Matcha Cake");
    }
}
```
```java
public class TiramisuCake extends Cake{
    @Override
    public void show() {
        System.out.println("Tiramisu Cake!");
    }
}
```

Abstract Factory:
```java
public interface FlavourFactory {
    Coffee createCoffee();
    Cake createCake();
}
```

Concrete Factory:
```java
public class ItalianFlavourFactory implements FlavourFactory{
    public Coffee createCoffee(){
        return new LatteCoffee();
    }
    public Cake createCake(){
        return new TiramisuCake();
    }
}
```
```java
public class AmericanFlavourFactory implements FlavourFactory{
    public Coffee createCoffee(){
        return new AmericanoCoffee();
    }
    public Cake createCake(){
        return new MatchaCake();
    }
}
```

Usage:
```java
public class Test {
    public static void main(String[] args) {
        AmericanFlavourFactory americanFlavourFactory = new AmericanFlavourFactory();
        Cake cake01 = americanFlavourFactory.createCake();
        Coffee coffee01 = americanFlavourFactory.createCoffee();
        cake01.show();
        System.out.println(coffee01.getName());

        System.out.println("===============");

        ItalianFlavourFactory italianFlavourFactory = new ItalianFlavourFactory();
        Cake cake02 = italianFlavourFactory.createCake();
        Coffee coffee02 = italianFlavourFactory.createCoffee();
        cake02.show();
        System.out.println(coffee02.getName());
    }
}
```
```
Output:
Matcha Cake
Americano Coffee
===============
Tiramisu Cake!
Latte Coffee
```

**The disadvantages of Abstract Factory:**
If I want to add a new flavour called `MalaysianFlavour`, I can add the concrete factory called `MalaysianFlavourFactory` and the concrete product called `AhHuatFlatWhiteCoffee`. As you can see, when we add a new flavour we only need to add the new class without modifying the existing code.

**The disadvantages of Abstract Factory:**
If I want to add a new product called `IceCream`, all concrete factory class must modify the code because concrete factory need to add a  interface for create `IceCream` instance.

Based on the above advantages and disadvantages, we need to **analyse whether our product is fixed and flavour is variable before using Abstract Factory.** Another example: There are only two products, laptops and mobile phones, but there are many brands.

## Factory Method with properties
We can try to emulate the **Spring IoC design pattern** and create a Coffee class by writing the Concrete class path using the properties file.

**My File Structure:**
java:
-FactoryMethod_withProperties
--Coffee.java
--AmericanoCoffee.java
--LatteCoffee.java
--CoffeeFactory.java
--Test.java

resources:
-bean.properties (we write the path of the coffee concrete classes in this file)

```java
public abstract class Coffee {

    String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
```java
public class AmericanoCoffee extends Coffee {
    @Override
    public String getName() {
        return "Americano Coffee";
    }
}
```
```java
public class LatteCoffee extends Coffee {
    @Override
    public String getName() {
        return "Latte Coffee";
    }
}

```
Properties file: `bean.properties`
```properties
americano=FactoryMethod_withProperties.AmericanoCoffee
latte=FactoryMethod_withProperties.LatteCoffee
```
```java
public class CoffeeFactory {

    private static HashMap<String,Coffee> coffeeMap = new HashMap<>();

    static {
        Properties beanProperties = new Properties();
        InputStream resourceAsStream = CoffeeFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            beanProperties.load(resourceAsStream);
            Set<Object> keys = beanProperties.keySet();
            for (Object key : keys){
                String className = beanProperties.getProperty((String) key);
                //className: americanoCoffee class path( FactoryMethod_withProperties.AmericanoCoffee )
                //use reflection to create class
                Class clazz = Class.forName(className);
                Coffee coffeeClass = (Coffee)clazz.newInstance();
                coffeeMap.put((String) key,coffeeClass);//key:value = americano:FactoryMethod_withProperties.AmericanoCoffee class
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Coffee createCoffee(String name){
        return coffeeMap.get(name);
    }

}
```
Usage:
```java
public class Test {
    public static void main(String[] args) {
        Coffee coffee = CoffeeFactory.createCoffee("latte");
        System.out.println(coffee.getName());//Output: Latte Coffee
    }
}
```
In this case, we read all data from `bean.properties` into the Hash Map variable called `coffeeMap`. We called the `createCoffee(String name)` method and send the string data same with key value in `bean.properties`, so that we can get the concrete class that the path you write in `bean.properties`.