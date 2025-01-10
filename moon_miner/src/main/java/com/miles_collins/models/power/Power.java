package com.miles_collins.models.power;

public abstract class Power {

    protected int power;

    public Power(int initialPower) {
        this.power = initialPower;
    }

    public int getPower() {
        return power;
    }

    public void increasePower(int amount) {
        power += amount;
    }

    public void resetPower() {
        power = 0;
    }

    public abstract void performAction();
}
