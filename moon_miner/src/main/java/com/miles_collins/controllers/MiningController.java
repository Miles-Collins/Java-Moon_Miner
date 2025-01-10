package com.miles_collins.controllers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.miles_collins.models.items.click.Rover;
import com.miles_collins.models.power.ClickPower;
import com.miles_collins.models.power.PassivePower;
import com.miles_collins.models.resources.Cash;
import com.miles_collins.models.resources.Gem;
import com.miles_collins.models.resources.ResourceManager;

public class MiningController {

    private static Rover rover;
    private static final ResourceManager resourceManager = new ResourceManager();
    private static final ClickPower clickPower = new ClickPower(1); // Initial click power
    private static final PassivePower passivePower = new PassivePower(0); // Initial passive power

    static {
        resourceManager.addResource(new Cash(1000)); // Initial cash
        resourceManager.addResource(new Gem(0)); // Initial gems
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "buyRover":
                    buyRover();
                    break;
                case "upgradeRover":
                    upgradeRover();
                    break;
                case "click":
                    click();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        } else {
            System.out.println("No command provided");
        }

        // Start passive power generation
        startPassivePowerGeneration();
    }

    public static void buyRover() {
        int roverCost = 100;
        if (resourceManager.getResource("Cash").getAmount() >= roverCost) {
            rover = new Rover("Basic Rover", roverCost);
            resourceManager.subtractAmount("Cash", roverCost);
            System.out.println("Bought: " + rover.getName() + " with cost: " + rover.getCost());
        } else {
            System.out.println("Not enough cash to buy a rover.");
        }
    }

    public static void upgradeRover() {
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

    public static void click() {
        resourceManager.addAmount("Cash", clickPower.clickPower());
        System.out.println("Clicked! Cash: " + resourceManager.getResource("Cash").getAmount());
    }

    private static void startPassivePowerGeneration() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            resourceManager.addAmount("Cash", passivePower.generatePassivePower());
            System.out.println("Passive power generated! Cash: " + resourceManager.getResource("Cash").getAmount());
        }, 0, 5, TimeUnit.SECONDS);
    }
}
