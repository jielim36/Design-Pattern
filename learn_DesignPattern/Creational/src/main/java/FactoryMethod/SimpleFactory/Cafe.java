package FactoryMethod.SimpleFactory;

public class Cafe {
    public Coffee orderCoffee(String type){
        SimpleCoffeeFactory simpleCoffeeFactory = new SimpleCoffeeFactory();
        Coffee coffee = simpleCoffeeFactory.createCoffee(type);
        coffee.addSugar();
        coffee.addMilk();
        return coffee;
    }
}
