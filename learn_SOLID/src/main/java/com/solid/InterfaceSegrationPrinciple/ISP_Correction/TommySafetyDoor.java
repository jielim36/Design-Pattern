package com.solid.InterfaceSegrationPrinciple.ISP_Correction;

import com.solid.InterfaceSegrationPrinciple.ISP_Violate.SafetyDoor;

public class TommySafetyDoor implements antiTheft,waterProof {//implement the interfaces that you need
    @Override
    public void antiTheft() {
        System.out.println("Anti-Theft");
    }
    @Override
    public void waterProof() {
        System.out.println("Waterproof");
    }
    //No need to implement the fireProof() method if don't use it.
}
