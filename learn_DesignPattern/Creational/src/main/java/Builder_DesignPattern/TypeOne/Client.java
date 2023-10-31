package Builder_DesignPattern.TypeOne;

public class Client {
    public static void main(String[] args) {
        Director director = new Director(new AsusComputerBuilder());
        Computer computer = director.construct();
        System.out.println(computer);//toString()
    }
}
