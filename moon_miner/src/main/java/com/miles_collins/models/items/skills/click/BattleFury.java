package com.miles_collins.models.items.skills.click;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;

import com.miles_collins.models.items.Item;

public class BattleFury extends Item {

    private double damageMultiplier;
    private boolean isActive;
    private boolean isOnCooldown;

    private static final int BASE_COST = 100;
    private static final String BASE_NAME_STRING = "Battle Fury";
    private static final int DURATION = 30000;
    private static final int COOLDOWN = 120000;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public BattleFury() {
        super(BASE_NAME_STRING, BASE_COST);
        this.damageMultiplier = 1.1;
        this.isActive = false;
        this.isOnCooldown = false;
    }

    @Override
    public void upgrade() {
        level++;
        if (level % 10 == 0) {
            cost *= 5;
            damageMultiplier *= 2;
        } else {
            cost *= 1.5;
            damageMultiplier += 0.1;
        }
        System.out.println(name + " upgraded to level " + level + ". New cost: " + cost);
    }

    @Override
    public void performAction() {
        if (isOnCooldown) {
            System.out.println(name + " is on cooldown.");
        } else if (!isActive) {
            activateSkill();
        } else {
            System.out.println(name + " is already active.");
        }
    }

    private void activateSkill() {
        isActive = true;
        isOnCooldown = true;
        System.out.println(name + " activated. Damage multiplier: " + getDamageMultiplier());

        // Schedule deactivation after DURATION
        scheduler.schedule(this::deactivateSkill, DURATION, TimeUnit.MILLISECONDS);
    }

    private void deactivateSkill() {
        isActive = false;
        System.out.println(name + " deactivated.");

        // Schedule cooldown end after COOLDOWN
        scheduler.schedule(this::endCooldown, COOLDOWN, TimeUnit.MILLISECONDS);
    }

    public double getDamageMultiplier() {
        return damageMultiplier;
    }

    private void endCooldown() {
        isOnCooldown = false;
        System.out.println(name + " cooldown ended.");
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isOnCooldown() {
        return isOnCooldown;
    }
}
