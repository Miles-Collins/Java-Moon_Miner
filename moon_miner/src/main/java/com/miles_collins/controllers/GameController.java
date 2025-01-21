package com.miles_collins.controllers;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.miles_collins.models.items.passive.GoblinSquire;
import com.miles_collins.models.power.ClickPower;
import com.miles_collins.models.power.PassivePower;
import com.miles_collins.models.resources.Gem;
import com.miles_collins.models.resources.Gold;
import com.miles_collins.models.resources.ResourceManager;
import com.miles_collins.services.RoverService;

public class GameController {

    private static final RoverService roverService;
    private static ResourceManager resourceManager;
    private static final ClickPower clickPower = new ClickPower(1); // Initial click power
    private static final PassivePower passivePower = new PassivePower(0); // Initial passive power

    static {
        initializeResourceManager();
        roverService = new RoverService(resourceManager);
    }

    private static void initializeResourceManager() {
        resourceManager = new ResourceManager();
        resourceManager.addResource(new Gold(1000)); // Initial cash
        resourceManager.addResource(new Gem(0)); // Initial gems
    }

    public static void main(String[] args) {
        // Start passive power generation
        startPassivePowerGeneration();
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

    public static void setResourceManager(ResourceManager resourceManager) {
        MiningController.resourceManager = resourceManager;
    }

    public static GoblinSquire getRover() {
        return roverService.getRover();
    }
}
