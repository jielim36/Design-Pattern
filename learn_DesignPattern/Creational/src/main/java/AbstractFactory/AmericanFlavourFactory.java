package AbstractFactory;

public class AmericanFlavourFactory implements FlavourFactory{
    public Coffee createCoffee(){
        return new AmericanoCoffee();
    }
    public Cake createCake(){
        return new MatchaCake();
    }
}
