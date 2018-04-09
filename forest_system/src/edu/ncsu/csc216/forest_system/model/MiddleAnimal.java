
package edu.ncsu.csc216.forest_system.model;

import java.awt.Color;

/**
 * The middle animal that can move, eat, and breed
 * @author Ken
 *
 */
public class MiddleAnimal extends Organism {

	/**
	 * color 
	 */
	private static final Color COLOR = Color.orange;
	/**
	 * food rank
	 */
	private static final int FOOD_CHAIN_RANK = 400;
	/**
	 * number of steps until can breed after breeding
	 */
	private static final int BREED_TIME = 7;
	/**
	 * number of steps since ate until dies of starvation 
	 */
	private static final int STARVE_TIME = 6;
	
	
	/**
	 * Constructor of middle animal
	 * @param asymbol Symbol representing animal
	 */
	public MiddleAnimal(char asymbol) { //Char is capital...is that bad?
		super(asymbol, COLOR);
	}

	/**
	 * The animal chooses certain action to perform
	 * @param location The coordinates of the spot
	 * @param environment The grid holding Organism 
	 */
	public void act(Cell location, Grid environment) {
		if (canAct() ) {
			if (!breed(location, environment)  && (!eat(location, environment) ) ){
				move(location, environment);
			}
			incrementTimeSinceLastMeal();
			incrementTimeSinceLastBreed();
			if ( getTimeSinceLastMeal() >= STARVE_TIME ) {
				die();
			}
			disable();
		}
		
	}
	
	/**
	 * Gets food rank
	 * @return the food rank 
	 */
	@Override
	public int getFoodChainRank() {
		// TODO Auto-generated method stub
		return FOOD_CHAIN_RANK;
	}

	/**
	 * The animal makes a new baby
	 * @return the Item a new animal 
	 */
	@Override
	public Item makeNewBaby() {
		MiddleAnimal baby = new MiddleAnimal( getSymbol());
		return baby;
	}

	/**
	 * checks if can breed
	 * @param breedTime number of steps since last bred
	 * @return whether can breed  
	 */
	@Override
	public boolean pastBreedTime(int breedTime) {
		return (breedTime >= BREED_TIME );
	}


}
