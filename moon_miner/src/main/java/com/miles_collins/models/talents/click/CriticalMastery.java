package com.miles_collins.models.talents.click;

import com.miles_collins.models.talents.Talent;

public class CriticalMastery extends Talent {

    private static final int MAX_LEVEL = 100;
    private static final double CRITICAL_HIT_INCREMENT = 0.001;
    private static final double INITIAL_COST_MULTIPLIER = 1.5;
    private static final double MAX_CRIT_CHANCE = 0.5;
    private double critChance;
    private double costMultiplier;

    public CriticalMastery() {
        super("Critical Mastery", 100);
        this.critChance = 0.05;
        this.costMultiplier = INITIAL_COST_MULTIPLIER;
    }

    @Override
    public void upgrade() {
        if (level < MAX_LEVEL) {
            level++;
            critChance += CRITICAL_HIT_INCREMENT;
            if (critChance > MAX_CRIT_CHANCE) {
                critChance = MAX_CRIT_CHANCE;
            }
            cost *= costMultiplier;
            if (level % 10 == 0) {
                costMultiplier += 0.25;
            }
            System.out.println(name + " upgraded to level " + level + ". Crit chance: " + (critChance * 100) + "%. New cost: " + cost + ". Cost multiplier: " + costMultiplier);
        } else {
            System.out.println(name + " is already at max level.");
        }
    }

    public void reset() {
        level = 0;
        critChance = 0.05;
        cost = 100;
        costMultiplier = INITIAL_COST_MULTIPLIER;
        System.out.println(name + " has been reset.");
    }

    public double getCritChance() {
        return critChance;
    }

    public int getUpgradeCost() {
        return cost;
    }
}
