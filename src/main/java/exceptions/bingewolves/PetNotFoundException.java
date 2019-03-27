/**
 * 
 */
package exceptions.bingewolves;

/**
 * This class is a custom exception that will be thrown when the pet name provided
 * returns null when searched for in the petMap
 * 
 * @author David Alvarez, 2/20/19
 */
public class PetNotFoundException extends Exception {
	
	public PetNotFoundException() {
		super("That Pet does not exist please check spelling or enter in a new Pet.");
	}
}
