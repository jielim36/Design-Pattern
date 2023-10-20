package com.solid.DependencyInversionPrinciple.DIP_correction;

public class ComputerDemo {
    public static void main(String[] args) {
        //Polymorphism: InterfaceClass objName = new ImplementationClass();
        HardDisk hardDisk = new WdHardDisk();
        Cpu cpu = new IntelCpu();
        Ram ram = new KingstonRam();

        Computer computer = new Computer();
        computer.setCpu(cpu);
        computer.setRam(ram);
        computer.setHardDisk(hardDisk);
        computer.computerStart();

        System.out.println("===============");

        cpu = new AmdCpu();//Change to different brand
        computer.setCpu(cpu);
        computer.computerStart();
    }
}
