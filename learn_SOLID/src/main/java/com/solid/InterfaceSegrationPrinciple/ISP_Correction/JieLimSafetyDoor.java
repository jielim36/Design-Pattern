package com.solid.InterfaceSegrationPrinciple.ISP_Correction;

import com.solid.InterfaceSegrationPrinciple.ISP_Violate.SafetyDoor;

public class JieLimSafetyDoor implements antiTheft,fireProof,waterProof {
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
