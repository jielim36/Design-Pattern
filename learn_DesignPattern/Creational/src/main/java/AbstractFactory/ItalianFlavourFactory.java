package AbstractFactory;

public class ItalianFlavourFactory implements FlavourFactory{
    public Coffee createCoffee(){
        return new LatteCoffee();
    }
    public Cake createCake(){
        return new TiramisuCake();
    }

}