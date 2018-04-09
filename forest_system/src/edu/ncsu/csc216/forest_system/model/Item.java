/**
 * 
 */
package edu.ncsu.csc216.forest_system.model;

import java.awt.Color;

/**
 * Describes the behaviors that an item located in a cell on a grid must
 * provide.
 * 
 * @author Jo Perry
 */
public interface Item {

	/**
	 * Perform the action of the Item located in a given cell.
	 * 
	 * @param where
	 *            cell in which the item is located
	 * @param grid
	 *            grid that the cell exists in
	 */
	void act(Cell where, Grid grid);

	/**
	 * What is the single character symbol for the Item?
	 * 
	 * @return the character symbol
	 */
	char getSymbol();

	/**
	 * What is the display color for this Item?
	 * 
	 * @return the color
	 */
	Color getColor();

	/**
	 * Enable the Item to act.
	 */
	void enable();

	/**
	 * Disable the Item from acting.
	 */
	void disable();

	/**
	 * Is this Item alive?
	 * 
	 * @return true if the Item is alive, false otherwise.
	 */
	boolean isAlive();
}