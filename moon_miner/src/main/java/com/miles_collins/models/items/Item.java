package com.miles_collins.models.items;

public abstract class Item {

    protected String name;
    protected int cost;
    protected int level;

    public Item(String name, int cost) {
        this.name = name;
        this.cost = cost;
        this.level = 1;
    }

    public void upgrade() {
        level++;
        cost *= 1.5; // Example cost increase formula
        System.out.println(name + " upgraded to level " + level + ". New cost: " + cost);
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getLevel() {
        return level;
    }

    public abstract void performAction();
}
