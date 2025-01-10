package com.miles_collins.models.resources;

public class Cash extends Resource {

    public Cash(int initialAmount) {
        super(initialAmount);
    }

    @Override
    public String getResourceType() {
        return "Cash";
    }

}
