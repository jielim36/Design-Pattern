# Software Design Princles
In software developing, to improve system's maintainability, reusability, scalability, and flexibility. Programmer may follow few(6) principles to develop a program,it can improve your project develop speed, and reduce the cost of develop and maintain.
There are six principles of software design, we also called it ***SOLID***:
- <a href="#single-responsibility-principle-srp">**S**ingle Responsibility Principle (SRP) 单一职责原则</a>
- <a href="#openclosed-principle-ocp">**O**pen/Closed Principle (OCP) 开闭原则</a>
- <a href="#liskov-substitution-principle-lsp">**L**iskov Substitution Principle (LSP) 里氏替换原则</a>
- <a href="#interface-segregation-principle-isp">**I**nterface Segregation Principle (ISP) 接口隔离原则</a>
- <a href="#dependency-inversion-principle-dip">**D**ependency Inversion Principle (DIP) 依赖倒置原则</a>

Other: 
- <a href="#law-of-demeter-lod">Law of Demeter (LoD) 迪米特法则 / The Least Knowledge Principle 最少知识原则</a>
- <a href="#composite-reuse-principle-crp">Composite Reuse Principle (CRP) 合成复用原则</a>

---

## High Cohension and Low Coupling
Before we learn about Software Design Principle and Design Pattern, we should know what is **"High Cohension and Low Coupling"**.
***Hight Cohension:*** Means that the element within a module are closely related and work together to execute a well-defined task.
***Low Coupling:*** Means that there is minimal interdependence and impact between modules, aiming to enhance system flexibility and maintainability.

---

## Single Responsibility Principle (SRP)
Every class, module, or function in a program should have only one responsibilty.

#### Case of violating SRP:
```java
public class Student {

    private String username;
    private String email;
    private String password;

    public void registerStudent(){
        //code...
    }

    public void calculateStudentResults(){
        //code...
    }

    public void sendEmail(){
        //code...
    }

    //getter and setter

}
```
In this case, `Student` class **violates** the Single Responsibility Principle. Because `Student` class has **three responsibilities** - register students, calculate result of students, and sending emails to students.

#### Fixing this problem
Now, we try to separate each responsibility into a specific responsibilty class.
```java
public class Student {

    private String username;
    private String email;
    private String password;

    //getter and setter

}
```
```java
public class StudentEmail {
    public void sendEmail(){
        //code...
    }
}

```
```java
public class StudentRegister {
    public void registerStudent(){
        //code...
    }
}
```
```java
public class StudentResult {
    public void calculateStudentResults(){
        //code...
    }
}
```
Now we've separated each reponsibility/functionality into a specific class. This would make your program easy to update and maintain. If you need to make changes to the email functionality, you can simply edit the code within the `StudentEmail` class without having to worry about other classes such as `StudentRegister`.

---

## Open/Closed Principle (OCP)
Definition: Software entities (Class, module, functions) should be open for extension, and closed for modification.
When software needs to change (for updates and maintenance), it is advisable to achieve the goals through extension rather than modifying the existing code. Therefore, we recommend building a framework through abstraction and implementing details through extensions. When our software requires updates, simply extend a new implementation class based on the requirements of the abstract class.

```java
public abstract class AbstractTheme {
    public abstract void display();
}
```

```java
public class DefaultTheme extends AbstractTheme {
    @Override
    public void display() {
        System.out.println("Default Theme");
    }
}
```

```java
//When we want to create a new theme, we directly implement a new class based on 
public class DarkTheme extends AbstractTheme {
    @Override
    public void display() {
        System.out.println("Dark Theme");
    }
}
```
```java
public class UserInput {

    private AbstractTheme theme;

    public void setTheme(AbstractTheme abstractTheme){
        this.theme = abstractTheme;
    }

    public void display(){
        theme.display();
    }
}
```

```java
public class Client {
    public static void main(String[] args) {
        //create a UserInput object
        UserInput userInput = new UserInput();
        //Create a default theme
        AbstractTheme theme = new DefaultTheme();

        userInput.setTheme(theme);
        userInput.display();//output

        //if user create a new theme,no need to change the source code
        theme = new DarkTheme();

        userInput.setTheme(theme);
        userInput.display();//output

    }
}
```
```
Output:
Default Theme
Dark Theme
```
In this case, when we want to create a new theme called `DarkTheme`, we directly implement the `abstractTheme` class, and we don't modify any existing code.

