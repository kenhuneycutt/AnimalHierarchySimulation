package edu.ncsu.csc216.forest_system.model;

/**
 * Represents cells with coordinates in the ecosystem
 * @author Ken Huneycutt
 */
public class Cell {

	/**
	 * The row value of cell.
	 */
	private int row;
	/**
	 * The column value for cell.
	 */
	private int column;
	
	/**
	 * Construct a cell object.
	 * sets the cell's rows and columns.
	 * @param row Row defines the x coordinate of cell
	 * @param column defines the y coordinate of cell 
	 */
	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	/**
	 * This method returns the row of Cell
	 * @return row The coordinate corresponding to cell's row.
	 */
	public int getRow(){
		return row;
	}
	
	/**
	 * This method returns the row of Cell
	 * @return row The coordinate corresponding to cell's row.
	 */
	public int getCol(){
		return column;
	}

}
