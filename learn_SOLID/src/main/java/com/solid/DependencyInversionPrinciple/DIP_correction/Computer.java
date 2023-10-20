package com.solid.DependencyInversionPrinciple.DIP_correction;

public class Computer {

    //Using interface instead of an specific implementation class (IntelCpu class)
    private HardDisk hardDisk;
    private Cpu cpu;
    private Ram ram;

    public void computerStart(){
        System.out.println("Computer is running...");
        System.out.println("Data from hard disk is: " + hardDisk.getData());
        cpu.run();
        ram.save();
    }

    public HardDisk getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(HardDisk hardDisk) {
        this.hardDisk = hardDisk;
    }

    public Cpu getCpu() {
        return cpu;
    }

    public void setCpu(Cpu cpu) {
        this.cpu = cpu;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }
}
