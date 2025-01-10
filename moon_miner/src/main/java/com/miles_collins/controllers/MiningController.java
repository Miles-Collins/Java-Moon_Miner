package com.miles_collins.controllers;

import com.miles_collins.models.items.click.Rover;
import com.miles_collins.models.power.ClickPower;

public class MiningController {

    public static void main(String[] args) {
        System.out.println("Mining Controller Initialized");
        ClickPower clickPower = new ClickPower(1);
        clickPower.click();
        buyRover();
    }

    public static void buyRover() {
        Rover rover = new Rover("Basic Rover", 100);
        System.out.println("Created: " + rover.getName() + " with cost: " + rover.getCost());
        rover.performAction();
        rover.upgrade();
        System.out.println("Upgraded: " + rover.getName() + " to level: " + rover.getLevel() + " with new cost: " + rover.getCost());
    }
}
