package Builder_DesignPattern.TypeOne;

public class AsusComputerBuilder extends Builder{
    @Override
    public void buildRam() {
        computer.setRam("(Asus) 16GB RAM");
    }

    @Override
    public void buildCpu() {
        computer.setCpu("(Asus) Intel CPU");
    }

    @Override
    public Computer createComputer() {
        return computer;
    }
}
