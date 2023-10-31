package Builder_DesignPattern.TypeTwo.after;

public class Computer {

    private String cpu;
    private String ram;
    private String storage;
    private String brand;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.brand = builder.brand;
    }

    public static final class Builder{
        private String cpu;
        private String ram;
        private String storage;
        private String brand;

        public Builder cpu(String cpu){
            this.cpu = cpu;
            return this;//return builder
        }

        public Builder ram(String ram){
            this.ram = ram;
            return this;
        }

        public Builder storage(String storage){
            this.storage = storage;
            return this;
        }

        public Builder brand(String brand){
            this.brand = brand;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }

    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
