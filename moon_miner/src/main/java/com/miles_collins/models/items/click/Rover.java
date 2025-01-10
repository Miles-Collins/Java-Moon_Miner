package com.miles_collins.models.items.click;

import com.miles_collins.models.items.Item;

public class Rover extends Item {

    public Rover(String name, int cost) {
        super("Rover", cost);
    }

    @Override
    public void performAction() {
        System.out.println("Rover is exploring the moon surface.");
    }

}
