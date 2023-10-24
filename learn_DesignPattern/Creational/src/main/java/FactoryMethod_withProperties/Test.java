package FactoryMethod_withProperties;

public class Test {
    public static void main(String[] args) {
        Coffee coffee = CoffeeFactory.createCoffee("latte");
        System.out.println(coffee.getName());//Output: Latte Coffee
    }
}
