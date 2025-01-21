package com.miles_collins.services;

import com.miles_collins.models.items.passive.GoblinSquire;
import com.miles_collins.models.resources.ResourceManager;

public class RoverService {

    private GoblinSquire goblinSquire;
    private final ResourceManager resourceManager;

    public RoverService(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void buyGoblin() {
        if (goblinSquire != null) {
            System.out.println("A rover has already been bought.");
            return;
        }
        int cost = goblinSquire.getCost();
        if (resourceManager.getResource("Gold").getAmount() >= cost) {
            goblinSquire = new GoblinSquire();
            resourceManager.subtractAmount("Cash", goblinSquire.BASE_COST);
            System.out.println("Bought: " + goblinSquire.getName() + " with cost: " + goblinSquire.getCost());
        } else {
            System.out.println("Not enough cash to buy a goblinSquire.");
        }
    }

    public void upgradeRover() {
        if (goblinSquire != null) {
            int upgradeCost = goblinSquire.getCost();
            if (resourceManager.getResource("Cash").getAmount() >= upgradeCost) {
                goblinSquire.upgrade();
                resourceManager.subtractAmount("Cash", upgradeCost);
                System.out.println("Upgraded: " + goblinSquire.getName() + " to level: " + goblinSquire.getLevel() + " with new cost: " + goblinSquire.getCost());
            } else {
                System.out.println("Not enough cash to upgrade the rover.");
            }
        } else {
            System.out.println("No rover to upgrade. Please buy a rover first.");
        }
    }

    public GoblinSquire getRover() {
        return goblinSquire;
    }
}
