package com.miles_collins.models.power;

public class PassivePower extends Power {

    public PassivePower(int initialPower) {
        super(initialPower);
    }

    @Override
    public void performAction() {
        System.out.println("Passive power activated! Current power: " + power);
    }

    public int generatePassivePower() {
        return power;
    }
}
