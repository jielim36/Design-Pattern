package FactoryMethod.demo01_noDesignPattern;

public abstract class Coffee {

    String name;

    public void addSugar(){
        System.out.println("Add Sugar!!");
    }

    public void addMilk(){
        System.out.println("Add Milk!!");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
