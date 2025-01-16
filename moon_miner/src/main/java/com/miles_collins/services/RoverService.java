package com.miles_collins.services;

import com.miles_collins.models.items.passive.Rover;
import com.miles_collins.models.resources.ResourceManager;

public class RoverService {

    private Rover rover;
    private final ResourceManager resourceManager;

    public RoverService(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public void buyRover() {
        if (rover != null) {
            System.out.println("A rover has already been bought.");
            return;
        }
        int cost = rover.getCost();
        if (resourceManager.getResource("Cash").getAmount() >= cost) {
            rover = new Rover();
            resourceManager.subtractAmount("Cash", Rover.BASE_COST);
            System.out.println("Bought: " + rover.getName() + " with cost: " + rover.getCost());
        } else {
            System.out.println("Not enough cash to buy a rover.");
        }
    }

    public void upgradeRover() {
        if (rover != null) {
            int upgradeCost = rover.getCost();
            if (resourceManager.getResource("Cash").getAmount() >= upgradeCost) {
                rover.upgrade();
                resourceManager.subtractAmount("Cash", upgradeCost);
                System.out.println("Upgraded: " + rover.getName() + " to level: " + rover.getLevel() + " with new cost: " + rover.getCost());
            } else {
                System.out.println("Not enough cash to upgrade the rover.");
            }
        } else {
            System.out.println("No rover to upgrade. Please buy a rover first.");
        }
    }

    public Rover getRover() {
        return rover;
    }
}
