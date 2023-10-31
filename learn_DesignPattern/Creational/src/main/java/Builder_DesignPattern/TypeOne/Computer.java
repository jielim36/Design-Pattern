package Builder_DesignPattern.TypeOne;

public class Computer {

    private String Ram;
    private String Cpu;

    public String getRam() {
        return Ram;
    }

    public void setRam(String ram) {
        Ram = ram;
    }

    public String getCpu() {
        return Cpu;
    }

    public void setCpu(String cpu) {
        Cpu = cpu;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "Ram='" + Ram + '\'' +
                ", Cpu='" + Cpu + '\'' +
                '}';
    }
}
