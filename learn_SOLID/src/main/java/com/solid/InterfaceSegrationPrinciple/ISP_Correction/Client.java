package com.solid.InterfaceSegrationPrinciple.ISP_Correction;

public class Client {
    public static void main(String[] args) {
        JieLimSafetyDoor door = new JieLimSafetyDoor();
        door.antiTheft();
        door.fireProof();
        door.waterProof();
    }
}
