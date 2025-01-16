package com.miles_collins.models.items.passive;

import com.miles_collins.models.items.Item;

public class Rover extends Item {

    private static final int BASE_COST = 100;
    private static final String BASE_NAME_STRING = "Rover";

    public Rover(String name, int cost) {
        super(BASE_NAME_STRING, BASE_COST);
    }

    @Override
    public void performAction() {
        System.out.println("Rover is exploring the moon surface.");
    }
}
