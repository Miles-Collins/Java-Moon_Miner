package com.miles_collins.models.resources;

public class Gold extends Resource {

    public Gold(int initialAmount) {
        super(initialAmount);
    }

    @Override
    public String getResourceType() {
        return "Gold";
    }

}
