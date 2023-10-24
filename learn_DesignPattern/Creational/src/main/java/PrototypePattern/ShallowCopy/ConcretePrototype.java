package PrototypePattern.ShallowCopy;

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
