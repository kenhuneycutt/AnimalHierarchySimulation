package edu.ncsu.csc216.forest_system.model;

import java.awt.Color;


/**
 * High Animal with actions move, eat, and breed
 * @author Ken
 *
 */
public class HighAnimal extends Organism {

	/**
	 * color
	 */
	private static final Color COLOR = Color.red;
	/**
	 * food rank
	 */
	private static final int FOOD_CHAIN_RANK = 500;
	/**
	 * number of steps to have since last breed to breed
	 */
	private static final int BREED_TIME = 15;
	/** 
	 * number of steps before die of starvation without food
	 */
	private static final int STARVE_TIME = 5;
	
	
	/**
	 * The constructor of high animal
	 * @param symbol The symbol for animal
	 */
	public HighAnimal(char symbol) {
		super(symbol, COLOR);
	}

	/**
	 * The animal chooses an action to perform
	 * @param location The coordinates of the spot
	 * @param environment The grid holding Organism
	 */
	public void act(Cell location, Grid environment){
		if (canAct()){
			if (!eat(location, environment) && (!breed(location, environment))){
				move(location, environment);
			}
			incrementTimeSinceLastMeal();
			incrementTimeSinceLastBreed();
			if (getTimeSinceLastMeal() >= STARVE_TIME) {
				die();
			}
			disable();
		}
	}
	
	/**
	 * gets food rank
	 * @return the food rank 
	 */
	@Override
	public int getFoodChainRank() {
		// TODO Auto-generated method stub
		return FOOD_CHAIN_RANK;
	}

	/**
	 * the animal makes a new baby
	 * @return the baby animal  
	 */
	@Override
	public Item makeNewBaby() {
		HighAnimal baby = new HighAnimal(getSymbol() );
		return baby;
	}

	/**
	 * whether the animal is beyond the breed limit
	 * @param breedTime amount of steps since last breed
	 * @return whether past the Breed limit 
	 */
	@Override
	public boolean pastBreedTime(int breedTime) {
		return (breedTime >= BREED_TIME );
	}

}