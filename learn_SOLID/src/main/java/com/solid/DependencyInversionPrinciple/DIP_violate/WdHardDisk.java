package com.solid.DependencyInversionPrinciple.DIP_violate;

public class WdHardDisk {
    public void save(String data){
        System.out.println("WD Hard disk saving data:" + data);
    }
    public String getData(){
        System.out.println("Fetching data from WD hard disk...");
        return "Hello WD!!";
    }
}