---

## Liskov Substitution Principle (LSP)
LSP told us, **if you replace a superclass object with its subclass object,the program will not occur any errors. The reverse is not true, if the program accept a subclass object, its may not able to use the superclass object.** For example, if I like animal means I must like dog; If i love dog, it doesn't means I like animals, because I don't like cat, although it is a animal too.

Child class can extend the functionality of the parent class, but can't change the original function of the parent class. In other words, when we inherit a class, try not to override the method of the parent class, we can only add the new method.

#### Example of violating LSP:
```java
public class Rectangle {

    private double height;
    private double width;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}

```

```java
public class Square extends Rectangle{

    @Override
    public void setHeight(double height) {
        //set the height and width at the same time
        super.setHeight(height);
        super.setWidth(height);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        super.setHeight(width);
    }
}
```

```java
public class RectangleDemo {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(20);
        rectangle.setWidth(10);
        resize(rectangle);
        printInfo(rectangle);

        System.out.println("=============");

        Square square = new Square();
        square.setHeight(20);//set height and width at the same time
        resize(square);
        printInfo(square);

        System.out.println("DONE!");
    }

    public static void resize(Rectangle rectangle){
        //If width less than height,increase width
        while (rectangle.getWidth() <= rectangle.getHeight()){
            rectangle.setWidth(rectangle.getWidth()+1);
            //if square called the setWidth method, will set the width and height at the same time
            //so this while loop will become a infinite loop
        }
    }

    public static void printInfo(Rectangle rectangle){
        System.out.println("Width:" + rectangle.getWidth());
        System.out.println("Height:" + rectangle.getHeight());
    }

}
```

```
Output:
Width:21.0
Height:20.0
=============
<Infinite loop...>
```
In this case, I try to use the `resize()` method with *Rectangle Object* and its didn't occur any error, but when I use the `resize()` method with the *Square Object* (subclass of the Rectangle) occur the infinite loop problem. 

So we got the conclusion: `resize()` method is only accept the Rectangle Object, subclass of the Rectangle class (Square Class) is not accept. Therefore, the **inheritance relationship between Rectangle and Square class violates Liskov Substitution Principle (LSP).** This is because the LSP needs to make sure that the methods available to the parent class are also available when switching to the child class.
<br>

#### Fixing this problem
Now, we refactor the relationship between Rectangle and Square. We try to create a interface called `Quadrilateral`, let `Rectangle` and `Square` Class implement `Quadrilateral` interface.

```java
public interface Quadrilateral {
    double getHeight();
    double getWidth();
}
```

```java
public class Square implements Quadrilateral{

    private double side;

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getHeight() {
        return side;
    }

    @Override
    public double getWidth() {
        return side;
    }
}
```

```java
public class Rectangle implements Quadrilateral {

    private double height;
    private double width;

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }
}
```

```java
public class RectangleDemo {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(20);
        rectangle.setWidth(10);
        resize(rectangle);
        printInfo(rectangle);

        System.out.println("=============");

        Square square = new Square();
        square.setSide(20);
        //resize(square); now square can't use the resize method
        printInfo(square);

        System.out.println("DONE!");
    }

    //Parameter is only accept Rectangle means Square can't use it
    public static void resize(Rectangle rectangle){
        //If width less than height,increase width
        while (rectangle.getWidth() <= rectangle.getHeight()){
            rectangle.setWidth(rectangle.getWidth()+1);
        }
    }

    //Parameter accept quadrilateral means Rectangle and Square can access this method
    public static void printInfo(Quadrilateral quadrilateral){
        System.out.println("Width:" + quadrilateral.getWidth());
        System.out.println("Height:" + quadrilateral.getHeight());
    }

}
```
Now, we refer back the example above:
> if I like animal means I must like dog; If i love dog, it doesn't means I like animals, because I don't like cat, although it is a animal too.

