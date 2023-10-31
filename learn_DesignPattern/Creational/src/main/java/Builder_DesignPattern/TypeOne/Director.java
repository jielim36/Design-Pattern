package Builder_DesignPattern.TypeOne;

public class Director {

    private Builder builder;

    public Director(Builder builder){
        this.builder = builder;
    }

    public Computer construct(){
        builder.buildRam();
        builder.buildCpu();
        return builder.computer;
    }

}
