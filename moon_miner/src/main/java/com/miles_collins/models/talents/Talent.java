package com.miles_collins.models.talents;

public abstract class Talent {

    protected String name;
    protected int level;
    protected int cost;

    public Talent(String name, int cost) {
        this.name = name;
        this.level = 0;
        this.cost = cost;
    }

    public void upgrade() {
        level++;
        System.out.println(name + " upgraded to level " + level);
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
