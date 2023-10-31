package Builder_DesignPattern.TypeOne;

public class AcerComputerBuilder extends Builder{
    @Override
    public void buildRam() {
        computer.setRam("(Acer) 8GB RAM");
    }

    @Override
    public void buildCpu() {
        computer.setCpu("(Acer) AMD CPU");
    }

    @Override
    public Computer createComputer() {
        return computer;
    }
}
