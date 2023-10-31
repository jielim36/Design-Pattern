package Builder_DesignPattern.TypeTwo.after;

public class Client {
    public static void main(String[] args) {
        Computer computer =  new Computer.Builder() //create a Builder class to call builder's method
                .cpu("Intel") //set the data of cpu and return back Builder class
                .ram("Kingston 8GB")//ram can be call after invoke cpu() method because cpu() method return Builder class "this" keyword.
                .storage("WD SSD 500GB")
                .brand("Asus")
                .build();//invoke build() to call the Computer's private constructor to create a Computer object
        System.out.println(computer);
    }
}
