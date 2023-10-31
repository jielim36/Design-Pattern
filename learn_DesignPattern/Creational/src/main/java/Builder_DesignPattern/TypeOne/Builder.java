package Builder_DesignPattern.TypeOne;

public abstract class Builder {

    protected Computer computer = new Computer();

    public abstract void buildRam();
    public abstract void buildCpu();

    public abstract Computer createComputer();

    //if your system structure is simple
//    public Computer construct(){
//        this.buildRam();
//        this.buildCpu();
//        return this.computer;
//    }


}
