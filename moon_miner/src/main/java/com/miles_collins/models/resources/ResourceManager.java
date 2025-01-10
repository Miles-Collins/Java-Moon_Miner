package com.miles_collins.models.resources;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {

    private Map<String, Resource> resources = new HashMap<>();

    public void addResource(Resource resource) {
        resources.put(resource.getResourceType(), resource);
    }

    public Resource getResource(String resourceType) {
        return resources.get(resourceType);
    }

    public void addAmount(String resourceType, int amount) {
        Resource resource = resources.get(resourceType);
        if (resource != null) {
            resource.addAmount(amount);
        } else {
            System.out.println("Resource type not found.");
        }
    }

    public void subtractAmount(String resourceType, int amount) {
        Resource resource = resources.get(resourceType);
        if (resource != null) {
            resource.subtractAmount(amount);
        } else {
            System.out.println("Resource type not found.");
        }
    }
}
