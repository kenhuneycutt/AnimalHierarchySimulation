package edu.ncsu.csc216.forest_system.model;

/**
 * Behaviors of a rectangular grid. Each grid cell in the grid can contain an
 * Item.
 * 
 * @author Jo Perry
 */
public interface Grid {

	/**
	 * Is the grid cell empty?
	 * 
	 * @param location
	 *            location of the cell on the grid
	 * @return true if there is no item in that cell.
	 */
	boolean isEmpty(Cell location);

	/**
	 * What is the item at a particular grid cell?
	 * 
	 * @param location
	 *            location of the cell on the grid
	 * @return the Item at the given cell or null if the cell is empty
	 */
	Item getItemAt(Cell location);

	/**
	 * Remove the Item from a particular grid cell.
	 * 
	 * @param location
	 *            location of the cell on the grid
	 */
	void remove(Cell location);

	/**
	 * Add an Item to a particular grid cell.
	 * 
	 * @param item
	 *            Item to add
	 * @param location
	 *            location of the cell on the grid
	 */
	void add(Item item, Cell location);

	/**
	 * Finds empty cell next to current location
	 * @param position Location of cell
	 * @param startDirection the initial direction
	 * @return empty cell next to initial cell
	 */
	public Cell findFirstEmptyNeighbor(Cell position, int startDirection);

	/**
	 * What cell is due north of the given cell?
	 * 
	 * @param x
	 *            The given cell
	 * @return The cell due north
	 */
	Cell dueNorth(Cell x);

	/**
	 * What cell is due south of the given cell?
	 * 
	 * @param x
	 *            The given cell
	 * @return The cell due south
	 */
	Cell dueSouth(Cell x);

	/**
	 * What cell is due west of the given cell?
	 * 
	 * @param x
	 *            The given cell
	 * @return The cell due west
	 */
	Cell dueWest(Cell x);

	/**
	 * What cell is due east of the given cell?
	 * 
	 * @param x
	 *            The given cell
	 * @return The cell due east
	 */
	Cell dueEast(Cell x);

	/**
	 * What is in the grid?
	 * 
	 * @return a snapshot of the grid, showing the contents of each grid cell.
	 */
	Item[][] snapshot();

}