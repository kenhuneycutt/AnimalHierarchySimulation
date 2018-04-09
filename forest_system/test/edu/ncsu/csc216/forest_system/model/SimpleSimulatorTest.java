package edu.ncsu.csc216.forest_system.model;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;


/**
 * Test for SimpleSimulator class
 * @author Ken
 */
public class SimpleSimulatorTest {



	/**
	 * tested by ecosystem class
	 * checks if text file's list is same as processed by simulator
	 */
	@Test
	public void testStep() {
		SimpleSimulator sim = new SimpleSimulator("text.txt");
		sim.step();
		String[] exp = new String[4];
		exp[0] = "O Great Gray Owl";
		exp[1] = "M Mouse";
		exp[2] = "F Frog";
		exp[3] = "I Insect"; 
		assertArrayEquals(sim.displayItemNames(), exp);
		
		//
	}

	/**
	 * tests if displays correct list of item names from file,
	 * compared to hard coded copy
	 */
	@Test
	public void testDisplayItemNames() {
		SimpleSimulator instance = null;
		try {
			instance = new SimpleSimulator("trial.txt");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		File input = new File("trial.txt");
		assertTrue(input.exists()); 
		String[] exp = new String[4];
		exp[0] = "A Amphibean";
		exp[1] = "B Beowulf";
		exp[2] = "C Cannibal";
		
		exp[3] = "D Duck"; 
		
		assertArrayEquals(exp, instance.displayItemNames());
	}
	
}
