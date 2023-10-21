package FactoryMethod.demo01_noDesignPattern;

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
