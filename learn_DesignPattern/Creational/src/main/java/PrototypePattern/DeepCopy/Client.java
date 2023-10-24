package PrototypePattern.DeepCopy;

import java.io.*;

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
