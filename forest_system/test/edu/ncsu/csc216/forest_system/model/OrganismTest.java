/**
 * 
 */
package edu.ncsu.csc216.forest_system.model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for Organism class
 * @author Ken&co
 */
public class OrganismTest {

	MiddleAnimal testAnimal;
	Ecosystem grid;
	Cell location;
	Cell destination;
	private static final int BREED_LIMIT = 15; 
	LowAnimal testPrey;
	
	/**
	 * tests if setUp enacts items
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testAnimal = new MiddleAnimal('M');
		location = new Cell(0, 0);
		destination = new Cell(1, 0);
		grid = new Ecosystem(2, 2);
		testPrey = new LowAnimal('I');
	}

	
	//public void testSetRandomSeed() {
		//organismTest.setRandomSeed(1);
		//assertEquals(organismTest.randomGenerator, 1);
	//}

	/**
	 * tests if animals are alive
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Organism#isAlive()}.
	 */
	@Test
	public void testIsAlive() {
		
		assertTrue(testAnimal.isAlive());
		testAnimal.die();
		assertFalse(testAnimal.isAlive());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Organism#die()}.
	 * tests if animal can die
	 */
	@Test
	public void testDie() {
		assertTrue(testAnimal.isAlive());
		testAnimal.die();
		assertFalse(testAnimal.isAlive());
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Organism#canAct()}.
	 * tests if animal can act when it has been disabled, and enabled
	 */
	@Test
	public void testCanAct() {
		testAnimal.disable();
		assertFalse(testAnimal.canAct());
		testAnimal.enable();
		assertTrue(testAnimal.canAct());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Organism#enable()}.
	 * tests if animal can go from disabled to enabled 
	 */
	@Test
	public void testEnable() {
		testAnimal.disable();
		assertFalse(testAnimal.canAct());
		testAnimal.enable();
		assertTrue(testAnimal.canAct());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Organism#disable()}.
	 * tests if animal can go from enabled to disabled
	 */ 
	@Test
	public void testDisable() {
		testAnimal.enable();
		assertTrue(testAnimal.canAct());
		testAnimal.disable();
		assertFalse(testAnimal.canAct());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Organism#getTimeSinceLastBreed()}.
	 * tests if BreedLimit is smaller than time since bred to enable breeding
	 */
	@Test
	public void testGetTimeSinceLastBreed() {
		for (int i = 0; i < BREED_LIMIT; i++){
			testAnimal.incrementTimeSinceLastBreed();
		}
		assertTrue(testAnimal.breed(location, grid));
		assertEquals(testAnimal.getTimeSinceLastBreed(), 0);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Organism#getTimeSinceLastMeal()}.
	 * Tests if getTimeSinceLastMeal returns correct after incrementing time since ate
	 */
	@Test
	public void testGetTimeSinceLastMeal() {
		assertEquals(testAnimal.getTimeSinceLastMeal(), 0);
		testAnimal.incrementTimeSinceLastMeal();
		assertEquals(testAnimal.getTimeSinceLastMeal(), 1);
		grid.add(testPrey, destination);
		testAnimal.eat(location, grid);
		assertEquals(testAnimal.getTimeSinceLastMeal(), 0);
		

	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Organism#incrementTimeSinceLastBreed()}.
	 * tests if can increment time since last bred
	 */
	@Test
	public void testIncrementTimeSinceLastBreed() {
		assertEquals(testAnimal.getTimeSinceLastBreed(), 0);
		testAnimal.incrementTimeSinceLastBreed();
		assertEquals(testAnimal.getTimeSinceLastBreed(), 1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Organism#incrementTimeSinceLastMeal()}.
	 * tests if can increment time since last meal
	 */
	@Test
	public void testIncrementTimeSinceLastMeal() {
		assertEquals(testAnimal.getTimeSinceLastMeal(), 0);
		testAnimal.incrementTimeSinceLastMeal();
		assertEquals(testAnimal.getTimeSinceLastMeal(), 1);
		testAnimal.incrementTimeSinceLastMeal();
		assertEquals(testAnimal.getTimeSinceLastMeal(), 2);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Organism#getSymbol()}.
	 * tests if gets correct symbol from animal
	 */
	@Test
	public void testGetSymbol() {	
		assertEquals(testAnimal.getSymbol(), 'M');
	
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Organism#getColor()}.
	 * tests if gets correct color from organism
	 */
	@Test
	public void testGetColor() {
		assertEquals(testAnimal.getColor(), Color.orange);
	}

}
