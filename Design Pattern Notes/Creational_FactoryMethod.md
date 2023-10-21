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
- Abstract Factory: Provide the interface to create products, we can access its specific factory to create products.
- Concrete Factory: Subclasses of the Creator implement the Factory Method to create specific instances of the Product. Each subclass is responsible for instantiating a particular type of Product
- Abstract Product: Defined the rules of the product, describe the characteristic and functionality of the product.
- Concrete Product: Implement the specific functionality of the abstract product.

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
In this case, we solve the problem of **Open/Closed Principle**, because if we want create a new coffee type called `MochaCoffee`, we can directly create a new concrete product class called `MochaCoffee` and concrete factory class called `MochaCoffeeFactory` class, and we don't even need to change any existing code such as `Cafe` class. However, the disadvantages of factory method is there are so many class we need to declared.