In this case, if I using the **Quadrilateral** (Parameter of printInfo method) object, I can use **Square** (or Rectangle) to access it; If I using the **Rectangle** (Parameter of the resize method) object, it is not allowed for the **Square** object.

---

## Interface Segregation Principle (ISP)
**Clients should not be *forced* to depend upon interfaces that they do not use.**

Example:
We create a safety door brand called `JieLimSafetyDoor`, and it has the functional of anti-theft, fireproof and waterproof.

```java
public interface SafetyDoor {
    void antiTheft();
    void fireProof();
    void waterProof();
}
```
```java
public class JieLimSafetyDoor implements SafetyDoor{
    @Override
    public void antiTheft() {
        System.out.println("Anti-Theft");
    }

    @Override
    public void fireProof() {
        System.out.println("Fireproof");
    }

    @Override
    public void waterProof() {
        System.out.println("Waterproof");
    }
}
```
```java
public class Client {
    public static void main(String[] args) {
        JieLimSafetyDoor door = new JieLimSafetyDoor();
        door.antiTheft();
        door.fireProof();
        door.waterProof();
    }
}
```
```
Output:
Anti-Theft
Fireproof
Waterproof
```
In this case, `JieLimSafetyDoor` class looks like very good. However,what would occur **if I add a new safety door brand that only contain anti-theft and waterproof functionality?**
```java
public class TommySafetyDoor implements SafetyDoor{
    @Override
    public void antiTheft() {
        System.out.println("Anti-Theft");
    }

    @Override
    public void fireProof() {
        //None
    }

    @Override
    public void waterProof() {
        System.out.println("Waterproof");
    }
}
```
As you can see, the new brand, called `TommySafetyDoor`, implements the `SafetyDoor` interface and **forces** it to implement the `fireProof()` method even though it doesn't have this functionality.

#### Solving the problem of enforced implementation methods
We try to split the methods of the `SafetyDoor` interface (anti-Theft, fireproof and waterproof) into interfaces called `antiTheft`,`fireProof` and `waterProof`.
```java
public interface antiTheft {
    void antiTheft();
}
```
```java
public interface fireProof {
    void fireProof();
}
```
```java
public interface waterProof {
    void waterProof();
}
```
```java
public class JieLimSafetyDoor implements antiTheft,fireProof,waterProof {
    @Override
    public void antiTheft() {
        System.out.println("Anti-Theft");
    }
    @Override
    public void fireProof() {
        System.out.println("Fireproof");
    }
    @Override
    public void waterProof() {
        System.out.println("Waterproof");
    }
}
```
```java
public class TommySafetyDoor implements antiTheft,waterProof {//implement the interfaces that you need
    @Override
    public void antiTheft() {
        System.out.println("Anti-Theft");
    }
    @Override
    public void waterProof() {
        System.out.println("Waterproof");
    }
    //No need to implement the fireProof() method if don't use it.
}
```
In this case, `JieLimSafetyDoor` and `TommySafetyDoor` implements the neccessary interfaces, so they don't have to force implement the method they don't need.


---

## Dependency Inversion Principle (DIP)
Dependency Inversion Principle consists of two parts:
- High-level Module should not depend on low-level module. Both should depend on abstractions.
- Abstractions should not depend on details. Details should depend on abstractions.

Example of build a computer:
Now, I'm going to build a computer that consists of three component which are CPU, Hard Disk, and RAM. In this case, I have Intel and AMD options when I selecting a CPU.

