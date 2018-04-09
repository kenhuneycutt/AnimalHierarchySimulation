/**
 * 
 */
package edu.ncsu.csc216.forest_system.model;
import java.awt.Color;
import java.util.Random;

/**
 * The parent of individual animals, holds common methods and variable
 * @author Ken
 *
 */
public abstract class Organism implements Item {
	
	/**
	 * Random number constant to keep constant random outputs
	 */
	private static int seed = 500;
	/**
	 * Generates the random  numbers using seed
	 */
	private static Random randomGenerator = new Random(seed); 
	
	/**
	 * The number of steps since organism last ate
	 */
	private int timeSinceLastMeal;
	
	/**
	 * the number of steps since organism last bred
	 */
	private int timeSinceLastBreed;
	/**
	 * whether the organism can act now
	 */
	private boolean canActThisStep;
	/**
	 * the symbol for organism
	 */
	private char symbol;
	/**
	 * Alive whether the organism is dead or alive
	 */
	private boolean alive;
	/**
	 * the color
	 */
	private Color color;
	
	/**
	 * Constant for low animal foodrank
	 */
	/*private static final int LowAnimalRank=200;
	
	private static final int MiddleAnimalRank = 400;
	
	private static final int HighAnimalRank = 500;
	*/
	
	 /**
	 * Constructor that sets inital values for organism
	 * @param symbol The symbol for specific animal
	 * @param color THe color represent organism
	 */
	public Organism(char symbol, Color color){
		this.symbol = symbol;
		this.color = color;
		timeSinceLastMeal = 0;
		timeSinceLastBreed = 0;
		alive = true;
		canActThisStep = false;
		
	}
	

	 /**
	  * Sets the random constant
	  * @param seedInput the random constant
	  */
	public static void setRandomSeed(int seedInput){
		randomGenerator.setSeed(seedInput);
	}

	 /**
	  * checks if alive
	  * @return whether alive 
	  */
	public boolean isAlive(){
		return alive;
	}
	
	 /**
      * Sets animal to dead
 	  */
	public void die(){
		alive = false;
	}
	
	 /**
	  * checks if animal can act
	  * @return whether can act 
	  */
	public boolean canAct(){
		return canActThisStep;
	}
	
	 /**
	  * lets animal act 
	  */
	public void enable(){
		canActThisStep = true;
	}
	
 	/**
	 * does not let animal act
	 */
	public void disable(){
		canActThisStep = false;
	}
	

	/**
	 * Checks the steps since last bred
	 * @return the steps since last breed 
	 */
	public int getTimeSinceLastBreed(){
		return timeSinceLastBreed;
	}
	
	/**
	 * checks when animal last ate
	 * @return when last ate 
	 */
	public int getTimeSinceLastMeal(){
		return timeSinceLastMeal;
	}
	
	/**
	 * increases time since last bred by 1
	 */
	public void incrementTimeSinceLastBreed(){
		timeSinceLastBreed++;
	}

	/**
	 * increments time since last ate by one
	 */
	public void incrementTimeSinceLastMeal(){
		timeSinceLastMeal++;
	}
	
	/**
	 * The animal attempts to breed 
	 * @param location The coordinates of the spot
	 * @param environment The grid holding the organism
	 * @return whether the attempt was successful 
	 */
	public boolean breed(Cell location, Grid environment){
		if (pastBreedTime(timeSinceLastBreed)){
			Cell destination = environment.findFirstEmptyNeighbor(location, randomGenerator.nextInt(4));
			if (destination == null){
				return false;
			} else {
				environment.add(makeNewBaby(), destination);
				timeSinceLastBreed = 0;
				return true;	
			}
		}
		return false;
	}
	
	/**
	 * Animal attempts to move to empty spot
	 * @param location The coordinates of the spot
	 * @param environment The grid holding organism
	 */
	public void move(Cell location, Grid environment){
		
		int direction = randomGenerator.nextInt(4); // Limits the results to 0, 1, 2, 3
		Cell destination = environment.findFirstEmptyNeighbor(location, direction);
		if (destination != null){
			environment.add(this, destination); 
			environment.remove(location);
		}
		

		
	}
	
	/**
	 * Animal attempts to eat a lower level animal
	 * @param location The coordinates of the spot
	 * @param environment The grid holding organism
	 * @return the location of the cell to the west 
	 */
	public boolean eat(Cell location, Grid environment){
		Cell destination = checkNeighborsFor(getFoodChainRank(), location, environment);
		if ( destination != null){
			timeSinceLastMeal = 0;
			return true;
		} else return false;
		
	}

	/**
	 * returns the symbol for animal
	 * @return the symbol for animal 
	 */
	public char getSymbol(){
		return symbol;
	}
	
	/**
	 * returns the color for animal
	 * @return the color for animal 
	 */
	public Color getColor(){
		return color;
	}
	
	/**
	 * gets food rank
	 * @return the food rank 
	 */
	public abstract int getFoodChainRank();
	
	/**
	 * creates a baby animal
	 * @return the new baby animal
	 */
	public abstract Item makeNewBaby();
	
	/**
	 * the animal does certain actions
	 * @param location The coordinates of the spot
	 * @param environment The grid holding organism
	 */
	public abstract void act(Cell location, Grid environment);
	
	/**
	 * checks if past breed time
	 * @param breedTime Amount of time that has passed since last bred
	 * @return whether can breed 
	 */
	public abstract boolean pastBreedTime(int breedTime);
	
	/**
	 * checks the neighboring cells for unempty cells that have lower foodrank animal
	 * @param foodRank FoodRank for current animal
	 * @param location the coordinates of the spot
	 * @param environment the grid holding organism
	 * @return the location of the cell that is lower ranked and not empty 
	 */
	private Cell checkNeighborsFor(int foodRank, Cell location, Grid environment){
		int rand = randomGenerator.nextInt(4);
		for (int i = 0; i < 4; i++){
			if (!environment.isEmpty(environment.dueWest(location)) && rand == 0 ){
				int rankWest = ((Organism)(environment.getItemAt(environment.dueWest(location)))).getFoodChainRank();
				if (rankWest < foodRank){ 
					return environment.dueWest(location);
				}
			}
			if (!environment.isEmpty(environment.dueNorth(location)) && rand == 1 ){
				int rankNorth = ((Organism)(environment.getItemAt(environment.dueNorth(location)))).getFoodChainRank();
				if ( rankNorth < foodRank){
					return environment.dueNorth(location);
				}
			}
			if (!environment.isEmpty(environment.dueEast(location)) && rand == 2 ){
				int rankEast = ((Organism)(environment.getItemAt(environment.dueEast(location)))).getFoodChainRank();
				if ( rankEast < foodRank){
					return environment.dueEast(location);
				}
			}
			if (!environment.isEmpty(environment.dueSouth(location)) && rand == 3 ){
				int rankSouth = ((Organism)(environment.getItemAt(environment.dueSouth(location)))).getFoodChainRank();
				if ( rankSouth < foodRank){
					return environment.dueSouth(location);
				}
			}
		}
		return null;
		
	}
	
	
}
