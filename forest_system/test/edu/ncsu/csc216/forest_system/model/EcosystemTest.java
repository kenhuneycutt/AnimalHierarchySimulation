
package edu.ncsu.csc216.forest_system.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for class Ecosystem
 * @author Ken
 */
public class EcosystemTest {

	
	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Ecosystem#snapshot()}.
	 * tests whether snapshot returns an identical Item Array
	 * @throws Exception 
	 */
	@Test
	public void testSnapshot() throws Exception {
		Grid gridd = new Ecosystem(1, 1);
		Item[][] exp = new Item[1][1];
		assertEquals(gridd.snapshot().length, exp.length);
		assertEquals(gridd.snapshot()[0][0], exp[0][0]);
		assertArrayEquals(gridd.snapshot(), exp);
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Ecosystem#add(edu.ncsu.csc216.forest_system.model.Item, edu.ncsu.csc216.forest_system.model.Cell)}.
	 * tests if item was added to spot
	 */
	@Test
	public void testAdd() {
		Grid gridd = new Ecosystem(2, 2);
		Cell temp = new Cell(0, 0);
		gridd.isEmpty(temp);
		gridd.add((Item) new MiddleAnimal('C'), temp);
		assertFalse(gridd.isEmpty(temp));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Ecosystem#isEmpty(edu.ncsu.csc216.forest_system.model.Cell)}.
	 * tests whether the cell is empty when cell has been removed
	 */
	@Test
	public void testIsEmpty() {
		Grid gridd = new Ecosystem(2, 2);
		Cell temp = new Cell(0, 0);
		gridd.isEmpty(temp);
		gridd.remove(temp);
		assertTrue(gridd.isEmpty(temp));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Ecosystem#remove(edu.ncsu.csc216.forest_system.model.Cell)}.
	 * tests if organism is removed from cell after being created there when specified by remove()
	 */
	@Test
	public void testRemove() {
		Organism testOrganism = new LowAnimal('C');
		Cell location = new Cell(0, 0);
		Grid gridd = new Ecosystem(2, 2);
		gridd.add(testOrganism, location);
		assertFalse(gridd.isEmpty(location));
		gridd.remove(location);
		assertTrue(gridd.isEmpty(location));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Ecosystem#findFirstEmptyNeighbor(edu.ncsu.csc216.forest_system.model.Cell, int)}.
	 * tests if returns null if all neighbor locations are full
	 */
	@Test
	public void testFindFirstEmptyNeighbor() {
		Organism testOrganism = new LowAnimal('C');
		Cell location = new Cell(1, 1);
		Cell locationN = new Cell(1, 0);
		Cell locationS = new Cell(1, 2);
		Cell locationW = new Cell(0, 1);
		Cell locationE = new Cell(2, 1);
		Grid gridd2 = new Ecosystem(3, 3);
		gridd2.add(testOrganism, location);
		gridd2.add(testOrganism, locationN);
		gridd2.add(testOrganism, locationW);
		gridd2.add(testOrganism, locationS);
		gridd2.add(testOrganism, locationE);
		assertEquals(gridd2.findFirstEmptyNeighbor(location, 0), null);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Ecosystem#dueWest(edu.ncsu.csc216.forest_system.model.Cell)}.
	 * tests finding correct coordinates of cell to the west/left
	 */
	@Test
	public void testDueWest() {
		Grid gridd = new Ecosystem(2, 2);
		Cell new1;
		Cell center = new Cell(1, 1);
		new1 = gridd.dueWest(center);
		assertEquals(new1.getCol(), 0);
		assertEquals(new1.getRow(), 1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Ecosystem#dueEast(edu.ncsu.csc216.forest_system.model.Cell)}.
	 * tests if finding correct coordinates for cell to the east
	 */
	@Test
	public void testDueEast() {
		Grid gridd = new Ecosystem(3, 3);
		Cell new1;
		Cell center = new Cell(1, 1);
		new1 = gridd.dueEast(center);
		assertEquals(new1.getCol(), 2);
		assertEquals(new1.getRow(), 1);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Ecosystem#dueNorth(edu.ncsu.csc216.forest_system.model.Cell)}.
	 * tests if  finding correct coordinates for cell to the north
	 */
	@Test
	public void testDueNorth() {
		Grid gridd = new Ecosystem(2, 2);
		Cell new1;
		Cell center = new Cell(1, 1);
		new1 = gridd.dueNorth(center);
		assertEquals(new1.getCol(), 1);
		assertEquals(new1.getRow(), 0);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Ecosystem#dueSouth(edu.ncsu.csc216.forest_system.model.Cell)}.
	 *  tests if finding correct coordinates for cell to the south
	 */
	@Test
	public void testDueSouth() {
		Grid gridd = new Ecosystem(3, 3);
		Cell new1;
		Cell center = new Cell(1, 1);
		new1 = gridd.dueSouth(center);
		assertEquals(new1.getCol(), 1);
		assertEquals(new1.getRow(), 2);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.forest_system.model.Ecosystem#getItemAt(edu.ncsu.csc216.forest_system.model.Cell)}.
	 * tests if can retrieve correct item at certain cell after item is made in the cell
	 */
	@Test
	public void testGetItemAt() {
		Organism testOrganism = new LowAnimal('C');
		Cell location = new Cell(0, 0);
		Grid gridd = new Ecosystem(2, 2);
		gridd.add(testOrganism, location);
		assertEquals(gridd.getItemAt(location), testOrganism);
		
	}

}
