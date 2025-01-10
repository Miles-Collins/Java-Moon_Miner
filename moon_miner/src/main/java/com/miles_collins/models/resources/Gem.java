package com.miles_collins.models.resources;

public class Gem extends Resource {

    public Gem(int initialAmount) {
        super(initialAmount);
    }

    @Override
    public String getResourceType() {
        return "Gem";
    }

}
