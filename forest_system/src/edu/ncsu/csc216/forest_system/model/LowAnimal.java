
package edu.ncsu.csc216.forest_system.model;
import java.awt.Color;

/**
 * Low level organism that breeds and moves
 * @author Ken
 */
public class LowAnimal extends Organism {

	/**
	 * the color 
	 */
	private static final Color COLOR = Color.green;
	/**
	 * the food rank
	 */
	private static final int FOOD_CHAIN_RANK = 200;
	/**
	 * the breed time minimum after breeding
	 */
	private static final int BREED_TIME = 1;
	/**
	 * the number of steps that will die if do not eat
	 */
	private static final int STARVE_TIME = 10;
	/**
	 * number of steps alive so far
	 */
	private int age; 
	
	/**
	 * Constructor for low animal
	 * @param symbola The symbol for animal
	 */
	public LowAnimal(char symbola) {
		super(symbola, COLOR);
	}

	/**
	 * Animal chooses an action to perform
	 * @param location The coordinates of the spot
	 * @param environment The grid holding organism
	 */
	public void act(Cell location, Grid environment){
		if (canAct()){
			if (!breed(location, environment)){
				move(location, environment);
			}
			age++;
			incrementTimeSinceLastBreed();
			if (age >= STARVE_TIME){
				die();
			}
			disable(); 	
		}		
	}
	
	/**
	 * returns the food rank
	 * @return the food rank 
	 */
	@Override
	public int getFoodChainRank() {
		return FOOD_CHAIN_RANK;
	}

	/**
	 * makes a new baby at neighbor cell
	 * @return the New Baby Animal 
	 */
	@Override
	public Item makeNewBaby() {
		LowAnimal baby = new LowAnimal(getSymbol());
		return baby;
	}

	/**
	 * Checks if animal can breed again
	 * @param breedTime number of steps since last bred
	 * @return whether can breed 
	 */
	@Override
	public boolean pastBreedTime(int breedTime) {
		return (breedTime >= BREED_TIME);
	}

}