#### Case of violating DIP
```java
public class IntelCpu {
    public void run(){
        System.out.println("Using Intel CPU...");
    }
}

```
```java
public class KingstonRam {
    public void save(){
        System.out.println("Using Kingston RAM...");
    }
}

```
```java
public class WdHardDisk {
    public void save(String data){
        System.out.println("WD Hard disk saving data:" + data);
    }
    public String getData(){
        System.out.println("Fetching data from WD hard disk...");
        return "Hello WD!!";
    }
}
```
```java
public class Computer {

    //Component (specific brand)
    private WdHardDisk hardDisk;
    private IntelCpu cpu;
    private KingstonRam ram;

    public void computerStart(){
        System.out.println("Computer is running...");
        System.out.println("Data from hard disk is: " + hardDisk.getData());
        cpu.run();
        ram.save();
    }

    //getter/setter method
    public WdHardDisk getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(WdHardDisk hardDisk) {
        this.hardDisk = hardDisk;
    }

    public IntelCpu getCpu() {
        return cpu;
    }

    public void setCpu(IntelCpu cpu) {
        this.cpu = cpu;
    }

    public KingstonRam getRam() {
        return ram;
    }

    public void setRam(KingstonRam ram) {
        this.ram = ram;
    }
}
```
```java
public class ComputerDemo {
    public static void main(String[] args) {
        //Create component of computer (specific brand)
        WdHardDisk hardDisk = new WdHardDisk();
        IntelCpu cpu = new IntelCpu();
        KingstonRam ram = new KingstonRam();

        Computer computer = new Computer();
        computer.setCpu(cpu);
        computer.setRam(ram);
        computer.setHardDisk(hardDisk);
        computer.computerStart();
    }
}
```
```
Output:
Computer is running...
Fetching data from WD hard disk...
Data from hard disk is: Hello WD!!
Using Intel CPU...
Using Kingston RAM...
```
In this case, although the computer may initially appear to have no issues, but its scalability could be severely limited. This is because if we want to change the CPU to a different brand such as AMD, the computer may not accept to "plug in" because the CPU in this computer class is specific to a particular brand.

*Computer class:*
```java
//Component (specific brand)
private WdHardDisk hardDisk;
private IntelCpu cpu;
private KingstonRam ram;
```

#### Applying the Dependency Inversion Principle Correctly
The solution to the above case is using **Interface** for each component. It will make **Computer class depend on the abstractions** (interface class of each component) instead of the implementation class (IntelCpu class)
```java
public interface Cpu {
    void run();
}
```
```java
public class IntelCpu implements Cpu{
    public void run(){
        System.out.println("Using Intel CPU...");
    }
}
```
```java
public class Computer {

    //Using interface instead of an specific implementation class (IntelCpu class)
    private HardDisk hardDisk;
    private Cpu cpu;
    private Ram ram;

    public void computerStart(){
        System.out.println("Computer is running...");
        System.out.println("Data from hard disk is: " + hardDisk.getData());
        cpu.run();
        ram.save();
    }
    //getter and setter method
}    
```
```java
public class ComputerDemo {
    public static void main(String[] args) {
        //Polymorphism: InterfaceClass objName = new ImplementationClass();
        HardDisk hardDisk = new WdHardDisk();
        Cpu cpu = new IntelCpu();
        Ram ram = new KingstonRam();

        Computer computer = new Computer();
        computer.setCpu(cpu);
        computer.setRam(ram);
        computer.setHardDisk(hardDisk);
        computer.computerStart();

        System.out.println("===============");

        cpu = new AmdCpu();//Change to different brand
        computer.setCpu(cpu);
        computer.computerStart();
    }
}
```
```
Output:
Computer is running...
Fetching data from WD hard disk...
Data from hard disk is: Hello WD!!
Using Intel CPU...
Using Kingston RAM...
===============
Computer is running...
Fetching data from WD hard disk...
Data from hard disk is: Hello WD!!
Using AMD CPU... //worked
Using Kingston RAM...
```
In this case, the members of the Computer class is using **interface instead of a specific implementation class**. This allows the class to remain functional even when switching to a different brand of CPU.

---

## Law of Demeter (LoD) 
The **Law of Demeter** is also known as **The Least Knowledge Principle**. In simpler terms, the fundamental concept of LoD can be expressed as **"Talk only to your immediate friends and not to strangers."** Its means that if two software entities do not need to communicate directly, there should be no direct interaction between them; the communication should go through a third-party intermediary. The goal is to reduce the coupling between classes and enhance the modularity and independence of modules.

We using the case of relationship between agent and artists.
Artists are fully focus on their art, so many of their day-to-day affairs are handled by an agent, such as fan meetings and business negotiations with companies. The agent here is a friend of the artist, while the fans and the company are strangers to the artist.

