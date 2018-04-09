
package edu.ncsu.csc216.forest_system.model;

/**
 * Holds the information about grid and oversees the organisms and cells
 * @author Ken Huneycutt
 */
public class Ecosystem implements Grid {

	//private static final Exception IllegalArgumentException = null;
	/**
	 * the maximum number of rows.
	 */
	private int maxRows;
	/**
	 * The maximum number of columns
	 */
	private int maxCols;
	
	/**
	 * The 2d array of Items to represent the grid.
	 */
	private Item[][] map;

	
	
	/**
	 * The construction of Ecosystem with rows and columns
	 * @param rows Number of rows
	 * @param columns Number of columns
	 */
	public Ecosystem(int rows , int columns){
		 maxRows = rows;
		 maxCols = columns;
		 map = new Item[rows][columns];
		 
		
	}
	
	/**
	 * Enables all organisms, allow actions, and then deletes the dead
	 * @return map The 2d array of Items of organisms after the actions
	 */
	public Item[][] snapshot(){
		return map;
	}
	
		
	/**
	 * adds the item to the specified cell location
	 * @param added The organism being created
	 * @param location The coordinates for created Item
	 */
	public void add(Item added, Cell location){
		map [location.getRow()] [location.getCol()] = added;
	}

	/**
	 * Checks if a cell spot is empty
	 * @param location The coordinates for specified grid spot
	 * @return whether the location has no Items 
	 */
	public boolean isEmpty(Cell location) {
		if (location.getRow() > maxRows || location.getRow() < 0 || location.getCol() > maxCols || location.getCol() < 0){
			throw new IllegalArgumentException();
		}
		return (map [location.getRow()] [location.getCol()] == null);
	}
	
	/**
	 * Removes any Item in the cell
	 * @param location The coordinates of the spot
	 */
	public void remove(Cell location){
		map [location.getRow()] [location.getCol()] = null;
	}
	
	/**
	 * Checks randomly to the cells next to it whether empty or not
	 * @param location The coordinates of the spot
	 * @param rand the seed for random
	 * @return The cell location if it finds an empty cell
	 */
	public Cell findFirstEmptyNeighbor(Cell location, int rand){
		for (int i = 0; i < 4; i++){
			if ( rand == 0 && isEmpty(dueWest(location))) {
				return dueWest(location);
			}
			else if ( rand == 1 && isEmpty(dueNorth(location))) {
				return dueNorth(location);
			}
			else if ( rand == 2 && isEmpty(dueEast(location))) {
				return dueEast(location);
			}
			else if ( rand == 3 && isEmpty(dueSouth(location))) {
				return dueSouth(location);
			}
			if (rand != 4) {
				rand++;
			} else {
				rand = 0;
			}
		}
		return null;
		
	}
	
	
	/**
	 * Checks the coordinates of the cell to the west, and wraps screen
	 * @param location The coordinates of the spot
	 * @return the location of the cell to the west 
	 */
	public Cell dueWest(Cell location) {
		if ( location.getCol() == 0) {
			Cell west = new Cell(location.getRow(), maxCols - 1);
			return west;
		} else {
			Cell west = new Cell(location.getRow(), location.getCol() - 1);
			return west;
		}
	}
	/**
	 * Checks the coordinates of the cell to the east, and wraps screen
	 * @param location The coordinates of the spot
	 * @return the location of the cell to the east 
	 */
	public Cell dueEast(Cell location){
		if (location.getCol() == maxCols - 1) {
			Cell east = new Cell(location.getRow(), 0);
			return east;
		} else{
			Cell east = new Cell(location.getRow(), location.getCol() + 1);
			return east;
		}
	}
	/**
	 * Checks the coordinates of the cell to the north, and wraps screen
	 * @param location The coordinates of the spot
	 * @return the location of the cell to the north 
	 */
	public Cell dueNorth(Cell location){
		if (location.getRow() == 0) {
			Cell north = new Cell( maxRows - 1, location.getCol() );
			return north;
		} else {
			Cell north = new Cell( location.getRow() - 1, location.getCol() );
			return north;
		}
	}
	/**
	 * Checks the coordinates of the cell to the east, and wraps screen
	 * @param location The coordinates of the spot
	 * @return the location of the cell to the east 
	 */
	public Cell dueSouth(Cell location){
		if (location.getRow() == maxRows - 1) {
			Cell south = new Cell(0, location.getCol());
			return south;
		} else {
			Cell south = new Cell(location.getRow() + 1, location.getCol());
			return south;
		}
	}

	/**
	 * Finds an Item for a specified cell location
	 * @param location The coordinates of the spot
	 * @return the Item in location 
	 */
	public Item getItemAt(Cell location) {
		return map[location.getRow()][location.getCol()];
	}
}
