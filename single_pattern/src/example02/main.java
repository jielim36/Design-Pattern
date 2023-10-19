package example02;

/*
饿汉式和懒汉式的区别
饿汉式在类加载时就创建好了一个对象（原理是static）
懒汉式：没有使用该类的对象它就不会有对象
 */

public class main {
    public static void main(String[] args) {
        System.out.println(SingleTon.number1);

        SingleTon cat = SingleTon.getInstance();
        System.out.println(cat);//toString

        SingleTon cat2 = SingleTon.getInstance();//这里新创建的cat2和cat这两个对象都是一样的（地址）
        System.out.println(cat2);

        System.out.println(cat == cat2);

    }
}