```java
public class Artist {

    String name;

    public Artist(String name){
        this.name = name;
    }
    //getter and setter
}
```
```java
public class Company {

    private String name;

    public Company(String name) {
        this.name = name;
    }
    //getter and setter
}
```
```java
public class Fans {

    private String name;

    public Fans(String name) {
        this.name = name;
    }
    //getter and setter
}
```
Now, there are declared three independent class called `Artist`,`Fans`,and `Company`. If these objects want to interact, we need a medium called `Agent`.
```java
public class Agent {

    private Artist artist;
    private Company company;
    private Fans fans;

    //fans meeting
    public void meeting(){
        System.out.println("Artist(Name:"+ artist.getName() +") meeting with fans(Name:"+ fans.getName() +")");
    }

    //business talk
    public void businessNegotiations(){
        System.out.println("Artist(Name:"+ artist.getName() +") talking with company(Name:"+ company.getName() +")");
    }
    //getter and setter
}    
```
```java
public class Client {
    public static void main(String[] args) {
        Agent agent = new Agent();
        Artist artist = new Artist("Jielim");
        Fans fans = new Fans("Tom");
        Company company = new Company("ABC Company");
        agent.setArtist(artist);
        agent.setFans(fans);
        agent.setCompany(company);

        agent.meeting();
        agent.businessNegotiations();
    }
}
```
```
Output:
Artist(Name:Jielim) meeting with fans(Name:Tom)
Artist(Name:Jielim) talking with company(Name:ABC Company)
```
The advantage of Law of Demeter is reduce the coupling between classes, make it more easily to maintain.

---

## Composite Reuse Principle (CRP)
The core idea of CRP is **"prefer object composition over class inheritance."** The purpose of this principle is to reduce system coupling, enhance module independence, and promote code reuse.

Usually class reuse is divided into two types: **inheritance reuse and composite reuse:**

Although **inheritance reuse** has the **advantage of simple and ease of implementation**, it also has the following **disadvantages**:
1. Inheritance reuse destroys the encapsulation of the class. Because inheritance exposes the details of the parent class to the child class.
2. Subclasses are highly coupled with the parent class. Any change in the implementation of the parent class leads to changes in the implementation of the subclass, which is not favourable for the extension and maintenance of the class.

When using **composite reuse**, an existing object can be incorporated into a new object to make it a part of the new object, and the new object can call the functions of the existing object, which has the following **advantages**:
1. It maintains the encapsulation of the class. It maintains the encapsulation of the class because the internal details of the component objects are invisible to the new object.
2. Low coupling between objects. Abstraction can be declared at the member position of the class.

Example: Categories of the Vehicle
There are different types of cars such as petrol and electric cars, and they come in a variety of colours such as red, white and black. 

**Inheritance Reuse:**
![InheritanceCarUML](https://www.panziye.com/wp-content/uploads/2022/05/2022053013555418.png)
As you can see, if we want to create a new type of car called *Ligh-energy Car*, we need to add a new class called `LightEnergyCar` inherit from superclass `Car`, and create few classes such as `RedLightEnergyCar` and `WhiteLightEnergyCar` inherit from parent class `LightEnergyCar`. Moreover, if we add a new color, all type of car should add a new class for this color.

<br>

**Composite Reuse:**
![CompositeCarUML](https://www.panziye.com/wp-content/uploads/2022/05/2022053013570569.png)
You can observe that we have separated the Car and Color concepts. This separation makes it easy to introduce new car types and colors. For example, if you want to add a new car type, such as `LightEnergyCar`, you can simply insert it directly below the Car superclass without needing to create a new subclass such as `WhiteLightEnergyCar`. The process is the same for adding a new color.

---
## References (where did I learn it)
1. https://www.freecodecamp.org/news/solid-principles-explained-in-plain-english/
2. https://juejin.cn/post/6844903795017646094
3. https://stackify.com/solid-design-principles/
4. https://www.freecodecamp.org/news/solid-principles-single-responsibility-principle-explained/
4. https://stackify.com/solid-design-open-closed-principle/
5. https://stackify.com/solid-design-liskov-substitution-principle/
6. https://stackify.com/interface-segregation-principle/
7. https://stackify.com/dependency-inversion-principle/
8. https://www.bilibili.com/video/BV1Np4y1z7BU/?p=18&vd_source=114500c2627a5627c7d30c7176c58bbb