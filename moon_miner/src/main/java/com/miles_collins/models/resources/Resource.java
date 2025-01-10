package com.miles_collins.models.resources;

public abstract class Resource {

    protected int amount;

    public Resource(int initialAmount) {
        this.amount = initialAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }

    public void subtractAmount(int amount) {
        if (this.amount >= amount) {
            this.amount -= amount;
        } else {
            System.out.println("Insufficient amount to subtract.");
        }
    }

    public abstract String getResourceType();
}
