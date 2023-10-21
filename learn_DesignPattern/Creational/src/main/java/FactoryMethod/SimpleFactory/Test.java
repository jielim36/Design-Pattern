package FactoryMethod.SimpleFactory;

public class Test {
    public static void main(String[] args) {
        Cafe cafe = new Cafe();
        Coffee coffee = cafe.orderCoffee("Latte Coffee");

        System.out.println("My Coffe: " + coffee.getName());
    }
}
