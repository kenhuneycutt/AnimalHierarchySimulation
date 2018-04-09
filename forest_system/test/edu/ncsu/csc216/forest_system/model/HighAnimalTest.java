package edu.ncsu.csc216.forest_system.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for class HighAnimal
 * @author Ken&co
 *
 */
public class HighAnimalTest {

	HighAnimal testHigh;
	Ecosystem grid;
	Cell location;
	private static final int BREED_LIMIT = 15; 
	private static final int FOOD_RANK = 500;
	
	/**
	 * The setUp
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testHigh = new HighAnimal('O');
		testHigh.enable();
		location = new Cell(0, 0);
		grid = new Ecosystem(2, 2);
	}

	/**
	 * tests if correct food rank is returned
	 */
	@Test
	public void testGetFoodChainRank() {
		assertEquals(testHigh.getFoodChainRank(), FOOD_RANK);
	}

	/**
	 * tests if correct baby organism is returned
	 */
	@Test
	public void testMakeNewBaby() {
		Item baby = testHigh.makeNewBaby();
		assertEquals(baby.getSymbol(), 'O');
	}

	/**
	 * tests if act increments time since ate and bred
	 */
	@Test
	public void testAct() {
		assertEquals(testHigh.getTimeSinceLastBreed(), 0);
		assertEquals(testHigh.getTimeSinceLastMeal(), 0);
		testHigh.act(location, grid);
		assertEquals(testHigh.getTimeSinceLastBreed(), 1);
		assertEquals(testHigh.getTimeSinceLastMeal(), 1);
	}

	/**
	 * tests if returns true only when breed time is
	 * above the breed limit
	 */
	@Test
	public void testPastBreedTime() {
		assertEquals(testHigh.getTimeSinceLastBreed(), 0);
		assertFalse(testHigh.pastBreedTime(testHigh.getTimeSinceLastBreed() ));
		for (int i = 0; i < BREED_LIMIT; i++){
			testHigh.incrementTimeSinceLastBreed();
		}
		assertEquals(testHigh.getTimeSinceLastBreed(), BREED_LIMIT);
		assertTrue(testHigh.pastBreedTime(testHigh.getTimeSinceLastBreed()));
		testHigh.breed(location, grid);
		assertEquals(testHigh.getTimeSinceLastBreed(), 0);
		assertFalse(testHigh.pastBreedTime(testHigh.getTimeSinceLastBreed()));
	}

}
