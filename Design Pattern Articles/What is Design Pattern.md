# Design Pattern

### Purpose
Make our project more *readability, scalability, maintainability, and reusability*.

### Type of design pattern
- **Creational Design Pattern:** Describe how to create a object? Its characterictic is separating the create and usage of the object.
- **Structural Design Pattern:** 
- **Behavioral Design Pattern:** Describe between class or object, how to done a requirement that a single object can't do it.

---
# Software design princles
In software developing, to improve system's maintainability, reusability, scalability, and flexibility. Programmer may follow few(6) principles to develop a program,it can improve your project develop speed, and reduce the cost of develop and maintain.
There are six principles of software design, we also called it ***SOLID***:
- **S**ingle Responsibility Principle 单一职责原则
- [**O**pen/Closed Principle 开闭原则](#OCP_portal)
- [**L**iskov Substitution Principle 里氏替换原则](#LSP_portal)
- **I**nterface Segregation Principle 接口隔离原则
- **D**ependency Inversion Principle 依赖倒置原则

#### Open/Closed Principle (OCP) {#OCP_portal}
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

#### Liskov Substitution Principle(LSP) 里氏代换原则 {#LSP_portal}
LSP told us, **if you replace a superclass object with its subclass object,the program will not occur any errors. The reverse is not true, if the program accept a subclass object, its may not able to use the superclass object.** For example, if I like animal means I must like dog; If i love dog, it doesn't means I like animals, because I don't like cat, although it is a animal too.

Child class can extend the functionality of the parent class, but can't change the original function of the parent class. In other words, when we inherit a class, try not to override the method of the parent class, we can only add the new method.

**Example of violating LSP:**
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
In this case, I try to use the *resize()* method with *Rectangle Object* and its didn't occur any error, but when I use the *resize()* method with the *Square Object* (subclass of the Rectangle) occur the infinite loop problem. 

So we got the conclusion: *resize()* method is only accept the Rectangle Object, subclass of the Rectangle class (Square Class) is not accept. Therefore, the **inheritance relationship between Rectangle and Square class violates Liskov Substitution Principle (LSP).** This is because the LSP needs to make sure that the methods available to the parent class are also available when switching to the child class.
<br>

#### Fixing this problem
Now, we refactor the relationship between Rectangle and Square. We try to create a interface called `Quadrilateral`, let Rectangle and Square Class implement Quadrilateral interface.

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

In this case, if I using the **Quadrilateral** (Parameter of printInfo method) object, I can use **Square** (or Rectangle) to access it; If I using the **Rectangl**e (Parameter of the resize method) object, it is not allowed for the **Square** object.