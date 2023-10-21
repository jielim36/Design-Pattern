package FactoryMethod.FactoryMethod;

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
