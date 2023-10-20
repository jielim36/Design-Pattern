package com.solid.DependencyInversionPrinciple.DIP_correction;

public class WdHardDisk implements HardDisk{

    public void save(String data){
        System.out.println("WD Hard disk saving data:" + data);
    }

    public String getData(){
        System.out.println("Fetching data from WD hard disk...");
        return "Hello WD!!";
    }

}
