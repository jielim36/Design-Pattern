package example02;
/*
单例模式中的懒汉式的核心：
1.将构造器私有化
2.定义一个static静态属性对象
3.提供一个public的static方法，可以返回一个Cat对象
4.懒汉式，只要当用户使用getInstance方法时，才会返回对象，后面再次调用时，会返回上次创建的cat对象（保证了单例）
 */
public class SingleTon {
    public static int number1 = 100;
  private String name;
  private static SingleTon cat;//这里默认指向null
    // 在懒汉式里，定义的static对象指向null，而不是new SingleTon(); 所以当外部调用内部的属性就不会生成对象而造成资源浪费
    //但是这个静态对象其实在外部调用这里的属性时也会被加载到，因为他是static属性，但是因为它没有new SingleTon();所以不算是创建对象
    private SingleTon(String name) {
        System.out.println("构造器被调用");
//如果外部调用内部的static属性（外部调用内部的可能性只有static属性/方法，因为其他普通属性需要创建对象才能调用，static可以直接通过类名调用）时构造器是不会被执行的，
        // 除非我们有创建对象，但是我们的对象只有SingleTon cat,没有像饿汉式的一样有 = new SingleTon();
        //如果是饿汉式，当外部调用某个static属性时就会调用构造器，因为构造器被调用的原理是有创建对象，
        // 而饿汉式里只要外部调用某个static属性就会生成创建对象，所以导致调用构造器
        this.name = name;
    }

    public static SingleTon getInstance(){//懒汉式里，当外部需要对象时需要调用这个方法才会生成/创建对象
        if(cat == null){//当外部第一次调用该方法用于创建对象时，这里会把cat创建成对象（new SingleTon("小可爱");）
            //当外部第二次或以上时，cat就不等于null了（因为它是SingleTon对象了），所以就不会重新创建新的对象
            cat = new SingleTon("小可爱");
        }
        return cat;
    }

    @Override
    public String toString() {
        return "SingleTon{" +
                "name='" + name + '\'' +
                '}';
    }
}
