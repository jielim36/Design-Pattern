# Builder Design Pattern

Builder Design Pattern can separate the construction of a complex object from its representation so that the same construction process can create different representations.

## Builder Design Pattern Type Two

In real case, there are many component which are RAM,CPU,Fans,and Graphic Card to build a computer.
![computer](https://i.insider.com/577440fedd0895585b8b4a25?width=400&format=jpeg&auto=webp)

Structure:
Product: The complex object we want to create.
Builder: This abstract base class efines all of the steps that must be taken in order to correctly create a product.
ConcreteBuilder: Implement `Builder` interface, 

Product: `Computer` class
```java
public class Computer {

    private String Ram;
    private String Cpu;

    //getter and setter

    @Override
    public String toString() {
        return "Computer{" +
                "Ram='" + Ram + '\'' +
                ", Cpu='" + Cpu + '\'' +
                '}';
    }
}
```
Builder class:
```java
public abstract class Builder {

    protected Computer computer = new Computer();

    public abstract void buildRam();
    public abstract void buildCpu();

    public abstract Computer createComputer();

}
```
Concrete Builder: `AsusComputerBuilder` and `AcerComputerBuilder`
```java
public class AcerComputerBuilder extends Builder{
    @Override
    public void buildRam() {
        computer.setRam("(Acer) 8GB RAM");
    }

    @Override
    public void buildCpu() {
        computer.setCpu("(Acer) AMD CPU");
    }

    @Override
    public Computer createComputer() {
        return computer;
    }
}
```
```java
public class AsusComputerBuilder extends Builder{
    @Override
    public void buildRam() {
        computer.setRam("(Asus) 16GB RAM");
    }

    @Override
    public void buildCpu() {
        computer.setCpu("(Asus) Intel CPU");
    }

    @Override
    public Computer createComputer() {
        return computer;
    }
}
```
Director:
```java
public class Director {

    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    public Computer construct(){
        builder.buildRam();
        builder.buildCpu();
        return builder.computer;
    }

}
```
Usage:
```java
public class Client {
    public static void main(String[] args) {
        Director director = new Director(new AsusComputerBuilder());
        Computer computer = director.construct();
        System.out.println(computer);//toString()
    }
}
```
```
Output:
Computer{Ram='(Asus) 16GB RAM', Cpu='(Asus) Intel CPU'}
```
In this case, `Computer` abstract class defined what component need, `Builder` abstract class defined the method for build computer component. After that, we can create the different type of computer into a concrete builder class such as `AsusComputerBuilder` and `AcerComputerBuilder`, and implement the method that `Builder` abstract class defined. Finally, we need the `Director` class to create a complete computer using builder, and the usage is send a concrete builder that we declared into the `Director` constructor.

## Builder Design Pattern Type Two

When a class's constructor requires many parameters, the readability of the class is poor and it is easy to make typing errors. In this case, we can use the Builder Pattern for refactoring.

### Before
```java
public class Computer {

    private String cpu;
    private String ram;
    private String storage;
    private String brand;

    public Computer(String cpu, String ram, String storage, String brand) {
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
        this.brand = brand;
    }

    //getter and setter

    @Override
    public String toString() {
        return "Phone{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
```
Usage:
```java
public static void main(String[] args) {
        Computer computer = new Computer("Intel","Kingston 8GB","WD SSD 500GB","Asus");
        System.out.println(computer);
        //Output: Phone{cpu='Intel', ram='Kingston 8GB', storage='WD SSD 500GB', brand='Asus'}
    }
```

### After
```java
public class Computer {

    private String cpu;
    private String ram;
    private String storage;
    private String brand;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.brand = builder.brand;
    }

    public static final class Builder{
        private String cpu;
        private String ram;
        private String storage;
        private String brand;

        public Builder cpu(String cpu){
            this.cpu = cpu;
            return this;//return builder
        }

        public Builder ram(String ram){
            this.ram = ram;
            return this;
        }

        public Builder storage(String storage){
            this.storage = storage;
            return this;
        }

        public Builder brand(String brand){
            this.brand = brand;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }

    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
```
Usage:
```java
public static void main(String[] args) {
        Computer computer =  new Computer.Builder() //create a Builder class to call builder's method
                .cpu("Intel") //set the data of cpu and return back Builder class using "this" keyword
                .ram("Kingston 8GB")//ram can be call after invoke cpu() method because cpu() method return Builder class "this" keyword.
                .storage("WD SSD 500GB")
                .brand("Asus")
                .build();//invoke build() to call the Computer's private constructor to create a Computer object
        System.out.println(computer);//Computer{cpu='Intel', ram='Kingston 8GB', storage='WD SSD 500GB', brand='Asus'}
    }
```
In this case, we modify the `Computer`'s constructor into private access modifier, and using `Builder` inner class to build each component and call the `build()` method to create a `Computer` object.