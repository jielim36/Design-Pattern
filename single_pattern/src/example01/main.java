package example01;

/*
什么是设计模式？Design Pattern
1.设计模式是在大量的实践中总结和理论化之后优选出来的代码结构，编程风格，以及解决问题的思考方式。
  设计模式更像是经典的棋谱，不同的棋局有不同的棋谱（套路）
2.静态方法和属性的经典使用
3.设计模式有很多中（23种左右）

现在讲的是单例模式SingleTon Pattern 里的 饿汉式

单例模式：
1.所谓类的单例设计模式，就是采取一定的方法保证在整个的软件系统中，对于某个类只能存在一个对象实例
  并且该类只提供一个取得其对象实例的方法
2.单例模式有两种方式：1.饿汉式
                   2.懒汉式

 */
public class main {
    public static void main(String[] args) {
//        new SingleTon("Jie"); 会报错，因为SingleTon类的构造器是private访问权限，可以防止其他人对该类随意的创建对象

        //利用getInstance创建对象
        SingleTon person = SingleTon.getInstance(); //person对象是getInstance获取的singleTon静态对象
        System.out.println(person);//toString

        SingleTon person2 = SingleTon.getInstance(); //所以person2也通过getInstance创建的对象会和第一个person对象共享，所以没有区别
        System.out.println(person2);

        System.out.println(person == person2);//true

        System.out.println(SingleTon.number);//类名+静态属性
        /* (这个设计模式叫饿汉式的原因：假设我们上面没有创建对象，
        这里在调用和创建对象无关的属性时也会直接调用，
        因为饿汉式里的对象是static，当我们调用该类的属性时会加载到类，
        加载类的时候就会执行static的东西，所以static对象就被创建了)
        饿汉式的核心就是“着急”，只要我们加载到该类，就会创建了对象，因为static

        饿汉式的缺点：
        -因为加载该类就会创建对象的原因，导致如果我们有调用该类的属性却没有使用对象时就会造成资源的浪费

         */

    }

}
