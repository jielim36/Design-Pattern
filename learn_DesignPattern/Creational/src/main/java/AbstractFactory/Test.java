package AbstractFactory;

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
