package com.miles_collins;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.miles_collins.controllers.MiningController;
import com.miles_collins.models.items.passive.Rover;
import com.miles_collins.models.resources.Cash;
import com.miles_collins.models.resources.ResourceManager;

public class MiningControllerTest {

    private ResourceManager resourceManager;

    @BeforeEach
    void setUp() {
        resourceManager = new ResourceManager();
        resourceManager.addResource(new Cash(1000)); // Add 1000 cash to the resource manager
        MiningController.setResourceManager(resourceManager);
    }

    @Test
    void testBuyRover() {
        MiningController.buyRover();
        Rover rover = MiningController.getRover();
        assertNotNull(rover);
        assertEquals("Rover", rover.getName());
        assertEquals(100, rover.getCost());
        assertEquals(900, resourceManager.getResource("Cash").getAmount());
    }

    @Test
    void testUpgradeRover() {
        MiningController.buyRover();
        MiningController.upgradeRover();
        Rover rover = MiningController.getRover();
        assertNotNull(rover);
        assertEquals(2, rover.getLevel());
        assertEquals(150, rover.getCost());
        assertEquals(750, resourceManager.getResource("Cash").getAmount());
    }

    @Test
    void testUpgradeRoverTwice() {
        MiningController.buyRover();
        MiningController.upgradeRover();
        MiningController.upgradeRover();
        Rover rover = MiningController.getRover();
        assertNotNull(rover);
        assertEquals(3, rover.getLevel());
        assertEquals(225, rover.getCost());
        assertEquals(525, resourceManager.getResource("Cash").getAmount());
    }

    @Test
    void testUpgradeRoverThrice() {
        MiningController.buyRover();
        MiningController.upgradeRover();
        MiningController.upgradeRover();
        MiningController.upgradeRover();
        Rover rover = MiningController.getRover();
        assertNotNull(rover);
        assertEquals(4, rover.getLevel());
        assertEquals(338, rover.getCost());
        assertEquals(187, resourceManager.getResource("Cash").getAmount());
    }

    @Test
    void testUpgradeRoverWithoutBuying() {
        MiningController.upgradeRover();
        assertNull(MiningController.getRover());
    }

    @Test
    void testBuyRoverWithoutEnoughCash() {
        resourceManager.subtractAmount("Cash", 950); // Reduce cash to 50
        MiningController.buyRover();
        assertNull(MiningController.getRover());
        assertEquals(50, resourceManager.getResource("Cash").getAmount());
    }

    @Test
    void testCannotBuySecondRover() {
        // Buy the first Rover
        MiningController.buyRover();
        Rover firstRover = MiningController.getRover();
        assertNotNull(firstRover);
        assertEquals("Rover", firstRover.getName());
        assertEquals(100, firstRover.getCost());
        assertEquals(900, resourceManager.getResource("Cash").getAmount());

        // Attempt to buy a second Rover
        MiningController.buyRover();
        Rover secondRover = MiningController.getRover();
        assertSame(firstRover, secondRover); // Ensure the same Rover instance is returned
        assertEquals(900, resourceManager.getResource("Cash").getAmount()); // Ensure cash has not changed
    }
}
