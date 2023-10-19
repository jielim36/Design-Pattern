package example01;

/*
单例模式SingleTon的“饿汉式”设计核心：
1.构造器私有话private ->防止外部直接new这个类的对象
2.在该类的内部创建对象
3.向外暴露一个静态的公共方法。 getInstance
4.在该类的内部创建的对象和getInstance都是static修饰符，为了让外部在调用此对象时是共享的，
  而且static可以让外部用类名+方法名直接调用getInstance方法，static也会让类加载时就直接执行。
5.这个设计模式叫饿汉式的原因就是static方法在类加载是就执行了（着急）

 */

public class SingleTon {
    public static int number = 100;
    private String name;
    private static SingleTon singleTon = new SingleTon("Single Ton");

    private SingleTon(String name) { //将构造器进行私有化（private），防止外部对该类随意的创建对象
        this.name = name;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }

    @Override
    public String toString() {
        return "SingleTon{" +
                "name='" + name + '\'' +
                '}';
    }
}
