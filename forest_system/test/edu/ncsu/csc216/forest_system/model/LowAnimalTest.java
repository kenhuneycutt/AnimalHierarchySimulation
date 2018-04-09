package edu.ncsu.csc216.forest_system.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests lowAnimal tests
 * @author Ken
 */
public class LowAnimalTest {

	LowAnimal testLow;
	Ecosystem grid;
	Cell location;
	private static final int FOOD_RANK = 200;
	
	/**
	 * The setUp
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testLow = new LowAnimal('I');
		testLow.enable();
		location = new Cell(0, 0);
		grid = new Ecosystem(2, 2);
	}

	/**
	 * tests if correct returned food rank
	 */
	@Test
	public void testGetFoodChainRank() {
		assertEquals(testLow.getFoodChainRank(), FOOD_RANK);
	}

	/**
	 * tests if correct baby is returned
	 */
	@Test
	public void testMakeNewBaby() {
		Item baby = testLow.makeNewBaby();
		assertEquals(baby.getSymbol(), 'I');
	}

	/**
	 * tests if act incremented the times for since bred and 
	 * since ate
	 */
	@Test
	public void testAct() {
		assertEquals(testLow.getTimeSinceLastBreed(), 0);
		assertEquals(testLow.getTimeSinceLastMeal(), 0);
		testLow.act(location, grid);
		assertEquals(testLow.getTimeSinceLastBreed(), 1);
	}

	/**
	 * tests whether returns true only when breed time is above limit
	 */
	@Test
	public void testPastBreedTime() {
		assertEquals(testLow.getTimeSinceLastBreed(), 0);
		assertFalse(testLow.pastBreedTime(testLow.getTimeSinceLastBreed() ));
		testLow.incrementTimeSinceLastBreed();
		assertEquals(testLow.getTimeSinceLastBreed(), 1);
		assertTrue(testLow.pastBreedTime(testLow.getTimeSinceLastBreed()));
		testLow.breed(location, grid);
		assertEquals(testLow.getTimeSinceLastBreed(), 0);
		assertFalse(testLow.pastBreedTime(testLow.getTimeSinceLastBreed()));
	}

}
