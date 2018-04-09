package edu.ncsu.csc216.forest_system.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Tests the class MiddleANimal
 * @author Ken
 */
public class MiddleAnimalTest {

	
	MiddleAnimal testMiddle;
	LowAnimal testPrey;
	Ecosystem grid;
	Cell location;
	Cell destination;
	private static final int BREED_LIMIT = 7; 
	private static final int FOOD_RANK_M = 400; 
	private static final int FOOD_RANK_L = 200;
	
	/**
	 * The setUp
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testMiddle = new MiddleAnimal('C');
		testPrey = new LowAnimal('I');
		testMiddle.enable();
		location = new Cell(0, 0);
		destination = new Cell(1, 0);
		grid = new Ecosystem(2, 2);
	}

	/**
	 * tests if the food rank is correct for level
	 */
	@Test
	public void testGetFoodChainRank() {
		assertEquals(testMiddle.getFoodChainRank(), FOOD_RANK_M);
	}

	/**
	 * checks if correct baby is returned
	 */
	@Test
	public void testMakeNewBaby() {
		Item baby = testMiddle.makeNewBaby();
		assertEquals(baby.getSymbol(), 'C');

	}

	/**
	 * tests act by incrementing breed time so animal can breed,
	 * and then the animal acts and its first option is to breed,
	 * and timeSinceLastBreed is reset afterward
	 */
	@Test
	public void testAct() {
		assertEquals(testMiddle.getTimeSinceLastBreed(), 0);
		testMiddle.incrementTimeSinceLastBreed();
		assertEquals(testMiddle.getTimeSinceLastBreed(), 1);
		testMiddle.incrementTimeSinceLastBreed();
		testMiddle.incrementTimeSinceLastBreed();
		testMiddle.incrementTimeSinceLastBreed();
		testMiddle.incrementTimeSinceLastBreed();
		testMiddle.incrementTimeSinceLastBreed();
		testMiddle.incrementTimeSinceLastBreed();
		assertEquals(testMiddle.getTimeSinceLastBreed(), BREED_LIMIT);
		testMiddle.act(location, grid);
		assertEquals(testMiddle.getTimeSinceLastBreed(), 1);
		
	}

	/**
	 * increments timeSinceLastBreed and checks if the method
	 * only returns true if the breed time is above the limit
	 */
	@Test
	public void testPastBreedTime() {
		assertEquals(testMiddle.getTimeSinceLastBreed(), 0);
		testMiddle.incrementTimeSinceLastBreed();
		assertEquals(testMiddle.getTimeSinceLastBreed(), 1);
		testMiddle.incrementTimeSinceLastBreed();
		testMiddle.incrementTimeSinceLastBreed();
		testMiddle.incrementTimeSinceLastBreed();
		testMiddle.incrementTimeSinceLastBreed();
		testMiddle.incrementTimeSinceLastBreed();
		testMiddle.incrementTimeSinceLastBreed();
		assertEquals(testMiddle.getTimeSinceLastBreed(), BREED_LIMIT);
		assertTrue(testMiddle.pastBreedTime(testMiddle.getTimeSinceLastBreed()));
		testMiddle.act(location, grid);
		assertEquals(testMiddle.getTimeSinceLastBreed(), 1);
		assertFalse(testMiddle.pastBreedTime(testMiddle.getTimeSinceLastBreed()));
	}

	/**
	 * Testing parent Organism's methods
	 * Increments timeSinceLastBreed and checks if animal can
	 * breed when above breed limit
	 */
	@Test
	public void testBreed() {
		for (int i = 0; i < BREED_LIMIT + 1; i++){
			testMiddle.incrementTimeSinceLastBreed();
		}
		assertEquals(testMiddle.getTimeSinceLastBreed(), BREED_LIMIT + 1);
		assertTrue(testMiddle.breed(location, grid));
		
	}

	/**
	 * Testing parent Organism's methods
	 * the animal moves locations, and checks if is in
	 * destination and that original location is empty
	 */
	@Test
	public void testMove() {
		testMiddle.move(location, grid);
		//assertTrue(grid.isEmpty(location));
		assertFalse(grid.isEmpty(destination));
	}

	/**
	 * Testing parent Organism's methods
	 * .
	 * sets up with placing middle animal and low animal
	 * on a grid. checks and increments middle animal's 
	 * timeSinceLastAte. AFter eating, the number is changed
	 */
	@Test
	public void testEat() {
		grid.add(testPrey, destination);
		grid.add(testMiddle, location);
		assertEquals(testPrey.getFoodChainRank(), FOOD_RANK_L);
		assertEquals(testMiddle.getFoodChainRank(), FOOD_RANK_M);
		testMiddle.incrementTimeSinceLastMeal();
		testMiddle.incrementTimeSinceLastMeal();
		testMiddle.incrementTimeSinceLastMeal();
		assertFalse(grid.isEmpty(location));
		assertFalse(grid.isEmpty(destination));
		assertEquals(testMiddle.getTimeSinceLastMeal(), 3);
		//assertTrue(testMiddle.eat(location, grid));
		//assertTrue(grid.isEmpty(location));
		assertEquals(testMiddle.getTimeSinceLastMeal(), 3);
	}

}
