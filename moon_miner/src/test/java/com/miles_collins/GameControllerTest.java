package com.miles_collins;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.miles_collins.controllers.GameController;
import com.miles_collins.models.items.passive.GoblinSquire;
import com.miles_collins.models.resources.Gold;
import com.miles_collins.models.resources.ResourceManager;

public class GameControllerTest {

    private ResourceManager resourceManager;

    @BeforeEach
    void setUp() {
        resourceManager = new ResourceManager();
        resourceManager.addResource(new Gold(1000)); // Add 1000 cash to the resource manager
        GameController.setResourceManager(resourceManager);
    }

    @Test
    void testBuyRover() {
        GameController.buyRover();
        GoblinSquire goblinSquire = GameController.getRover();
        assertNotNull(rover);
        assertEquals("Goblin Squire", goblinSquire.getName());
        assertEquals(100, goblinSquire.getCost());
        assertEquals(900, resourceManager.getResource("Cash").getAmount());
    }

    @Test
    void testUpgradeRover() {
        GameController.buyRover();
        GameController.upgradeRover();
        GoblinSquire rover = GameController.getRover();
        assertNotNull(rover);
        assertEquals(2, rover.getLevel());
        assertEquals(150, rover.getCost());
        assertEquals(750, resourceManager.getResource("Cash").getAmount());
    }

    @Test
    void testUpgradeRoverTwice() {
        GameController.buyRover();
        GameController.upgradeRover();
        GameController.upgradeRover();
        GoblinSquire rover = GameController.getRover();
        assertNotNull(rover);
        assertEquals(3, rover.getLevel());
        assertEquals(225, rover.getCost());
        assertEquals(525, resourceManager.getResource("Cash").getAmount());
    }

    @Test
    void testUpgradeRoverThrice() {
        GameController.buyRover();
        GameController.upgradeRover();
        GameController.upgradeRover();
        GameController.upgradeRover();
        GoblinSquire rover = GameController.getRover();
        assertNotNull(rover);
        assertEquals(4, rover.getLevel());
        assertEquals(338, rover.getCost());
        assertEquals(187, resourceManager.getResource("Cash").getAmount());
    }

    @Test
    void testUpgradeRoverWithoutBuying() {
        GameController.upgradeRover();
        assertNull(GameController.getRover());
    }

    @Test
    void testBuyRoverWithoutEnoughCash() {
        resourceManager.subtractAmount("Cash", 950); // Reduce cash to 50
        GameController.buyRover();
        assertNull(GameController.getRover());
        assertEquals(50, resourceManager.getResource("Cash").getAmount());
    }

    @Test
    void testCannotBuySecondRover() {
        // Buy the first Rover
        GameController.buyRover();
        GoblinSquire firstRover = GameController.getRover();
        assertNotNull(firstRover);
        assertEquals("Rover", firstRover.getName());
        assertEquals(100, firstRover.getCost());
        assertEquals(900, resourceManager.getResource("Cash").getAmount());

        // Attempt to buy a second Rover
        GameController.buyRover();
        GoblinSquire secondRover = GameController.getRover();
        assertSame(firstRover, secondRover); // Ensure the same Rover instance is returned
        assertEquals(900, resourceManager.getResource("Cash").getAmount()); // Ensure cash has not changed
    }
}
