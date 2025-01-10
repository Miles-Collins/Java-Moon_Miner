package com.miles_collins.models.power;

public class ClickPower extends Power {

    public ClickPower(int initialPower) {
        super(initialPower);
    }

    public int clickPower() {
        return power;
    }

    @Override
    public void performAction() {
        System.out.println("Click power activated! Current power: " + power);
    }
}
