package com.solid.InterfaceSegrationPrinciple.ISP_Violate;

public class JieLimSafetyDoor implements SafetyDoor{
    @Override
    public void antiTheft() {
        System.out.println("Anti-Theft");
    }
    @Override
    public void fireProof() {
        System.out.println("Fireproof");
    }
    @Override
    public void waterProof() {
        System.out.println("Waterproof");
    }
}
