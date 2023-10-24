package PrototypePattern.ShallowCopy;

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
