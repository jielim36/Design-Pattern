package com.solid.InterfaceSegrationPrinciple.ISP_Violate;

public class TommySafetyDoor implements SafetyDoor{
    @Override
    public void antiTheft() {
        System.out.println("Anti-Theft");
    }

    @Override
    public void fireProof() {
        //None
    }

    @Override
    public void waterProof() {
        System.out.println("Waterproof");
    }
}
