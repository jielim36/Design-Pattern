package com.solid.DependencyInversionPrinciple.DIP_correction;

public class AmdCpu implements Cpu{
    @Override
    public void run() {
        System.out.println("Using AMD CPU...");
    }
}
