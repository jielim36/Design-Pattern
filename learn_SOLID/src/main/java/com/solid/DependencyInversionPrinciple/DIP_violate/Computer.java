package com.solid.DependencyInversionPrinciple.DIP_violate;

public class Computer {

    //Component (specific brand)
    private WdHardDisk hardDisk;
    private IntelCpu cpu;
    private KingstonRam ram;

    public void computerStart(){
        System.out.println("Computer is running...");
        System.out.println("Data from hard disk is: " + hardDisk.getData());
        cpu.run();
        ram.save();
    }
    public WdHardDisk getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(WdHardDisk hardDisk) {
        this.hardDisk = hardDisk;
    }

    public IntelCpu getCpu() {
        return cpu;
    }

    public void setCpu(IntelCpu cpu) {
        this.cpu = cpu;
    }

    public KingstonRam getRam() {
        return ram;
    }

    public void setRam(KingstonRam ram) {
        this.ram = ram;
    }
}
