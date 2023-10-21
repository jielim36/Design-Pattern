package FactoryMethod.SimpleFactory;

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
