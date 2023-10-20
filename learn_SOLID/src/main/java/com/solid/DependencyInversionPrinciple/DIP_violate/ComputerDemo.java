package com.solid.DependencyInversionPrinciple.DIP_violate;

public class ComputerDemo {
    public static void main(String[] args) {
        //Create component of computer (specific brand)
        WdHardDisk hardDisk = new WdHardDisk();
        IntelCpu cpu = new IntelCpu();
        KingstonRam ram = new KingstonRam();

        Computer computer = new Computer();
        computer.setCpu(cpu);
        computer.setRam(ram);
        computer.setHardDisk(hardDisk);
        computer.computerStart();
    }
}
