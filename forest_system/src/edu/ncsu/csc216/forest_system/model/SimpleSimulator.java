package edu.ncsu.csc216.forest_system.model;
import java.io.*;
import java.util.Scanner;

/**
 * Processes the text file and initializes the grid and steps
 * @author Ken
 *
 */
public class SimpleSimulator {

	/**
	 * SIZE the size of the expected grid
	 */
	private static final int SIZE = 20;
	
	/**
	 * THRESHOLD the minimum number of different types of organism in the ecosystem
	 */
	private static final int THRESHOLD = 2;
	
	/**
	 * List of organism names.
	 */
	private String[] names;
	/**
	 * the amount of different names of organisms
	 */
	private int numberOfNames;
	/**
	 * list of symbols used for organisms
	 */
	private char[] symbol;
	
	/**
	 * The char representing an empty cell.
	 */
	private static final char EMPTY = '.';
	private Grid simpleSystem;
	//private Cell gridOfCell[][];
	
	
	/**
	 * Constructs SimpleSimulator, and process the input file to
	 * translate it into an array of organisms, also getting a list
	 * of symbols for the organisms and list of names
	 * @param inputFileName the Text file being processed 
	 * @throws IllegalArgumentException if file is nonexistent
	 */
	public SimpleSimulator(String inputFileName) {
		int numberOrganisms = 0;
		Scanner input = null;
		File inFile = new File(inputFileName);
	    try {
			input = new Scanner(inFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new IllegalArgumentException();
		}
	    if (input.hasNextInt()) {
	    	numberOfNames = input.nextInt();
	    	if (numberOfNames > 0) { 
	    		numberOrganisms = numberOfNames;
	    	}
	    }	
		symbol = new char[numberOrganisms];
		names = new String[numberOrganisms];
		if (input != null){
			createSymbolList(input, numberOrganisms);
		}
		createCells();
		
		simpleSystem = new Ecosystem(SIZE, SIZE);
		createGrid(inputFileName);
		 
	}
	
	/**
	 * Activates the start of step, by enabling then activating and deleting animals
	 */
	 public void step(){
		 
		 
		enableAll();
		activateAct();
		deleteDead();
	 }
	 
	 
	 /**
	 * gets the display of grid of organisms 
	 * after their actions
	 * @return the 2d array of Items that make the ecosystem grid 
	 */
	 public Item[][] snapshot(){
		 Item[][] creature = simpleSystem.snapshot();
		 return creature;
	 }
	 
	 /**
	 * Displays the list of names of the organisms
	 * @return list with names of organims 
	 */
	 public String[] displayItemNames(){
		 return names;
	 }
	 /**
	 * Displays the list of names of the organisms
	 * @return list with names of organims 
	 */
	 public String[] itemNames(){
		 return names;
	 }
	 
	 
	 
	 /**
	 * checks the input and finds the symbols for each organism
	 * and puts it on a list
	 * @param input The file input with organism names and symbols
	 * @param n The number of organisms in the file
	 * @throws IllegalArgumentException if file is nonexistent
	 */
	 private void createSymbolList(Scanner input, int n) {
		 System.out.println(input.nextLine());
		 Scanner line = null;
		 String tempLine = "";
		 char tempSymbol = 'a'; //initialize
		 
		 for (int i = 0; i < n; i++){
			tempLine = input.nextLine();
	 		line = new Scanner(tempLine);
	 		names[i] = tempLine;
	 		if (line.hasNext()){
	 			tempSymbol = line.next().charAt(0);
	 		}
	 		symbol[i] = tempSymbol;
	 	}
		line.close();
	 }
	
	 
	 /**
	 * Checks the coordinates of the cell to the west, and wraps screen
	 */
	 private void createCells(){
		 Cell[][] gridOfCells = new Cell[SIZE][SIZE];
		 for (int i = 0; i < SIZE; i++){
	 		for (int j = 0; j < SIZE; j++){
	 			gridOfCells[i][j] = new Cell( i, j);
	 		}
	 	}
	 }
	 
	 /**
	 * Creates a grid by reading the text file and adding the items to the cells
	 * @param inputFileName The text file being read with the grid initialization
	 * @throws IllegalArgumentException if n is too small
	 */
	 private void createGrid(String inputFileName) {
		 String hierarchy = null; 
		 Scanner input = null;
		 char character = 'a'; //initialize
		 String stringDummy = "";
		 int n = 0;
		 
		 try {
		    input = new Scanner(new File(inputFileName));
	    } catch ( FileNotFoundException e){
           throw new IllegalArgumentException();
        }	
		 if (input.hasNextInt()){
			 n = input.nextInt(); //4
		 }
		 if (n >= THRESHOLD){
			 input.nextLine();
		 	for (int i = 0; i < n; i++){
				 input.nextLine();
			 }
			 
			 for (int i = 0; i < SIZE; i++){
				 if (input.hasNext()) {
					 stringDummy = input.next();
				 }
				 for (int j = 0; j < SIZE; j++){
					 character = stringDummy.charAt(j);
					 if (character != EMPTY){
						 for (int k = 0; k < symbol.length; k++){
							 if (character == symbol[k]){
								 if (k == 0){
									 hierarchy = "high";
								 }
								 else if (k == symbol.length - 1) {
									 hierarchy = "low";
								 }
								 else{
									 hierarchy = "middle";
								 }
							 }
						 }
					 } else {
						 hierarchy = "empty";
					 }
					 if (hierarchy != null) {
						 if (hierarchy.equals("high") ) {
							 HighAnimal newie = new HighAnimal(character);
							 Cell temp = new Cell(i, j);
							 simpleSystem.add(newie, temp);
						 } else if ( hierarchy.equals("middle") ) {
							 MiddleAnimal newie = new MiddleAnimal(character);
							 Cell temp = new Cell(i, j);
							 simpleSystem.add(newie, temp);
						 } else if ( hierarchy.equals("low") ) {
							 LowAnimal newie = new LowAnimal(character);
							 Cell temp = new Cell(i, j);
							 simpleSystem.add(newie, temp);
						 }
					 }
				 }	
			 }
		 input.close();
		 } else {
			 input.close();
			 throw new IllegalArgumentException();
		 }
	 }
	 
	 /**
	 * Checks the map and deletes any organisms that are dead
	 */
	private void deleteDead(){
		for (int i = 0; i < SIZE; i++){
	 		for (int j = 0; j < SIZE; j++){
	 			Cell temp = new Cell(i, j);
	 			if ((!simpleSystem.isEmpty(temp)) && !simpleSystem.getItemAt(temp).isAlive()) {
 					simpleSystem.remove(temp);
	 			}
	 		}
		}
	}
	
	/**
	 * goes through map and lets each organism act
	 */
	private void activateAct(){
		for (int i = 0; i < SIZE; i++){
	 		for (int j = 0; j < SIZE; j++){
	 			Cell temp = new Cell(i, j);
	 			if (!simpleSystem.isEmpty(temp) && ((Organism)simpleSystem.getItemAt(temp)).canAct()){
 					((Organism)simpleSystem.getItemAt(temp)).act(temp, simpleSystem);
	 			}
	 		}
		}
	}
	
	/**
	 * Checks the coordinates of the cell to the south, and wraps screen
	 */
	private void enableAll(){
		//if (useless == 0){
			//useless++;
		//} else
		for (int i = 0; i < SIZE; i++){
	 		for (int j = 0; j < SIZE; j++){
	 			Cell temp = new Cell(i, j);
	 			if (!simpleSystem.isEmpty(temp)){//changed to temp
	 				if ( ((Organism)simpleSystem.getItemAt(temp)).isAlive()){
	 					((Organism)simpleSystem.getItemAt(temp)).enable();
	 				} else {
	 					simpleSystem.remove(temp);
	 				}
	 			}
	 		}
		}
		
	}
	 
}


