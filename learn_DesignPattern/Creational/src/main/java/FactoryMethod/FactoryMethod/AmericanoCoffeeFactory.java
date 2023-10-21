package FactoryMethod.FactoryMethod;

public class AmericanoCoffeeFactory implements CoffeeFactory{
    @Override
    public Coffee createCoffee() {
        return new AmericanoCoffee();
    }
}
