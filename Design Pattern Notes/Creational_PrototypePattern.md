# Prototype Pattern

The Prototype Pattern is a creational design pattern that allows you to copy an existing object into a new instances of objects while minimizing the coupling to the object creation process. There are two types of copying which are shallow copy and deep copy.

**Shallow Copy:**
- In shallow copy, only the object itself is copied, and the reference-type member objects within the object are not copied.
- If the prototype object contains reference-type member variables, a shallow copy will duplicate references to these members rather than their content.
- This means that if you modify the reference-type member objects within the prototype object, the cloned objects will also be affected because they share the same reference-type members.

**Deep Copy:**
- In deep copy, not only is the object itself copied, but reference-type member objects are also recursively copied to ensure that the cloned object is completely independent from the original.
- This means that regardless of how the reference-type member objects of the prototype object are modified, the cloned objects remain unaffected because they have their own independent reference-type members.

Structure:
1. **Abstract Prototype Class:** provide the method that concrete prototype must implement such as `clone()` method.
2. **Concrete Prototype Class:** Implement the method of abstract prototype class, concrete protype class can be copy.
3. **Usage Class:** Using the `clone()` method of  concrete prototype class to clone a new object.

## Case One (Shallow Copy)
Concrete Prototype Class is implement the `Cloneable` interface of JDK.
```java
public class ConcretePrototype implements Cloneable{

    private Student student;

    public ConcretePrototype() {
        System.out.println("ConcretePrototype constructor is called...");
    }

    @Override
    protected ConcretePrototype clone() throws CloneNotSupportedException {
        System.out.println("Cloning new object in clone() method...");
        return (ConcretePrototype) super.clone();
    }

    public void printInfo(){
        System.out.println("Hello World!!");
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
```
Variable in ConcretePrototype class.
```java
public class Student {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

Usage:
```java
public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        ConcretePrototype existingObj = new ConcretePrototype();//constructor output: ConcretePrototype constructor is called...
        Student student = new Student();
        student.setName("Jielim36");
        existingObj.setStudent(student);

        ConcretePrototype cloneObj = existingObj.clone();
        cloneObj.printInfo();//output: Hello World!!
        cloneObj.getStudent().setName("Tommy");

        System.out.println("Object Same? : " + (existingObj==cloneObj));//output: false
        System.out.println("Existing Object's student name:" + existingObj.getStudent().getName());
        System.out.println("Clone Object's student name:" + cloneObj.getStudent().getName());
        System.out.println("student name address Same? : " + (existingObj.getStudent() == cloneObj.getStudent()));
    }
}
```
```
Output:
ConcretePrototype constructor is called...
Cloning new object in clone() method...
Hello World!!
Object Same? : false
Existing Object's student name:Tommy
Clone Object's student name:Tommy
student name address Same? : true
```

Based on the output we can get the following information:
- When a we create a concrete prototype object will called the constructor and output a message "ConcretePrototype constructor is called...". However, when we use the `clone()` method to create a object, the constructor is not executed.
- If we compare the memory address between the existing object and clone object, the result will be false.
- If we set the student name of the existing object previously, and we clone it and change the student name of the clone object. As you can see, the student name between both objects is change to the same name called "Tommy", means that it is a **shallow copy** because we change the name of the clone object will be affected the existing object.
- If we compare the variable memory address between the existing object and clone object, the result will be true, meaning that it is a **shallow copy**. Because the variable address of the cloned object is referenced to the variable address of the existing object.

## Case Two (Deep Copy)
How do we change the student name of clone object and not affected the student name of the existing object?

We implement `Serializable` interface in `Student` and `ConcretePrototype` class.
```java
public class ConcretePrototype implements Cloneable, Serializable {
    //code...
}
```
```java
public class Student implements Serializable {
    //code...
}
```
```java
public class Client {
    public static void main(String[] args) throws Exception {
        ConcretePrototype existingObj = new ConcretePrototype();//constructor output: ConcretePrototype constructor is called...
        Student student = new Student();
        student.setName("Jielim36");
        existingObj.setStudent(student);

        //write object
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("myObject.txt"));
        oos.writeObject(existingObj);
        oos.close();

        //read object
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("myObject.txt"));
        ConcretePrototype cloneObj = (ConcretePrototype) ois.readObject();
        ois.close();

        //change the student name of the readObject
        cloneObj.getStudent().setName("Tommy");

        System.out.println("Object Same? : " + (existingObj==cloneObj));//output: false
        System.out.println("Existing Object's student name:" + existingObj.getStudent().getName());
        System.out.println("Clone Object's student name:" + cloneObj.getStudent().getName());
        System.out.println("student name address Same? : " + (existingObj.getStudent() == cloneObj.getStudent()));
    }
}

```
```
Output:
ConcretePrototype constructor is called...
Object Same? : false
Existing Object's student name:Jielim36
Clone Object's student name:Tommy
student name address Same? : false
```
In this case, we try to use write and read object with stream to clone a new object. After that, we can directly change the student name of the clone obejct and not affect the student name of the existing object anymore.

## Custom Prototype Pattern
```java
public interface User {
    User clone();
}
```
```java
public class Admin implements User {

    private String username;
    private Authority authority;

    public Admin(String username) {
        this.username = username;
    }

    @Override
    public Admin clone() {
        Admin admin = new Admin(this.getUsername());//create a new admin object and give it own data
        admin.setAuthority(this.getAuthority());
        return admin;
    }

    //getter and setter
}
```
```java
public class Authority {
    private int level;
    private String description;

    public Authority(int level, String description) {
        this.level = level;
        this.description = description;
    }

    //getter and setter
}
```
```java
public class Client {
    public static void main(String[] args) {
        Admin admin = new Admin("jielim36");
        admin.setAuthority(new Authority(1,"Highest Authority"));

        Admin cloneAdmin = admin.clone();
        cloneAdmin.getAuthority().setDescription("Lowest Authority");

        System.out.println("Existing Admin authority Description:" + admin.getAuthority().getDescription());
        System.out.println("Clone Admin authority Description:" + cloneAdmin.getAuthority().getDescription());
    }
}
```
If you want to customize your own `clone()` method, you can create an object and pass your all value of the existing object to the new object, but it is **shallow copy**.