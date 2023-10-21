package FactoryMethod.FactoryMethod;

public class Test {
    public static void main(String[] args) {
        Cafe cafe = new Cafe();
        CoffeeFactory factory = new AmericanoCoffeeFactory();
        cafe.setCoffeeFactory(factory);

        Coffee coffee = cafe.orderCoffee();
        System.out.println("My Coffee:"+coffee.getName());
    }
}
